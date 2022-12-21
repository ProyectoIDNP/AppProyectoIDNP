package org.dailyplastic.idnp.prueba.model;

public class EditInformationUser {
    public String changeName;
    public String changeEmail;

    public EditInformationUser(String changeName, String changeEmail){
        this.changeName = changeName;
        this.changeEmail = changeEmail;
    }

    @Override
    public String toString(){

        return this.changeName;
    }
}
