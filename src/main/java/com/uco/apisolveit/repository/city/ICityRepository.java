package com.uco.apisolveit.repository.city;

import com.uco.apisolveit.domain.city.City;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ICityRepository extends ReactiveCrudRepository<City,String> {
    @Query("{'description': ?0}")
    Mono<City> findByName(String description);
}
