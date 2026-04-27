package what.whatjava.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import what.whatjava.entitys.users.EntityUser;
import what.whatjava.entitys.users.EntityUserFriend;
 
@Repository
public interface UserFriendsRepository extends JpaRepository<EntityUserFriend, Long> {

    List<EntityUserFriend> findByUserID(EntityUser user);

    Optional<EntityUserFriend> findByUserIDAndFriendsId(EntityUser user , EntityUser user2);

}
