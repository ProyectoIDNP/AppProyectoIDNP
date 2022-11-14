package org.dailyplastic.idnp.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.dailyplastic.idnp.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CategoriasFragment categoriasFragment = new CategoriasFragment();
    EstadisticasFragment estadisticasFragment = new EstadisticasFragment();
    MiConsumoFragment miConsumoFragment = new MiConsumoFragment();
    PlasticoFragment plasticoFragment = new PlasticoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,categoriasFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.categorias:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,categoriasFragment).commit();
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