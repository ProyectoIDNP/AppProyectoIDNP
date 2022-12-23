package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.dto.ConsumptionDto;
import org.dailyplastic.idnp.prueba.interfaces.ConsumptionService;
import org.dailyplastic.idnp.prueba.interfaces.RegisterService;
import org.dailyplastic.idnp.prueba.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragment extends Fragment {

    TextView usernameRegister;
    TextView emailRegister;
    TextView passwordRegister;
    CheckBox conditionsRegister;
    Button buttonRegister;

    RegisterService registerService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View registerFragmentView = inflater.inflate(R.layout.fragment_register, container, false);

        usernameRegister = registerFragmentView.findViewById(R.id.editTextRegisterName);
        emailRegister = registerFragmentView.findViewById(R.id.editTextTextRegisterEmail);
        passwordRegister = registerFragmentView.findViewById(R.id.editTextTextRegisterPassword);
        conditionsRegister = registerFragmentView.findViewById(R.id.checkBoxRegisterConditions);
        buttonRegister = registerFragmentView.findViewById(R.id.buttonRegisterUser);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User userSend = new User();
                userSend.setUsername(usernameRegister.getText().toString());
                userSend.setEmail(emailRegister.getText().toString());
                userSend.setPassword(passwordRegister.getText().toString());

                if(conditionsRegister.isChecked()) {
                    create(userSend);
                    //returnToLoginFragment();
                } else {
                    Toast.makeText(getActivity(), "Acepte las condiciones para continuar", Toast.LENGTH_LONG).show();
                }


            }
        });



        return registerFragmentView;
    }

    public void create(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        registerService = retrofit.create(RegisterService.class);
        Call<User> call = registerService.create(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                User userReceived = response.body();
                Toast.makeText(getContext(), "Se registro con éxito", Toast.LENGTH_LONG).show();
                getParentFragmentManager().popBackStack();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    public void returnToLoginFragment() {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        LoginFragment fragment1 = new LoginFragment();
        transaction.replace(R.id.mainFrame, fragment1);
        transaction.commit();

    }
}