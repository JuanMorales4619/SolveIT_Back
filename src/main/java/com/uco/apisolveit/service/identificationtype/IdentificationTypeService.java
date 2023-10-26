package com.uco.apisolveit.service.identificationtype;

import com.uco.apisolveit.domain.identificationtype.IdentificationType;
import com.uco.apisolveit.repository.identificationtype.IIdentificationType;
import com.uco.apisolveit.util.Constant;
import com.uco.apisolveit.util.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IdentificationTypeService {
    @Autowired
    private IIdentificationType identificationTypeRepository;

    public Flux<IdentificationType> get() {
        return identificationTypeRepository.findAll();
    }

    public Mono<IdentificationType> get(String identificationTypeDescription) {

        return identificationTypeRepository.findByDescription(identificationTypeDescription);
    }

    private void validationData(IdentificationType identificationType) {
        UtilString.requieresNoNullOrNoEmpty(identificationType.getDescription(), String.format(Constant.TXT_EXPECT_VALUE, identificationType.getDescription()));
    }
}
