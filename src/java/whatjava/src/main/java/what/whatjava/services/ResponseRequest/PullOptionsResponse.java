package what.whatjava.services.ResponseRequest;

import java.util.List;

import what.whatjava.dtos.DomainListDTO;
import what.whatjava.entitys.domains.EntityDomainListsValues;

public class PullOptionsResponse {
    
    public static List<DomainListDTO> from(List<EntityDomainListsValues> options){

        return options.stream()
            .map(op -> DomainListDTO.builder()
                .nameList(op.getNamelist())
                .imageUrl(op.getImage_url())
                .build()
            ).toList();

    }
}
