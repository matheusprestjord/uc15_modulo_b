package persistencia;

import java.time.LocalDate;

public class Insumo {

    private int id;
    private String nome;
    private double quantidade;
    private double preco;
    private LocalDate dataValidade;
    private String fornecedor;
    private LocalDate dataEntrada;

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String _nome) {
        this.nome = _nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double _quantidade) {
        this.quantidade = _quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double _preco) {
        this.preco = _preco;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate _dataValidade) {
        this.dataValidade = _dataValidade;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String _fornecedor) {
        this.fornecedor = _fornecedor;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate _dataEntrada) {
        this.dataEntrada = _dataEntrada;
    }
}
