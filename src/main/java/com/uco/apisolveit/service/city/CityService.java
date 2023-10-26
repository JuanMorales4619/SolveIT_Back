package com.uco.apisolveit.service.city;

import com.uco.apisolveit.domain.city.City;
import com.uco.apisolveit.repository.city.ICityRepository;
import com.uco.apisolveit.util.Constant;
import com.uco.apisolveit.util.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CityService {
    @Autowired
    private ICityRepository cityRepository;

    public Flux<City> get() {
        return cityRepository.findAll();
    }
    public Mono<City> get(String cityId) {
        if(!Objects.isNull(cityId)){
            UtilString.requieresNoNullOrNoEmpty(cityId,String.format(Constant.TXT_EXPECT_VALUE, cityId));
        }
        return cityRepository.findByName(cityId);
    }

}
