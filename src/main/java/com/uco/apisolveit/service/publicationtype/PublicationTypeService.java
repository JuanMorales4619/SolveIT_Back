package com.uco.apisolveit.service.publicationtype;

import com.uco.apisolveit.domain.publicationtype.PublicationType;
import com.uco.apisolveit.repository.publicationtype.IPublicationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class PublicationTypeService {
    @Autowired
    private IPublicationTypeRepository publicationTypeRepository;

    public Flux<PublicationType> get() {
        return publicationTypeRepository.findAll();
    }

    public Mono<PublicationType> get(String publicationTypeId) {

        return publicationTypeRepository.findByCategory(publicationTypeId);
    }
}
