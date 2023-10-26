package com.uco.apisolveit.controller.publication;


import com.uco.apisolveit.controller.Response;
import com.uco.apisolveit.domain.publication.Publication;
import com.uco.apisolveit.dto.publication.PublicationDTO;
import com.uco.apisolveit.messengerservice.client.MessageSenderBroker;
import com.uco.apisolveit.service.publication.PublicationService;
import com.uco.apisolveit.service.publicationtype.PublicationTypeService;
import com.uco.apisolveit.util.UtilObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rest")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @Autowired
    private PublicationTypeService publicationTypeService;

    @Autowired
    private MessageSenderBroker messageSenderBroker;

    @GetMapping("/publication")
    public ResponseEntity<Response<Publication>> getPublication(){
        ResponseEntity<Response<Publication>> responseEntity;
        List<String> messages = new ArrayList<>();
        Response<Publication> response = new Response<>();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        try{
            response.setData(publicationService.getAll().collectList().block());
            messages.add("All publications");
            statusCode = HttpStatus.OK;
        }catch (Exception exception){
            messages.add(exception.getMessage());
        }
        response.setMessage(messages);
        response.setStatus(statusCode);
        responseEntity = new ResponseEntity<>(response,statusCode);
        return responseEntity;
    }
    @GetMapping("/publication/title/{title}")
    public ResponseEntity<Response<Publication>> getPublicationByTitle(@PathVariable("title") String title){
        ResponseEntity<Response<Publication>> responseEntity;
        List<String> messages = new ArrayList<>();
        Response<Publication> response = new Response<>();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        try{
            if(!UtilObject.getUtilObject().getDefault( publicationService.getByTitle(title).collectList().block(),new ArrayList<Publication>()).isEmpty()){
                response.setData(publicationService.getByTitle(title).collectList().block());
                messages.add("Publications by title");
                statusCode = HttpStatus.OK;
            }else{
                messages.add("No publications found with this title.");
                statusCode = HttpStatus.NOT_FOUND;
            }

        }catch (Exception exception){
            messages.add(exception.getMessage());
        }
        response.setMessage(messages);
        response.setStatus(statusCode);
        responseEntity = new ResponseEntity<>(response,statusCode);
        return responseEntity;
    }

    @GetMapping("/publication/user/{user}")
    public ResponseEntity<Response<Publication>> getPublicationByUser(@PathVariable("user") String user){
        ResponseEntity<Response<Publication>> responseEntity;
        List<String> messages = new ArrayList<>();
        Response<Publication> response = new Response<>();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        try{
            if(!UtilObject.getUtilObject().getDefault( publicationService.getByUser(user).collectList().block(),new ArrayList<Publication>()).isEmpty()){
                response.setData(publicationService.getByUser(user).collectList().block());
                messages.add("Publications by user");
                statusCode = HttpStatus.OK;
            }else{
                messages.add("user without posts.");
                statusCode = HttpStatus.NOT_FOUND;
            }

        }catch (Exception exception){
            messages.add(exception.getMessage());
        }
        response.setMessage(messages);
        response.setStatus(statusCode);
        responseEntity = new ResponseEntity<>(response,statusCode);
        return responseEntity;
    }

    @GetMapping("/publication/type/{type}")
    public ResponseEntity<Response<Publication>> getPublicationByType(@PathVariable("type") String type){
        ResponseEntity<Response<Publication>> responseEntity;
        List<String> messages = new ArrayList<>();
        Response<Publication> response = new Response<>();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        try{

            if(!UtilObject.getUtilObject().getDefault(publicationService.getByType(publicationTypeService.get(type).block()).collectList().block(), new ArrayList<Publication>()).isEmpty()){
                response.setData(publicationService.getByType(publicationTypeService.get(type).block()).collectList().block());
                messages.add("Publications by type");
                statusCode = HttpStatus.OK;
            }else{
                messages.add("No publications found with this type");
                statusCode = HttpStatus.NOT_FOUND;
            }

        }catch (Exception exception){
            messages.add(exception.getMessage());
        }
        response.setMessage(messages);
        response.setStatus(statusCode);
        responseEntity = new ResponseEntity<>(response,statusCode);
        return responseEntity;
    }

    @GetMapping("/publication/date/{date}")
    public ResponseEntity<Response<Publication>> getPublicationByDate(@PathVariable("date") Date date){
        ResponseEntity<Response<Publication>> responseEntity;
        List<String> messages = new ArrayList<>();
        Response<Publication> response = new Response<>();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        try{
            if(!UtilObject.getUtilObject().getDefault(publicationService.getByDate(date).collectList().block(),new ArrayList<Publication>()).isEmpty()){
                response.setData(publicationService.getByDate(date).collectList().block());
                messages.add("Publications by date");
                statusCode = HttpStatus.OK;
            }else{
                messages.add("No publications found with this date");
                statusCode = HttpStatus.NOT_FOUND;
            }

        }catch (Exception exception){
            messages.add(exception.getMessage());
        }
        response.setMessage(messages);
        response.setStatus(statusCode);
        responseEntity = new ResponseEntity<>(response,statusCode);
        return responseEntity;
    }
    @GetMapping("/publication/some/{title}/{category}/{date}")
    public ResponseEntity<Response<Publication>> getPublicationBySome(@PathVariable("title") String title,@PathVariable("category") String category,@PathVariable("date") Date date){
        ResponseEntity<Response<Publication>> responseEntity;
        List<String> messages = new ArrayList<>();
        Response<Publication> response = new Response<>();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        try{
            response.setData(publicationService.getBySome(title,category,date).collectList().block());
            messages.add("Publications by title, type and date");
            statusCode = HttpStatus.OK;
        }catch (Exception exception){
            messages.add(exception.getMessage());
        }
        response.setMessage(messages);
        response.setStatus(statusCode);
        responseEntity = new ResponseEntity<>(response,statusCode);
        return responseEntity;
    }

    @PostMapping("/publication")
    public ResponseEntity<String> postPublication(@RequestBody PublicationDTO publicationDTO){
        return publicationService.save(Publication.setData(publicationDTO)).map(savePublication ->
                new  ResponseEntity<String>("savePublication",HttpStatus.CREATED))
                .defaultIfEmpty(new  ResponseEntity<>("Publication not found",HttpStatus.NOT_FOUND)).block();
       // messageSenderBroker.execute(Publication.setData(publicationDTO),"1");
    }

    @PutMapping("/publication/{id}")
    public ResponseEntity<Response<Publication>> putPublication(@PathVariable("id") String id,@RequestBody PublicationDTO publicationDTO){
        ResponseEntity<Response<Publication>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<Publication> data = new ArrayList<>();
        Response<Publication> response = new Response<>();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        try{
            data.add(publicationService.put(id,Publication.setData(publicationDTO)).block());
            messages.add("Publication actualized successfully");
            statusCode = HttpStatus.OK;
        }
        catch (Exception exception){
            messages.add(exception.getMessage());
        }
        response.setData(data);
        response.setMessage(messages);
        response.setStatus(statusCode);
        responseEntity = new ResponseEntity<>(response,statusCode);
        return responseEntity;
    }


    @DeleteMapping("/publication/{publicationId}")
    public Mono<ResponseEntity<String>>  deletePublication(@PathVariable("publicationId") String publicationId){
        return publicationService.delete(publicationId).then(Mono.just(new ResponseEntity<String>("Publication delete Successfully",HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>("Publication not found",HttpStatus.NOT_FOUND));
    }
}