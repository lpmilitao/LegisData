package br.com.ifsul.legisdata.response.partido;

public class PartidoDetalhadoResponse {
    private String id;
    private String sigla;
    private String nome;
    private String uri;
    private StatusPartidoResponse status;
    private String numeroEleitoral;
    private String urlLogo;
    private String urlWebSite;
    private String urlFacebook;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public StatusPartidoResponse getStatus() {
        return status;
    }

    public void setStatus(StatusPartidoResponse status) {
        this.status = status;
    }

    public String getNumeroEleitoral() {
        return numeroEleitoral;
    }

    public void setNumeroEleitoral(String numeroEleitoral) {
        this.numeroEleitoral = numeroEleitoral;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getUrlWebSite() {
        return urlWebSite;
    }

    public void setUrlWebSite(String urlWebSite) {
        this.urlWebSite = urlWebSite;
    }

    public String getUrlFacebook() {
        return urlFacebook;
    }

    public void setUrlFacebook(String urlFacebook) {
        this.urlFacebook = urlFacebook;
    }
}
