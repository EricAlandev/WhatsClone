package what.whatjava.resources;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import what.whatjava.dtos.DomainListDTO;
import what.whatjava.dtos.UserResponseDTO;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/options") 
@CrossOrigin(origins = "http://localhost:3000")
public interface ConfigurationsResource {
    
    @GetMapping
    public List<DomainListDTO> fetchConfigurationsList();

    @PutMapping("/account")
    public CompletableFuture<String> changeData(
    @RequestHeader("Authorization") String token, @RequestBody UserResponseDTO userData);
}
