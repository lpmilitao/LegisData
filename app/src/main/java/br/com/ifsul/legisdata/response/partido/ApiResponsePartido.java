package br.com.ifsul.legisdata.response.partido;

import java.util.ArrayList;
import java.util.List;

import br.com.ifsul.legisdata.response.LinksResponse;

public class ApiResponsePartido {
    private List<PartidoResponse> dados = new ArrayList<>();
    private List<LinksResponse> links = new ArrayList<>();

    public List<PartidoResponse> getDados() {
        return dados;
    }

    public void setDados(List<PartidoResponse> dados) {
        this.dados = dados;
    }

    public List<LinksResponse> getLinks() {
        return links;
    }

    public void setLinks(List<LinksResponse> links) {
        this.links = links;
    }
}
