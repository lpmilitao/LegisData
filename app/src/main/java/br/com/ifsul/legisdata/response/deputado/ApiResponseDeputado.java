package br.com.ifsul.legisdata.response.deputado;

import java.util.List;

import br.com.ifsul.legisdata.response.LinksResponse;

public class ApiResponseDeputado {
    private List<DeputadoResponse> dados;
    private List<LinksResponse> links;

    public List<DeputadoResponse> getDados() {
        return dados;
    }

    public void setDados(List<DeputadoResponse> dados) {
        this.dados = dados;
    }

    public List<LinksResponse> getLinks() {
        return links;
    }

    public void setLinks(List<LinksResponse> links) {
        this.links = links;
    }
}
