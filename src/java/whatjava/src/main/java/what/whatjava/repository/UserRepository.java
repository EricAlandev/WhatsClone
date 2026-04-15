package what.whatjava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import what.whatjava.entitys.users.EntityUser;

@Repository
public interface UserRepository extends JpaRepository<EntityUser, Long> {
    Optional<EntityUser> findByEmail(String email);
}
