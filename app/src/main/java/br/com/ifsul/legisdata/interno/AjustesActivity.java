package br.com.ifsul.legisdata.interno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import br.com.ifsul.legisdata.R;
import br.com.ifsul.legisdata.externo.HomeActivity;

public class AjustesActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button botaoSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        ajustarMenu();

        botaoSair = findViewById(R.id.botaoSair);
        botaoSair.setText("Sair");
        botaoSair.setOnClickListener(view -> sair());
    }

    public void sair(){
        FirebaseAuth.getInstance().signOut();

        Toast.makeText(getApplicationContext(), "Logout realizado com sucesso.",
                Toast.LENGTH_LONG).show();

        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(i);
    }

    public void ajustarMenu(){
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        MenuItem selectedItem = bottomNavigationView.getMenu().findItem(R.id.ajustes);
        selectedItem.setChecked(true);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.ajustes) {
                return true;
            } else if (item.getItemId() == R.id.deputados) {
                startActivity(new Intent(getApplicationContext(), ListagemDeputadosActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (item.getItemId() == R.id.partidos) {
                startActivity(new Intent(getApplicationContext(), ListagemPartidosActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }
}