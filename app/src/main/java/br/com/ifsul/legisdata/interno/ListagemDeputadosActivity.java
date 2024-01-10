package br.com.ifsul.legisdata.interno;

import static br.com.ifsul.legisdata.interno.ListagemPartidosActivity.URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import br.com.ifsul.legisdata.R;
import br.com.ifsul.legisdata.response.deputado.ApiResponseDeputado;
import br.com.ifsul.legisdata.response.deputado.DeputadoResponse;
import br.com.ifsul.legisdata.service.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListagemDeputadosActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Retrofit retrofit;
    ListView listaDeputados;
    ArrayAdapter<String> adapter;
    List<DeputadoResponse> deputados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_deputados);

        ajustarMenu();

        listaDeputados = findViewById(R.id.listaDeputados);
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        listaDeputados.setAdapter(adapter);

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        consultar();

        listaDeputados.setOnItemClickListener((adapterView, view, position, id) -> {
            String deputadoId = deputados.get(position).getId();

            Intent intent = new Intent(ListagemDeputadosActivity.this, DeputadoActivity.class);
            intent.putExtra("DEPUTADO_ID", deputadoId);
            startActivity(intent);
        });
    }

    private void consultar() {
        RestService restService = retrofit.create(RestService.class);

        Call<ApiResponseDeputado> call = restService.buscarDeputados();

        call.enqueue(new Callback<ApiResponseDeputado>() {
            @Override
            public void onResponse(Call<ApiResponseDeputado> call, Response<ApiResponseDeputado> response) {
                if (response.isSuccessful()) {
                    ApiResponseDeputado responseDeputado = response.body();
                    deputados = responseDeputado.getDados();

                    adapter.clear();
                    deputados.forEach(deputado -> {
                        adapter.add(deputado.getNome());
                    });
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseDeputado> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Ocorreu um erro ao tentar consultar os deputados: "
                        + t.getMessage(), Toast.LENGTH_LONG).show();
                deputados = new ArrayList<>();
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