package com.uco.apisolveit.service.publication;

import com.uco.apisolveit.domain.publication.Publication;
import com.uco.apisolveit.util.Constant;
import com.uco.apisolveit.util.UtilObject;
import com.uco.apisolveit.util.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.uco.apisolveit.repository.publication.IPublicationRepository;
import com.uco.apisolveit.domain.publicationtype.PublicationType;
import java.util.Date;
import java.util.Objects;


@Service
public class PublicationService {
    @Autowired
    private IPublicationRepository publicationRepository;

    public Flux<Publication> getAll() {
        return publicationRepository.findAll();
    }

    public Mono<Publication> getById(String id) {
        return publicationRepository.findById(id);
    }
    public Flux<Publication> getByTitle(String title) {
        return publicationRepository.findByTitle(title);
    }
    public Flux<Publication> getByUser(String user) {
        return publicationRepository.findByUser(user);
    }
    public Flux<Publication> getByType(PublicationType type) {
        return publicationRepository.findByType(type);
    }
    public Flux<Publication> getByDate(Date date) {
        if(!Objects.isNull(date)){
            UtilString.requieresNoNullOrNoEmpty(date.toString(),String.format(Constant.TXT_EXPECT_VALUE));
        }
        return publicationRepository.findByDate(date);
    }
    public Flux<Publication> getBySome(String title, String category, Date date) {
        if(!Objects.isNull(title) && !Objects.isNull(category) &&!Objects.isNull(date)){
            UtilString.requieresNoNullOrNoEmpty(title,String.format(Constant.TXT_EXPECT_VALUE));
            UtilString.requieresNoNullOrNoEmpty(category,String.format(Constant.TXT_EXPECT_VALUE));
            UtilString.requieresNoNullOrNoEmpty(date.toString(),String.format(Constant.TXT_EXPECT_VALUE));
        }
        return publicationRepository.findByTitleTypeDate(title,category,date);
    }


    public Mono<Publication> save(Publication publication){
        validationData(publication);
        return publicationRepository.save(publication);
    }

    public Mono<Publication>  put(String publicationId, Publication publication){
        validationData(publication);
        return publicationRepository.findById(publicationId).flatMap(existingPublication -> {

            existingPublication.setPublicationTitle(publication.getPublicationTitle().isEmpty() ? existingPublication.getPublicationTitle() : publication.getPublicationTitle());
            existingPublication.setCategory(UtilObject.getUtilObject().isNull(publication.getCategory()) ? existingPublication.getCategory() : publication.getCategory());
            existingPublication.setDescription(publication.getDescription().isEmpty() ? existingPublication.getDescription() : publication.getDescription());
            existingPublication.setPhone(Objects.isNull(publication.getPhone()) ? existingPublication.getPhone() : publication.getPhone());

            return publicationRepository.save(existingPublication);
        });
    }

    public Mono<Void> delete(String publicationId){
        return publicationRepository.findById(publicationId).flatMap(existingPerson -> publicationRepository.deleteById(publicationId));
    }

    private void validationData(Publication publication){
        UtilString.requieresNoNullOrNoEmpty(publication.getPublicationTitle(),String.format(Constant.TXT_EXPECT_VALUE, publication.getPublicationTitle()));
        UtilString.requieresNoNullOrNoEmpty(publication.getCategory().toString(),String.format(Constant.TXT_EXPECT_VALUE, publication.getCategory()));
        UtilString.requieresNoNullOrNoEmpty(publication.getDescription(),String.format(Constant.TXT_EXPECT_VALUE, publication.getDescription()));
        UtilString.requieresNoNullOrNoEmpty(publication.getPhone(),String.format(Constant.TXT_EXPECT_VALUE, publication.getPhone()));

        UtilString.requieresLength(publication.getPhone(), 10, 10,String.format(Constant.TXT_NO_LENGTH_REQUIERED, publication.getPhone()));

    }

}