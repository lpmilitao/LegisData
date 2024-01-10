package br.com.ifsul.legisdata.interno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import br.com.ifsul.legisdata.R;
import br.com.ifsul.legisdata.response.deputado.ApiResponseDeputadoDetalhado;
import br.com.ifsul.legisdata.service.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeputadoActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView nomeDeputado, nascimento, emailDeputado;
    Retrofit retrofit;
    String deputadoId;
    ImageView fotoDeputado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputado);

        ajustarMenu();

        deputadoId = getIntent().getStringExtra("DEPUTADO_ID");

        nomeDeputado = findViewById(R.id.nomeDeputado);
        nascimento = findViewById(R.id.nascimento);
        fotoDeputado = findViewById(R.id.fotoDeputado);
        emailDeputado = findViewById(R.id.emailDeputado);

        nomeDeputado.setText("");
        nascimento.setText("");
        emailDeputado.setText("");

        retrofit = new Retrofit.Builder()
                .baseUrl(ListagemPartidosActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        consultar();
    }

    public void consultar(){
        RestService restService = retrofit.create(RestService.class);

        Call<ApiResponseDeputadoDetalhado> call = restService.detalharDeputado(deputadoId);

        call.enqueue(new Callback<ApiResponseDeputadoDetalhado>() {
            @Override
            public void onResponse(Call<ApiResponseDeputadoDetalhado> call,
                                   Response<ApiResponseDeputadoDetalhado> response) {
                if (response.isSuccessful()) {
                    ApiResponseDeputadoDetalhado responseDeputado = response.body();

                    nomeDeputado.setText(responseDeputado.getDados().getNomeCivil());

                    nascimento.setText(responseDeputado.getDados().getMunicipioNascimento() +
                            " - " + responseDeputado.getDados().getUfNascimento());

                    emailDeputado.setText(responseDeputado.getDados().getUltimoStatus()
                            .getGabinete().getEmail());

                    Picasso.get().load(responseDeputado.getDados().getUltimoStatus().getUrlFoto())
                            .into(fotoDeputado);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseDeputadoDetalhado> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Ocorreu um erro ao tentar consultar o deputado: "
                                + t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println(t.getMessage());
            }
        });
    }

    public void ajustarMenu(){
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        MenuItem selectedItem = bottomNavigationView.getMenu().findItem(R.id.deputados);
        selectedItem.setChecked(true);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.ajustes) {
                startActivity(new Intent(getApplicationContext(), AjustesActivity.class));
                overridePendingTransition(0, 0);
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