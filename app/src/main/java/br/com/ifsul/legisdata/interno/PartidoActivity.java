package br.com.ifsul.legisdata.interno;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import br.com.ifsul.legisdata.R;
import br.com.ifsul.legisdata.response.partido.ApiResponsePartidoDetalhado;
import br.com.ifsul.legisdata.service.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PartidoActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    String partidoId;
    Retrofit retrofit;
    ImageView logoPartido;
    TextView nomePartido, siglaPartido, situacaoPartido, totalMembros, liderPartido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido);
        ajustarMenu();
        partidoId = getIntent().getStringExtra("PARTIDO_ID");
        logoPartido = findViewById(R.id.logoPartido);
        nomePartido = findViewById(R.id.nomePartido);
        siglaPartido = findViewById(R.id.siglaPartido);
        situacaoPartido = findViewById(R.id.situacaoPartido);
        totalMembros = findViewById(R.id.totalMembros);
        liderPartido = findViewById(R.id.liderPartido);

        nomePartido.setText("");
        siglaPartido.setText("");
        situacaoPartido.setText("");
        totalMembros.setText("");
        liderPartido.setText("");

        retrofit = new Retrofit.Builder()
                .baseUrl(ListagemPartidosActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        consultar();
    }

    public void consultar(){
        RestService restService = retrofit.create(RestService.class);

        Call<ApiResponsePartidoDetalhado> call = restService.detalharPartido(partidoId);

        call.enqueue(new Callback<ApiResponsePartidoDetalhado>() {
            @Override
            public void onResponse(Call<ApiResponsePartidoDetalhado> call,
                                   Response<ApiResponsePartidoDetalhado> response) {
                if (response.isSuccessful()) {
                    ApiResponsePartidoDetalhado responsePartido = response.body();
                    Picasso.get().load(responsePartido.getDados().getUrlLogo()).into(logoPartido);
                    nomePartido.setText(responsePartido.getDados().getNome());
                    siglaPartido.setText(responsePartido.getDados().getSigla());
                    situacaoPartido.setText(responsePartido.getDados().getStatus().getSituacao());
                    totalMembros.setText(responsePartido.getDados().getStatus().getTotalMembros() + " membros");
                    liderPartido.setText("Líder: " + responsePartido.getDados().getStatus().getLider().getNome());
                }
            }

            @Override
            public void onFailure(Call<ApiResponsePartidoDetalhado> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Ocorreu um erro ao tentar consultar o partido: "
                                + t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println(t.getMessage());
            }
        });
    }

    public void ajustarMenu(){
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        MenuItem selectedItem = bottomNavigationView.getMenu().findItem(R.id.partidos);
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