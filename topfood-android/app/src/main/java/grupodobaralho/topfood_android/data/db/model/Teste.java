package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;

public class Teste {

    @SerializedName("Aplicacao")
    private String aplicacao;
    @SerializedName("Descricao")
    private User descricao;
    @SerializedName("Arquiteto")
    private Product arquiteto;
    @SerializedName("Frontend")
    private String frontend;
    @SerializedName("Backend")
    private String backend;

    public Teste(String aplicacao, User descricao, Product arquiteto, String frontend, String backend) {
        this.aplicacao = aplicacao;
        this.descricao = descricao;
        this.arquiteto = arquiteto;
        this.frontend = frontend;
        this.backend = backend;
    }

    public String getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(String aplicacao) {
        this.aplicacao = aplicacao;
    }

    public User getDescricao() {
        return descricao;
    }

    public void setDescricao(User descricao) {
        this.descricao = descricao;
    }

    public Product getArquiteto() {
        return arquiteto;
    }

    public void setArquiteto(Product arquiteto) {
        this.arquiteto = arquiteto;
    }

    public String getFrontend() {
        return frontend;
    }

    public void setFrontend(String frontend) {
        this.frontend = frontend;
    }

    public String getBackend() {
        return backend;
    }

    public void setBackend(String backend) {
        this.backend = backend;
    }

    @Override
    public String toString() {
        return "Teste{" +
                "aplicacao='" + aplicacao + '\'' +
                ", descricao=" + descricao +
                ", arquiteto=" + arquiteto +
                ", frontend='" + frontend + '\'' +
                ", backend='" + backend + '\'' +
                '}';
    }
}
