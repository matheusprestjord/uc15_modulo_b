package persistencia;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int id;
    private String nome;
    private String cargo;
    private String senha;
    private List<String> permissoes;

    public Usuario() {
    }

    public Usuario(String nome, String cargo, String senha) {
        this.nome = nome;
        this.cargo = cargo;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<String> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<String> permissoes) {
        this.permissoes = permissoes;
    }

    // Usa as credenciais para acessar o sistema
    public void login() {
    }

    // Verifica as permissões do usuário
    public void consultarPermissoes() {
    }

    // Altera as permissões de um usuário específico
    public void alterarPermissoes() {
    }

    // Adiciona permissões a um usuário
    public void adicionarPermissao(String _permissao) {
        if (this.permissoes == null) {
            this.permissoes = new ArrayList<>();
        }
        this.permissoes.add(_permissao);
    }
}
