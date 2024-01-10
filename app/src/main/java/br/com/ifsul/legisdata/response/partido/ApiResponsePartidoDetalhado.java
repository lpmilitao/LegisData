package br.com.ifsul.legisdata.response.partido;

import java.util.List;

import br.com.ifsul.legisdata.response.LinksResponse;

public class ApiResponsePartidoDetalhado {
    private PartidoDetalhadoResponse dados;
    private List<LinksResponse> links;

    public PartidoDetalhadoResponse getDados() {
        return dados;
    }

    public void setDados(PartidoDetalhadoResponse dados) {
        this.dados = dados;
    }

    public List<LinksResponse> getLinks() {
        return links;
    }

    public void setLinks(List<LinksResponse> links) {
        this.links = links;
    }
}
