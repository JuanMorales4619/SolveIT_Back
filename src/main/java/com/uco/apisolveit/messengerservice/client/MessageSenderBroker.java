package com.uco.apisolveit.messengerservice.client;

import com.uco.apisolveit.config.ClientQueueConfig;
import com.uco.apisolveit.domain.person.Person;
import com.uco.apisolveit.domain.publication.Publication;
import com.uco.apisolveit.service.person.MessageSender;
import com.uco.apisolveit.util.gson.MapperJsonObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageSenderBroker implements MessageSender<Publication> {

    private final RabbitTemplate rabbitTemplate;
    private final MapperJsonObject mapperJsonObjeto;
    private final ClientQueueConfig clientQueueConfig;

    public MessageSenderBroker(RabbitTemplate rabbitTemplate, MapperJsonObject mapperJsonObjeto, ClientQueueConfig clientQueueConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapperJsonObjeto = mapperJsonObjeto;
        this.clientQueueConfig = clientQueueConfig;
    }

    @Override
    public void execute(Publication message, String idMessage) {
        MessageProperties propiedadesMensaje = generarPropiedadesMensaje(idMessage);

        Optional<Message> cuerpoMensaje = obtenerCuerpoMensaje(message, propiedadesMensaje);
        if (!cuerpoMensaje.isPresent()) {
            return;
        }
        rabbitTemplate.convertAndSend(clientQueueConfig.getExchangeName(), clientQueueConfig.getRoutingKeyName(), cuerpoMensaje.get());
    }

    private org.springframework.amqp.core.MessageProperties generarPropiedadesMensaje(String idMessageSender ) {
        return MessagePropertiesBuilder.newInstance()
                .setContentType(org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_JSON)
                .setHeader("idMensaje", idMessageSender)
                .build();
    }

    private Optional<Message> obtenerCuerpoMensaje(Object mensaje, org.springframework.amqp.core.MessageProperties propiedadesMensaje) {
        Optional<String> textoMensaje = mapperJsonObjeto.ejecutarGson(mensaje);

        return textoMensaje.map(msg -> MessageBuilder
                .withBody(msg.getBytes())
                .andProperties(propiedadesMensaje)
                .build());

    }

}
