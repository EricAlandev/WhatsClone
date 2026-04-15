package what.whatjava.services.loginAndRegister; 

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationTime;
    
    public EntityUser Login(EntityUser user){

        try {
            if(user.getEmail() != null){

                //try the search
                Optional<EntityUser> userFromDatabase = userRepository.findByEmail(user.getEmail());

                //throw if don't find
                if(userFromDatabase == null || userFromDatabase.isEmpty()){
                    throw new Error("User dosn't exists");
                }

                EntityUser userFinded = userFromDatabase.get();
                //compare the passwords
                if(userFinded.getPassword() != user.getPassword()){
                    throw new Error("Password its wrong");
                }  

                
                

            }
        } catch (Exception e) {
            
        }

        EntityUser save = userRepository.save(user);

        return save;
    }
}
