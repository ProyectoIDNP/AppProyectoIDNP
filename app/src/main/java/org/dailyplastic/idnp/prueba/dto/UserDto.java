package org.dailyplastic.idnp.prueba.dto;

import org.dailyplastic.idnp.prueba.model.User;

public class UserDto {
    String token;
    User user;

    public UserDto() {

    }

    public UserDto(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
