package what.whatjava.services.ResponseRequest;

import what.whatjava.entitys.users.EntityUser;

public class AddFriendResponse {
    
    public static String from(EntityUser userToAdd, EntityUser userAdded){

        String nameUserWhoAdd = userToAdd.getName();
        String nameUserAdded = userAdded.getName();



        return "The user " + nameUserAdded + " Added the user " + nameUserWhoAdd;
    }
}
