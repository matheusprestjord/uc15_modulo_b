package persistencia;

public class Cliente {

    private int id;
    private String cpf;
    private String nome;
    private int pontos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    // Adiciona pontos de fidelidade a um cliente específico através de seu CPF
    public void acumularPontos() {
    }

    // Consulta quantos pontos de fidelidade um cliente tem através de seu CPF
    public void consultarPontos() {
    }

    // Gasta os pontos do cliente por uma recompensa
    public void trocarPontos() {
    }
}
