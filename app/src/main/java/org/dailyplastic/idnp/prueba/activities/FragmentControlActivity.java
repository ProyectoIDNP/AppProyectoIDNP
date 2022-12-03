package org.dailyplastic.idnp.prueba.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.fragments.ProfileFragment;
import org.dailyplastic.idnp.prueba.fragments.StatisticsFragment;
import org.dailyplastic.idnp.prueba.fragments.MyPlasticConsumptionFragment;
import org.dailyplastic.idnp.prueba.fragments.PlasticsFragment;

public class FragmentControlActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ProfileFragment profileFragment = new ProfileFragment();
    StatisticsFragment estadisticasFragment = new StatisticsFragment();
    MyPlasticConsumptionFragment miConsumoFragment = new MyPlasticConsumptionFragment();
    PlasticsFragment plasticoFragment = new PlasticsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_control);
        bottomNavigationView  = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,plasticoFragment).commit();
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.green_primary));

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.perfil:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                        return true;
                    case R.id.estadisticas:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,estadisticasFragment).commit();
                        return true;
                    case R.id.miConsumo:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,miConsumoFragment).commit();
                        return true;

                    case R.id.plasticos:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,plasticoFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }
}