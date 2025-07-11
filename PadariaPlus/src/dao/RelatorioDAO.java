package dao;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import persistencia.Relatorio;

public class RelatorioDAO {

    Conexao conexao;
    Connection conn;

    public RelatorioDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    public void enviarRelatorio(Relatorio _relatorio) {
        //conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO relatorio (tipo, data_, descricao) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, _relatorio.getTipoRelatorio());
            stmt.setDate(2, Date.valueOf(_relatorio.getDataGeracao()));
            stmt.setString(3, _relatorio.getDescricao());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar relatório: " + e.getMessage());
        }
    }

    // Remover insumo
    public void removerRelatorio(int _id) {
        //conn = new conectaDAO().connectDB();
        String sql = "DELETE FROM relatorio WHERE id = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, _id);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro RelatorioDAO: " + e.getMessage());
        }
    }

    public List<Relatorio> listarTodos() {
        String sql = "SELECT * FROM relatorio";
        List<Relatorio> listaRelatorios = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setId(rs.getInt("id"));
                relatorio.setTipoRelatorio(rs.getString("tipo"));
                relatorio.setDataGeracao(rs.getDate("data_").toLocalDate());
                relatorio.setDescricao(rs.getString("descricao"));
                listaRelatorios.add(relatorio);
            }

            return listaRelatorios;
        } catch (Exception e) {
            System.out.println("Erro ao buscar relatórios: " + e.getMessage());
            return null;
        }
    }
}
