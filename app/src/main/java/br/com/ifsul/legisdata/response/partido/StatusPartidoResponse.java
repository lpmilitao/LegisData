package br.com.ifsul.legisdata.response.partido;

public class StatusPartidoResponse {
    private String data;
    private String idLegislatura;
    private String situacao;
    private String totalPosse;
    private String totalMembros;
    private String uriMembros;
    private LiderPartidoResponse lider;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIdLegislatura() {
        return idLegislatura;
    }

    public void setIdLegislatura(String idLegislatura) {
        this.idLegislatura = idLegislatura;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTotalPosse() {
        return totalPosse;
    }

    public void setTotalPosse(String totalPosse) {
        this.totalPosse = totalPosse;
    }

    public String getTotalMembros() {
        return totalMembros;
    }

    public void setTotalMembros(String totalMembros) {
        this.totalMembros = totalMembros;
    }

    public String getUriMembros() {
        return uriMembros;
    }

    public void setUriMembros(String uriMembros) {
        this.uriMembros = uriMembros;
    }

    public LiderPartidoResponse getLider() {
        return lider;
    }

    public void setLider(LiderPartidoResponse lider) {
        this.lider = lider;
    }
}
