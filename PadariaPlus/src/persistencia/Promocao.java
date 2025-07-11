package persistencia;

import java.time.LocalDate;

public class Promocao {

    private int id;
    private String descricao;
    private Produto produtoRelacionado;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Produto getProdutoRelacionado() {
        return produtoRelacionado;
    }

    public void setProdutoRelacionado(Produto produtoRelacionado) {
        this.produtoRelacionado = produtoRelacionado;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
