package br.com.ifsul.legisdata.response.deputado;

import java.util.List;

import br.com.ifsul.legisdata.response.LinksResponse;

public class ApiResponseDeputadoDetalhado {

    private DeputadoDetalhadoResponse dados;

    private List<LinksResponse> links;

    public DeputadoDetalhadoResponse getDados() {
        return dados;
    }

    public void setDados(DeputadoDetalhadoResponse dados) {
        this.dados = dados;
    }

    public List<LinksResponse> getLinks() {
        return links;
    }

    public void setLinks(List<LinksResponse> links) {
        this.links = links;
    }
}
