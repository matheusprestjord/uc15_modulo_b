package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import persistencia.Usuario;

public class UsuarioDAO {

    Conexao conexao;
    Connection conn;

    public UsuarioDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    public void cadastrarUsuario(Usuario _usuario) {
        //conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO usuario (nome, cargo, senha) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, _usuario.getNome());
            stmt.setString(2, _usuario.getCargo());
            stmt.setString(3, _usuario.getSenha());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro UsuarioDAO " + e.getMessage());
        }
    }

    public boolean jaExiste(String _nome) {
        String sql = "SELECT COUNT(*) as total FROM usuario WHERE nome = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, _nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("total");
                    return count > 0;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar se o usuário já existe: " + e.getMessage());
            // Você pode querer relançar a exceção ou lidar de outra forma
        }

        return false; // Retorna false em caso de erro ou se não encontrar registros
    }

    public String obterSenha(String _nome) {
        String sql = "SELECT senha FROM usuario WHERE nome = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, _nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Usuário existe, retorna a senha
                    return rs.getString("senha");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar usuário e obter senha: " + e.getMessage());
            // Você pode querer relançar a exceção ou lidar de outra forma
        }

        // Retorna null se o usuário não existir ou ocorrer um erro
        return null;
    }

    public String obterCargo(String _nome) {
        String sql = "SELECT cargo FROM usuario WHERE nome = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, _nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Usuário existe, retorna a senha
                    return rs.getString("cargo");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar usuário e obter cargo: " + e.getMessage());
            // Você pode querer relançar a exceção ou lidar de outra forma
        }

        // Retorna null se o usuário não existir ou ocorrer um erro
        return null;
    }

    // Verifica se o usuário colocou as credenciais corretas
    public boolean login(String nome, String senha) {
        String sql = "SELECT COUNT(*) as total FROM usuario WHERE nome = ? AND senha = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("total");
                    return count > 0;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar fazer login: " + e.getMessage());
        }

        return false;
    }
}
