package dao;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import persistencia.Produto;
import persistencia.Promocao;

public class PromocaoDAO {

    Conexao conexao;
    Connection conn;

    // Construtor feito para se conectar com o banco de dados
    public PromocaoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    // Criar promoções
    public void cadastrarPromocao(Promocao _promocao) {
        //conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO promocao (produto_id, datadeinicio, datadefim, descricao) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, _promocao.getProdutoRelacionado().getId());
            stmt.setDate(2, Date.valueOf(_promocao.getDataInicio()));
            stmt.setDate(3, Date.valueOf(_promocao.getDataFim()));
            stmt.setString(4, _promocao.getDescricao());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar promoção: " + e.getMessage());
        }
    }

    // Remover promoções
    public void remover(int _id) {
        //conn = new conectaDAO().connectDB();
        String sql = "DELETE FROM promocao WHERE id = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, _id);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao remover promoção: " + e.getMessage());
        }
    }

    // Listar todas as promoções
    public List<Promocao> listarTodos() {
        String sql = "SELECT p.*, pr.nome as produto_nome FROM promocao p "
                + "LEFT JOIN produto pr ON p.produto_id = pr.id";
        List<Promocao> listaPromocoes = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Promocao promocao = new Promocao();
                promocao.setId(rs.getInt("id"));
                Produto produto = new Produto();
                produto.setId(rs.getInt("produto_id"));
                produto.setNome(rs.getString("produto_nome"));
                promocao.setProdutoRelacionado(produto);
                promocao.setDataInicio(rs.getDate("datadeinicio").toLocalDate());
                promocao.setDataFim(rs.getDate("datadefim").toLocalDate());
                promocao.setDescricao(rs.getString("descricao"));
                listaPromocoes.add(promocao);
            }

            return listaPromocoes;
        } catch (Exception e) {
            System.out.println("Erro ao buscar promoções: " + e.getMessage());
            return null;
        }
    }
}
