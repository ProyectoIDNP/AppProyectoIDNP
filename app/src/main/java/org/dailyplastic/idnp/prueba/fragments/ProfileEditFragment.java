package org.dailyplastic.idnp.prueba.fragments;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.model.EditInformationUser;
import org.dailyplastic.idnp.prueba.model.Password;

import java.util.ArrayList;

public class ProfileEditFragment extends Fragment {

    EditText changeName, changeEmail;
    ArrayList<EditInformationUser> newRegisterProfile= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View fragviewPerfil = inflater.inflate(R.layout.fragment_profile_edit, container, false);

        Button buttonSaveProfile = fragviewPerfil.findViewById(R.id.buttonSavePerfil);

        changeName = (EditText) fragviewPerfil.findViewById(R.id.editName);
        changeEmail = (EditText) fragviewPerfil.findViewById(R.id.editEmail);

        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            EditInformationUser auxNewProfile;

            public void initialize_Prof(){
                newRegisterProfile.add(new EditInformationUser("Ana", "ana@gmail.com"));
            }
            public boolean find_Prof(EditInformationUser regProf){
                boolean exist=false;
                for (int i = 0; i < newRegisterProfile.size(); ++i){
                    if (regProf.changeName.compareTo(newRegisterProfile.get(i).changeName)!=0 &&
                            regProf.changeEmail.compareTo(newRegisterProfile.get(i).changeEmail)!=0)
                        return true;
                }
                return false;
            }

            @Override
            public void onClick(View v) {
                initialize_Prof();
                auxNewProfile = new EditInformationUser(changeName.getText().toString(), changeEmail.getText().toString());

                if(find_Prof(auxNewProfile)==true){
                    Log.d(TAG, "Nombre cambiado exitosamente, "+changeName+"!" );


                    AlertDialog.Builder alertRegisterPass = new AlertDialog.Builder(v.getContext());
                    alertRegisterPass.setMessage(" Nombre cambiado exitosamente,  "+changeName.getText().toString())
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int witch) {
                                    dialogInterface.cancel();
                                }
                            });


                    AlertDialog tituloMain = alertRegisterPass.create();
                    tituloMain.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface dialogInterface) {
                            tituloMain.getWindow().setBackgroundDrawableResource(R.color.green_primary);
                            tituloMain.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(android.R.color.white));
                        }
                    });
                    tituloMain.setTitle("Daily Plastic");
                    tituloMain.show();


                }
                else{
                    Log.d(TAG, "El password no se puede cambiar !");
                }
            }
        });
        return fragviewPerfil;
    }



}