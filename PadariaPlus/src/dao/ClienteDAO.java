package dao;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import persistencia.Cliente;

public class ClienteDAO {

    Conexao conexao;
    Connection conn;

    public ClienteDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    // Método para adicionar cliente
    public void adicionarCliente(Cliente _cliente) {
        String sql = "INSERT INTO CLIENTE (cpf, nome, pontos) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, _cliente.getCpf());
            stmt.setString(2, _cliente.getNome());
            stmt.setInt(3, _cliente.getPontos());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    // Método para buscar pelo CPF
    public Cliente buscarPorCpf(String _nome) {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, _nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setPontos(rs.getInt("pontos"));

                    return cliente;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente por CPF: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar
    }

    // Método para alterar o valor de pontos
    public boolean alterarPontos(int _pontos, int _id) {
        String sql = "UPDATE cliente SET pontos = ? WHERE id = ?";

        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setInt(1, _pontos);
            stmt.setInt(2, _id);

            // Usando executeUpdate() que retorna o número de linhas afetadas
            int rowsAffected = stmt.executeUpdate();

            // Verifica se alguma linha foi realmente atualizada
            if (rowsAffected > 0) {
                System.out.println("Pontos do cliente ID " + _id + " atualizados para " + _pontos);
                return true;
            } else {
                System.out.println("Nenhum cliente encontrado com o ID: " + _id);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar os pontos do cliente: " + e.getMessage());
            //e.printStackTrace();
            return false;
        }
    }
}
