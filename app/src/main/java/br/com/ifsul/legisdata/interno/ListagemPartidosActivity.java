package br.com.ifsul.legisdata.interno;

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
import br.com.ifsul.legisdata.response.partido.ApiResponsePartido;
import br.com.ifsul.legisdata.response.partido.PartidoResponse;
import br.com.ifsul.legisdata.service.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListagemPartidosActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Retrofit retrofit;
    ListView listaPartidos;
    ArrayAdapter<String> adapter;
    List<PartidoResponse> partidos = new ArrayList<>();
    public static final String URL = "https://dadosabertos.camara.leg.br/api/v2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_partidos);
        ajustarMenu();

        listaPartidos = findViewById(R.id.listaPartidos);
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        listaPartidos.setAdapter(adapter);

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        consultar();

        listaPartidos.setOnItemClickListener((adapterView, view, position, id) -> {
            String partidoId = partidos.get(position).getId();

            Intent intent = new Intent(ListagemPartidosActivity.this, PartidoActivity.class);
            intent.putExtra("PARTIDO_ID", partidoId);
            startActivity(intent);
        });
    }

    private void consultar() {
        RestService restService = retrofit.create(RestService.class);

        Call<ApiResponsePartido> call = restService.buscarPartidos();

        call.enqueue(new Callback<ApiResponsePartido>() {
            @Override
            public void onResponse(Call<ApiResponsePartido> call, Response<ApiResponsePartido> response) {
                if (response.isSuccessful()) {
                    ApiResponsePartido responsePartido = response.body();
                    partidos = responsePartido.getDados();

                    adapter.clear();

                    partidos.forEach(partido -> {
                        adapter.add(partido.getNome());
                    });
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponsePartido> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Ocorreu um erro ao tentar consultar os partidos: "
                        + t.getMessage(), Toast.LENGTH_LONG).show();
                partidos = new ArrayList<>();
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
                return true;
            }
            return false;
        });
    }
}