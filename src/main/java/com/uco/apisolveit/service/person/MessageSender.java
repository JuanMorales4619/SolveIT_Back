package com.uco.apisolveit.service.person;

public interface MessageSender<T>{
    void execute(T message, String idMessage);
}
