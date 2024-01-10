package br.com.ifsul.legisdata.externo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.ifsul.legisdata.R;
import br.com.ifsul.legisdata.interno.ListagemPartidosActivity;

public class LoginActivity extends AppCompatActivity {
    Button logar;
    ImageView voltar;
    TextView titulo, email, senha;
    EditText emailValor, senhaValor;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        titulo = findViewById(R.id.tituloLogin);
        email = findViewById(R.id.emailTitulo);
        senha = findViewById(R.id.senhaTitulo);
        emailValor = findViewById(R.id.campoEmail);
        senhaValor = findViewById(R.id.campoSenha);
        logar = findViewById(R.id.fazerLogin);
        voltar = findViewById(R.id.voltarHome);

        titulo.setText("Faça login");
        email.setText("Email");
        senha.setText("Senha");
        emailValor.setText("");
        senhaValor.setText("");

        voltar.setOnClickListener(view -> voltarParaHome());
        logar.setOnClickListener(view -> fazerLogin());
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), ListagemPartidosActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void voltarParaHome() {
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(i);
    }

    public void fazerLogin() {
        String email = String.valueOf(emailValor.getText());
        String password = String.valueOf(senhaValor.getText());

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(LoginActivity.this, "Digite seu e-mail", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Digite sua senha", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login realizado com sucesso.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ListagemPartidosActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Autenticação falhou.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}