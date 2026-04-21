
package what.whatjava.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import what.whatjava.dtos.DomainListDTO;
import what.whatjava.entitys.domains.EntityDomainLists;
import what.whatjava.entitys.domains.EntityDomainListsValues;
import what.whatjava.repository.ConfigValueRepository;
import what.whatjava.repository.ConfigurationsRepository;

@Service
public class ConfigurationsService {
    
    @Autowired
    private ConfigurationsRepository configurationsRepository; 

    @Autowired
    private ConfigValueRepository configValueRepository; 

    public List<DomainListDTO> findListConfiguration() {

        Optional<EntityDomainLists> typeList = configurationsRepository.findBynamelist("listConfiguration");

        if(typeList == null || typeList.isEmpty()){
            throw new RuntimeException("Type list not found");
        }

        EntityDomainLists typeFinded = typeList.get();

        if(typeFinded.getCode() == null || typeFinded.getCode() < 0){
            throw new RuntimeException("Dosn't exist code");
        }

        List<EntityDomainListsValues> listValues = configValueRepository.findByCode(typeFinded.getCode());

        if(listValues.size() == 0){
            List<DomainListDTO> emptyList = new ArrayList<>();;
            
           return emptyList;
        }

        return listValues.stream()
               .map(list -> new DomainListDTO(
                list.getId(),
                list.getNamelist(),
                list.getDescription(),
                list.getImage_url(),
                null
                )).toList();
        }
}