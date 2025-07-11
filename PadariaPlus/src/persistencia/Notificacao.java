package persistencia;

import java.util.Date;

public class Notificacao {

    private int id;
    private String mensagem;
    private String tipoNotificacao;
    private Date dataEnvio;

    public Notificacao(String _mensagem, String _tipoNotificacao, Date _dataEnvio) {
        this.mensagem = _mensagem;
        this.tipoNotificacao = _tipoNotificacao;
        this.dataEnvio = _dataEnvio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    // Envia a notificação para a tela inicial de um usuário
    public void enviarNotificacao() {
    }

    // Revela os detalhes da notificação
    public void exibirNotificacoes() {
    }
}
