package persistencia;

import java.time.LocalDate;

public class FluxoCaixa {

    private int id;
    private LocalDate data;
    private String tipo;
    private double valor;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate _data) {
        this.data = _data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String _tipo) {
        this.tipo = _tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double _valor) {
        this.valor = _valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String _descricao) {
        this.descricao = _descricao;
    }
}
