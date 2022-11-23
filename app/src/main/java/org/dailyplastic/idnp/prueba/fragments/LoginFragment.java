package org.dailyplastic.idnp.prueba.fragments;
import static android.content.ContentValues.TAG;
import org.dailyplastic.idnp.prueba.objects.User;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.activities.FragmentControlActivity;

import java.util.ArrayList;

public class LoginFragment extends Fragment {
    EditText emailOrName, password;
    ArrayList<User> Users = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragview = inflater.inflate(R.layout.fragment_login, container, false);

        Button buttonLogin = fragview.findViewById(R.id.loginButtonLogin);
        emailOrName = (EditText) fragview.findViewById(R.id.registerName);
        password = (EditText) fragview.findViewById(R.id.loginPassword);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            User auxUser;

            public void initialize_Users(){
                Users.add(new User("admin","1234"));
                Users.add(new User("user","1234"));
            }
            public boolean find_User(User use){
                boolean exist=false;
                for (int i = 0; i < Users.size(); ++i){
                    if (use.emailOrName.equals( Users.get(i).emailOrName) && use.password.equals( Users.get(i).password))
                        return true;
                }
                return false;
            }

            @Override
            public void onClick(View v) {
                initialize_Users();
                auxUser = new User(emailOrName.getText().toString(), password.getText().toString());
                if(find_User(auxUser)==true){
                    Log.d(TAG, "Inicio de sesion realizado con éxito "+auxUser+"!");
                    Intent intentMainActivity = new Intent(getActivity(), FragmentControlActivity.class);
                    startActivity(intentMainActivity);
                }
                else{
                    Log.d(TAG, "Usuario no existe o la contraseña es incorrecta !");
                    emailOrName.setText("");
                    password.setText("");
                }
            }
        });

        return fragview;
    }
}
