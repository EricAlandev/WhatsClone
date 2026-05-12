package what.whatjava.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import what.whatjava.dtos.DomainListDTO;
import what.whatjava.resources.ConfigurationsResource;
import what.whatjava.services.services.Configuration.ConfigurationsService;


@RestController 
@RequestMapping("/options") 
@CrossOrigin(origins = "http://localhost:3000")
public class ConfigurationsController implements ConfigurationsResource {
    
    @Autowired
    private ConfigurationsService  configurationsService; 

    @Override
    public List<DomainListDTO> fetchConfigurationsList() {

        List<DomainListDTO> callService = configurationsService.findListConfiguration();

        System.out.println("THis is the return of callService" + callService);

        return callService;
    }
}