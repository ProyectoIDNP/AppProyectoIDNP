package org.dailyplastic.idnp.prueba.model;

public class Password {
    public String currentPass;
    public String newPass;

    public Password(String currentPass, String newPass){
        this.currentPass = currentPass;
        this.newPass = newPass;
    }

    @Override
    public String toString(){

        return this.newPass;
    }
}
