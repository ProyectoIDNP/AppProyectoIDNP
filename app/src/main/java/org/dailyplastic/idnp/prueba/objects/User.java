package org.dailyplastic.idnp.prueba.objects;

public class User {
    public String emailOrName;
    public String password;

    public User(String nameUser, String password){
        this.emailOrName = nameUser;
        this.password = password;
    }

    @Override
    public String toString(){

        return this.emailOrName;
    }
}