package persistencia;

import java.util.Date;

public class Venda {

    private int id;
    private int itensVendidos;
    private float valorTotal;
    private Date dataVenda;

    public Venda(int _id, int _itensVendidos, float _valorTotal, Date _dataVenda) {
        this.id = _id;
        this.itensVendidos = _itensVendidos;
        this.valorTotal = _valorTotal;
        this.dataVenda = _dataVenda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItensVendidos() {
        return itensVendidos;
    }

    public void setItensVendidos(int itensVendidos) {
        this.itensVendidos = itensVendidos;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    // Gera um recibo da venda
    public void gerarRecibo() {
    }
}
