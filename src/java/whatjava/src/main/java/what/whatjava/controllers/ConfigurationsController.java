package what.whatjava.controllers;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import what.whatjava.dtos.DomainListDTO;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.resources.ConfigurationsResource;
import what.whatjava.services.ResponseRequest.AccountChangingResponse;
import what.whatjava.services.services.ServiceExecute;
import what.whatjava.services.services.Configuration.AccountService;
import what.whatjava.services.services.Configuration.ConfigurationsService;
import what.whatjava.services.services.Jwt.JwtService;


@RestController 
@CrossOrigin()
public class ConfigurationsController implements ConfigurationsResource {
    
    @Autowired
    private ConfigurationsService  configurationsService; 

    @Autowired
    private AccountService  accountService; 

    @Autowired 
    JwtService jwtService;

    @Override
    public List<DomainListDTO> fetchConfigurationsList() {

        List<DomainListDTO> callService = configurationsService.findListConfiguration();

        System.out.println("THis is the return of callService" + callService);

        return callService;
    }

    @Override
    public CompletableFuture<String> changeData(String token, UserResponseDTO userData){
        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(
            accountService, 
            new AccountService.InputValues(cleanToken, userData), 
            (output) -> AccountChangingResponse.from(output.getMessage(), output.getUpdatedOptions())
        );
    }
}