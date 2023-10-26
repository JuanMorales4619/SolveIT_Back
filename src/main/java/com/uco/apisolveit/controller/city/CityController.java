package com.uco.apisolveit.controller.city;

import com.uco.apisolveit.domain.city.City;
import com.uco.apisolveit.service.city.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/rest")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/City")
    public Flux<City> get(){
        return cityService.get();
    }

    @GetMapping("/City/{description}")
    public Mono<City> get(@PathVariable("description") String description){
        return cityService.get(description);
    }
}
