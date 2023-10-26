package com.uco.apisolveit.repository.identificationtype;

import com.uco.apisolveit.domain.identificationtype.IdentificationType;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IIdentificationType extends ReactiveCrudRepository<IdentificationType, String> {

    @Query("{'description': ?0}")
    Mono<IdentificationType> findByDescription(String description);
}
