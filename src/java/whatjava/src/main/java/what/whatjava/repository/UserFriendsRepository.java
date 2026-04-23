package what.whatjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import what.whatjava.entitys.users.EntityUserFriend;

@Repository
public interface UserFriendsRepository extends JpaRepository<EntityUserFriend, Long> {

}
