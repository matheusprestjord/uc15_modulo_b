package persistencia;

import java.util.List;

public class Caixa {

    private int id;
    private float saldoAtual;
    private List<Venda> historicoTransacoes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(float saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public List<Venda> getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    public void setHistoricoTransacoes(List<Venda> historicoTransacoes) {
        this.historicoTransacoes = historicoTransacoes;
    }

}
