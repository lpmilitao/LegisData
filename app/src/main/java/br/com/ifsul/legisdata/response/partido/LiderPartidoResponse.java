package br.com.ifsul.legisdata.response.partido;

public class LiderPartidoResponse {
    private String uri;
    private String nome;
    private String siglaPartido;
    private String uriPartido;
    private String uf;
    private String idLegislaturaidLegislatura;
    private String urlFoto;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public void setSiglaPartido(String siglaPartido) {
        this.siglaPartido = siglaPartido;
    }

    public String getUriPartido() {
        return uriPartido;
    }

    public void setUriPartido(String uriPartido) {
        this.uriPartido = uriPartido;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIdLegislaturaidLegislatura() {
        return idLegislaturaidLegislatura;
    }

    public void setIdLegislaturaidLegislatura(String idLegislaturaidLegislatura) {
        this.idLegislaturaidLegislatura = idLegislaturaidLegislatura;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
