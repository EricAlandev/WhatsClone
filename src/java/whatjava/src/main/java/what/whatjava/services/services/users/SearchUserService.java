package what.whatjava.services.services.users;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.ResponseRequest.SearchRequest;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class SearchUserService implements UseCase<SearchUserService.InputValues, SearchUserService.OutPutValues> {
        @Autowired
        UserRepository userRepository;

        @Autowired
        JwtService jwtService;

        @lombok.Value
        public static class InputValues implements UseCase.InputValues{
            SearchRequest searchRequest;
            String token;
        }

        @lombok.Value
        public static class OutPutValues implements UseCase.OutPutValues{
            List<EntityUser> searchResponse;
        }
        
        @Override
        public OutPutValues execute(InputValues input){
            jwtService.verifyToken(input.getToken());

            String name = input.getSearchRequest().getSearch();
            
            System.out.println("user values" + name);

            List<EntityUser> findedUsers = userRepository.findByNameContainingIgnoreCase(name);

            System.out.println("Finded Users values" + findedUsers);

            return new OutPutValues(findedUsers);
    }
}
