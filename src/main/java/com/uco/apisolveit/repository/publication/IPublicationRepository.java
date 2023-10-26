package com.uco.apisolveit.repository.publication;
import com.uco.apisolveit.domain.publication.Publication;
import com.uco.apisolveit.domain.publicationtype.PublicationType;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.Date;
public interface IPublicationRepository extends ReactiveCrudRepository<Publication, String>
{


    @Query("{ 'publicationTitle': ?0}")
    Flux<Publication> findByTitle( String title);

    @Query("{ 'userName': ?0}")
    Flux<Publication> findByUser( String user);

    @Query("{'category': ?0}")
    Flux<Publication> findByType(PublicationType type);

    @Query("{date: ?0}")
    Flux<Publication> findByDate(Date date);

    @Query("{title: ?0, category: ?1, date: ?2}")
    Flux<Publication> findByTitleTypeDate(String title, String type, Date date);


}
