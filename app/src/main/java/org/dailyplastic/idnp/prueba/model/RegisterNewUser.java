package org.dailyplastic.idnp.prueba.model;

public class RegisterNewUser {
    public String nameRegister;
    public String emailRegister;
    public String passwordRegister;

    public RegisterNewUser(String nameRegister, String emailRegister, String registerPassword){
        this.nameRegister = nameRegister;
        this.emailRegister = emailRegister;
        this.passwordRegister = registerPassword;
    }

    @Override
    public String toString(){

        return this.nameRegister;
    }
}
