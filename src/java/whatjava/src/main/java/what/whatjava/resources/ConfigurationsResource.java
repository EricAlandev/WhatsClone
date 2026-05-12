package what.whatjava.resources;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import what.whatjava.dtos.DomainListDTO;

@RestController
@RequestMapping("/options") 
@CrossOrigin(origins = "http://localhost:3000")
public interface ConfigurationsResource {
    
    @GetMapping
    public List<DomainListDTO> fetchConfigurationsList();
}
