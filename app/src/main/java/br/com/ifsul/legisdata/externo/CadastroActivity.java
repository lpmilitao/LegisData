package br.com.ifsul.legisdata.externo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.ifsul.legisdata.R;
import br.com.ifsul.legisdata.interno.ListagemPartidosActivity;

public class CadastroActivity extends AppCompatActivity {

    Button cadastrar;
    ImageView voltar;
    EditText emailValor, senhaValor;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        voltar = findViewById(R.id.voltarFromCadastro);
        cadastrar = findViewById(R.id.fazerCadastro);
        emailValor = findViewById(R.id.campoEmailCadastro);
        senhaValor = findViewById(R.id.campoSenhaCadastro);

        emailValor.setText("");
        emailValor.setHint("fulano@gmail.com");
        senhaValor.setText("");
        senhaValor.setHint("********");

        voltar.setOnClickListener(view -> voltarParaHome());
        cadastrar.setOnClickListener(view -> cadastrar());
    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), ListagemPartidosActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void voltarParaHome() {
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(i);
    }

    public void cadastrar() {
        String email = String.valueOf(emailValor.getText());
        String password = String.valueOf(senhaValor.getText());

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(CadastroActivity.this, "Por favor, digite seu email",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(CadastroActivity.this, "Por favor, digite sua senha",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(CadastroActivity.this, "Conta criada com sucesso.",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(CadastroActivity.this, "Aunteticação falhou.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}