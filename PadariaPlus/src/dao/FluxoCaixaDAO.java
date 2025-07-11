package dao;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import persistencia.FluxoCaixa;

public class FluxoCaixaDAO {

    Conexao conexao;
    Connection conn;

    public FluxoCaixaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    // Método para adicionar fluxo de caixa
    public void adicionarFluxoCaixa(FluxoCaixa _fluxoCaixa) {
        //conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO fluxodecaixa (data_, tipo, valor, descricao) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(_fluxoCaixa.getData()));
            stmt.setString(2, _fluxoCaixa.getTipo());
            stmt.setDouble(3, _fluxoCaixa.getValor());
            stmt.setString(4, _fluxoCaixa.getDescricao());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar fluxo de caixa: " + e.getMessage());
        }
    }

    // Método para remover fluxo de caixa
    public void remover(int _id) {
        //conn = new conectaDAO().connectDB();
        String sql = "DELETE FROM fluxodecaixa WHERE id = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, _id);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao remover fluxo de caixa: " + e.getMessage());
        }
    }

    // Método para listar todas os fluxos de caixa
    public List<FluxoCaixa> listarTodos() {
        String sql = "SELECT * FROM fluxodecaixa";
        List<FluxoCaixa> listaReceitas = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FluxoCaixa fluxoCaixa = new FluxoCaixa();
                fluxoCaixa.setId(rs.getInt("id"));
                fluxoCaixa.setData(rs.getDate("data_").toLocalDate());
                fluxoCaixa.setTipo(rs.getString("tipo"));
                fluxoCaixa.setValor(rs.getDouble("valor"));
                fluxoCaixa.setDescricao(rs.getString("descricao"));
                listaReceitas.add(fluxoCaixa);
            }

            return listaReceitas;
        } catch (Exception e) {
            System.out.println("Erro ao buscar fluxos de caixa: " + e.getMessage());
            return null;
        }
    }
}
