package org.dailyplastic.idnp.prueba.fragments;
import static android.content.ContentValues.TAG;

import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.dto.UserDto;
import org.dailyplastic.idnp.prueba.interfaces.UserService;
import org.dailyplastic.idnp.prueba.model.User;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.activities.FragmentControlActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {
    EditText username, password;

    UserService userService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragview = inflater.inflate(R.layout.fragment_login, container, false);
        Button buttonLogin = fragview.findViewById(R.id.loginButtonLogin);
        username = (EditText) fragview.findViewById(R.id.registerName);
        password = (EditText) fragview.findViewById(R.id.loginPassword);




        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aqui es donde inicio sesion
                User user = new User();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                logInUser(user);
            }

        });

        return fragview;
    }

    public void logInUser(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
        Call<UserDto> call = userService.logIn(user);
        call.enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    Toast.makeText(getContext(), "Inicio de sesión incorrecto", Toast.LENGTH_LONG).show();
                    return;
                }
                UserDto userReceived = response.body();
                /***
                 * AQUI ES DONDE SE DEBE ENVIAR O ALMACENAR EL userReceived DE FORMA GLOBAL
                 * userReceived  ES LA RESPUESTA QUE CONTIENE LOS DATOS DEL USUARIO
                 */
                

                AlertDialog.Builder alertLogin = new AlertDialog.Builder(getContext());
                alertLogin.setMessage("Bienvenido "+userReceived.getUser().getUsername())
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int witch) {
                                dialogInterface.cancel();
                                Intent intentMainActivity = new Intent(getActivity(), FragmentControlActivity.class);
                                startActivity(intentMainActivity);
                            }
                        });


                AlertDialog titulo = alertLogin.create();
                titulo.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        titulo.getWindow().setBackgroundDrawableResource(R.color.green_primary);
                        titulo.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(android.R.color.white));
                    }
                });
                titulo.setTitle("Daily Plastic");
                titulo.show();


                //Toast.makeText(getContext(), "Se registro con éxito", Toast.LENGTH_LONG).show();
                //getParentFragmentManager().popBackStack();

            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}
