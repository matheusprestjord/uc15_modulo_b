package dao;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import persistencia.Produto;

public class ProdutoDAO {

    Conexao conexao;
    Connection conn;
    ArrayList<Produto> listagem = new ArrayList<>();

    public ProdutoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    public void cadastrarProduto(Produto _produto) {
        //conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO produto (nome, quantidade, preco, datadevalidade) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, _produto.getNome());
            stmt.setDouble(2, _produto.getQuantidade());
            stmt.setDouble(3, _produto.getPreco());
            stmt.setDate(4, Date.valueOf(_produto.getDataValidade()));
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ProdutosDAO " + e.getMessage());
        }
    }

    // Vender produto
    public void venderProduto(int _id, int _quantidade) {
        // Desativar auto-commit para usar transação
        try {
            conn.setAutoCommit(false);

            // Primeiro comando: atualizar a quantidade
            String updateSql = "UPDATE produto SET quantidade = quantidade - ? WHERE id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, _quantidade);
                updateStmt.setInt(2, _id);
                int rowsUpdated = updateStmt.executeUpdate();

                if (rowsUpdated == 0) {
                    throw new Exception("Produto não encontrado ou quantidade insuficiente");
                }
            }

            // Segundo comando: remover os produtos insuficientes
            String deleteSql = "DELETE FROM produto WHERE id = ? AND quantidade <= 0";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                deleteStmt.setInt(1, _id);
                deleteStmt.executeUpdate();
            }

            // Commit da transação
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                System.err.println("Erro no rollback: " + ex.getMessage());
            }
            System.err.println("Erro ao vender produto: " + e.getMessage());
            throw new RuntimeException("Falha na subtração do insumo", e);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (Exception e) {
                System.err.println("Erro ao restaurar auto-commit: " + e.getMessage());
            }
        }
    }

    // Remover produto
    public void removerProduto(int _id) {
        //conn = new conectaDAO().connectDB();
        String sql = "DELETE FROM produto WHERE id = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, _id);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ProdutoDAO: " + e.getMessage());
        }
    }

    public ArrayList<Produto> listarProdutos() {
        return listagem;
    }

    public List<Produto> listarTodos() {
        String sql = "SELECT * FROM produto";
        List<Produto> listaProdutos = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getDouble("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setDataValidade(rs.getDate("datadevalidade").toLocalDate());
                listaProdutos.add(produto);
            }

            return listaProdutos;
        } catch (Exception e) {
            System.out.println("Erro ao buscar produtos: " + e.getMessage());
            return null;
        }
    }

    public Produto buscarPorNome(String _nome) {
        String sql = "SELECT * FROM produto WHERE nome = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, _nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setQuantidade(rs.getDouble("quantidade"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setDataValidade(rs.getDate("datadevalidade").toLocalDate());
                    // Preencha outros campos conforme necessário
                    return produto;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar produto por nome: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar
    }
}
