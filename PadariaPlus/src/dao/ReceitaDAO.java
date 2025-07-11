package dao;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import persistencia.Receita;

public class ReceitaDAO {

    Conexao conexao;
    Connection conn;

    public ReceitaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    // Método para adicionar receita
    public void adicionarReceita(Receita _receita) {
        //conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO receita (nome, mododepreparo) VALUES (?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, _receita.getNome());
            stmt.setString(2, _receita.getModoPreparo());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar receita: " + e.getMessage());
        }
    }

    // Método para remover receita
    public void removerReceita(int _id) {
        //conn = new conectaDAO().connectDB();
        String sql = "DELETE FROM receita WHERE id = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, _id);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao remover receita: " + e.getMessage());
        }
    }

    // Método para listar todas as receitas
    public List<Receita> listarTodos() {
        String sql = "SELECT * FROM receita";
        List<Receita> listaReceitas = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Receita receita = new Receita();
                receita.setId(rs.getInt("id"));
                receita.setNome(rs.getString("nome"));
                receita.setModoPreparo(rs.getString("mododepreparo"));
                listaReceitas.add(receita);
            }

            return listaReceitas;
        } catch (Exception e) {
            System.out.println("Erro ao buscar receitas: " + e.getMessage());
            return null;
        }
    }
}
