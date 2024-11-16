package server.Models.DTO;

import server.Models.Entities.User;

public class UserDTO {

    private int id;


    public UserDTO(User user) {
        this.id = user.getId();
    }

    public int getId() {
        return id;
    }

}