package what.whatjava.services.services.Chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import lombok.Value;
import what.whatjava.entitys.domains.EntityDomainLists;
import what.whatjava.entitys.domains.EntityDomainListsValues;
import what.whatjava.repository.ConfigValueRepository;
import what.whatjava.repository.ConfigurationsRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class PullAllOptionsService implements UseCase<PullAllOptionsService.InputValues, PullAllOptionsService.OutPutValues> {

    @Autowired
    ConfigurationsRepository configurationsRepository;

    @Autowired
    ConfigValueRepository configValueRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Value
    public static class InputValues implements UseCase.InputValues{
        String token;
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues{
        List<EntityDomainListsValues> options;
    }

    @Override
    public OutPutValues execute(InputValues input){

        String token = input.getToken();

        Claims claims = jwtService.verifyToken(token);
        Long idUser = claims.get("id", Long.class);

        userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("user dosn't exist"));

        EntityDomainLists domainValue = configurationsRepository.findBynamelist("account").orElseThrow(() -> new RuntimeException("Error trying to fetch the domainValue"));

        List<EntityDomainListsValues> options = new ArrayList<>();

        if(domainValue != null){
            options = configValueRepository.findByCode(domainValue.getCode());
        }

        return new OutPutValues(options);    
    }
}
