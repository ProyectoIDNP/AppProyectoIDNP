package org.dailyplastic.idnp.prueba.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.activities.MainMenuActivity;
import org.dailyplastic.idnp.prueba.dto.UserDto;

public class ProfileFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View profileFragment =  inflater.inflate(R.layout.fragment_profile, container, false);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        Button buttonProfile = profileFragment.findViewById(R.id.button_edit_profile);
        Button buttonPassword = profileFragment.findViewById(R.id.button_change_password);
        Button buttonExit = profileFragment.findViewById(R.id.button_exit);
        TextView textViewNameUserLogin = profileFragment.findViewById(R.id.textViewNameUserLogin);


        //Recuperacion del id del usuario logueado
        SharedPreferences mPrefs = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("userLoggedIn", "");
        UserDto userObj = gson.fromJson(json, UserDto.class);

        textViewNameUserLogin.setText(userObj.getUser().getUsername());


        buttonProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ProfileEditFragment fragment_profile_edit = new ProfileEditFragment();
                FragmentTransaction transaction20 = getParentFragmentManager().beginTransaction();
                transaction20.replace(R.id.container,fragment_profile_edit);
                transaction20.addToBackStack(null).commit();
            }
        });

        buttonPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditPasswordFragment editPasswordFragment = new EditPasswordFragment();
                FragmentTransaction transaction10 = getParentFragmentManager().beginTransaction();
                transaction10.replace(R.id.container, editPasswordFragment);
                transaction10.addToBackStack(null).commit();
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intentMainActivity = new Intent(getActivity(), MainMenuActivity.class);
                startActivity(intentMainActivity);
            }
        });


        return profileFragment;
    }
}