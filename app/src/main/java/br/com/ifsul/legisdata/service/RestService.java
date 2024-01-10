package br.com.ifsul.legisdata.service;

import br.com.ifsul.legisdata.response.deputado.ApiResponseDeputado;
import br.com.ifsul.legisdata.response.deputado.ApiResponseDeputadoDetalhado;
import br.com.ifsul.legisdata.response.partido.ApiResponsePartido;
import br.com.ifsul.legisdata.response.partido.ApiResponsePartidoDetalhado;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestService {
    @GET("partidos")
    Call<ApiResponsePartido> buscarPartidos();

    @GET("partidos/{id}")
    Call<ApiResponsePartidoDetalhado> detalharPartido(@Path("id")String id);

    @GET("deputados")
    Call<ApiResponseDeputado> buscarDeputados();

    @GET("deputados/{id}")
    Call<ApiResponseDeputadoDetalhado> detalharDeputado(@Path("id")String deputadoId);
}
