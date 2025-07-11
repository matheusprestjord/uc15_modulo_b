package dao;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import persistencia.Insumo;

public class InsumoDAO {

    Conexao conexao;
    Connection conn;

    public InsumoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    public void cadastrarInsumo(Insumo _insumo) {
        //conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO insumo (nome, quantidade, preco, datadevalidade, fornecedor, datadeentrada) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, _insumo.getNome());
            stmt.setDouble(2, _insumo.getQuantidade());
            stmt.setDouble(3, _insumo.getPreco());
            stmt.setDate(4, Date.valueOf(_insumo.getDataValidade()));
            stmt.setString(5, _insumo.getFornecedor());
            stmt.setDate(6, Date.valueOf(_insumo.getDataEntrada()));
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar insumo: " + e.getMessage());
        }
    }

    // Remover insumo
    public void removerInsumo(int _id) {
        //conn = new conectaDAO().connectDB();
        String sql = "DELETE FROM insumo WHERE id = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, _id);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro InsumoDAO: " + e.getMessage());
        }
    }

    public List<Insumo> listarTodos() {
        String sql = "SELECT * FROM insumo";
        List<Insumo> listaInsumos = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Insumo insumo = new Insumo();
                insumo.setId(rs.getInt("id"));
                insumo.setNome(rs.getString("nome"));
                insumo.setQuantidade(rs.getDouble("quantidade"));
                insumo.setPreco(rs.getDouble("preco"));
                insumo.setDataValidade(rs.getDate("datadevalidade").toLocalDate());
                insumo.setFornecedor(rs.getString("fornecedor"));
                insumo.setDataEntrada(rs.getDate("datadeentrada").toLocalDate());
                listaInsumos.add(insumo);
            }

            return listaInsumos;
        } catch (Exception e) {
            System.out.println("Erro ao buscar insumos: " + e.getMessage());
            return null;
        }
    }

    // Diminuir insumo
    public void diminuirInsumo(int _id, double _quantidade) {
        // Desativar auto-commit para usar transação
        try {
            conn.setAutoCommit(false);

            // Primeiro comando: atualizar a quantidade
            String updateSql = "UPDATE insumo SET quantidade = quantidade - ? WHERE id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setDouble(1, _quantidade);
                updateStmt.setInt(2, _id);
                int rowsUpdated = updateStmt.executeUpdate();

                if (rowsUpdated == 0) {
                    throw new Exception("Insumo não encontrado ou quantidade insuficiente");
                }
            }

            // Segundo comando: remover os produtos insuficientes
            String deleteSql = "DELETE FROM insumo WHERE id = ? AND quantidade <= 0";
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
            System.err.println("Erro ao vender insumo: " + e.getMessage());
            throw new RuntimeException("Falha na subtração do insumo", e);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (Exception e) {
                System.err.println("Erro ao restaurar auto-commit: " + e.getMessage());
            }
        }
    }
}
