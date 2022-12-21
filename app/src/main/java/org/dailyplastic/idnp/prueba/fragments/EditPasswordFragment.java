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
import org.dailyplastic.idnp.prueba.model.Password;

import java.util.ArrayList;

public class EditPasswordFragment extends Fragment {

    EditText currentPass, newPass;
    ArrayList<Password> newRegisterPassword = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View fragviewPass = inflater.inflate(R.layout.fragment_edit_password, container, false);

        Button buttonSave = fragviewPass.findViewById(R.id.buttonSavePerfil);

        currentPass = (EditText) fragviewPass.findViewById(R.id.currentPassword);
        newPass = (EditText) fragviewPass.findViewById(R.id.newPassword);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            Password auxNewPassword;

            //password registrados
            public void initialize_Pass(){
                newRegisterPassword.add(new Password("123", "456"));
            }
            public boolean find_Pass(Password regPass){
                boolean exist=false;
                for (int i = 0; i < newRegisterPassword.size(); ++i){
                    if (regPass.newPass.compareTo(newRegisterPassword.get(i).currentPass)!=0)
                        return true;
                }
                return false;
            }

            @Override
            public void onClick(View v) {
                initialize_Pass();
                auxNewPassword = new Password(currentPass.getText().toString(), newPass.getText().toString());

                if(find_Pass(auxNewPassword)==true){
                    Log.d(TAG, "Password cambiado exitosamente "+newPass+"!" );


                    AlertDialog.Builder alertRegisterPass = new AlertDialog.Builder(v.getContext());
                    alertRegisterPass.setMessage(" Password cambiado exitosamente,  "+newPass.getText().toString())
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
        return fragviewPass;
    }

}