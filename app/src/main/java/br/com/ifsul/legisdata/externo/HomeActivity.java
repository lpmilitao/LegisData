package br.com.ifsul.legisdata.externo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.ifsul.legisdata.R;

public class HomeActivity extends AppCompatActivity {
    Button login, cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        login = findViewById(R.id.botaoLogin);
        cadastro = findViewById(R.id.botaoCadastro);

        login.setOnClickListener(view -> acessarLogin(view));
        cadastro.setOnClickListener(view -> acessarCadastro(view));

        login.setText("Login");
        cadastro.setText("Cadastro");
    }

    public void acessarLogin(View v){
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }

    public void acessarCadastro(View v){
        Intent i = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(i);
    }
}