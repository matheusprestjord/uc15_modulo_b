package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    public Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/uc15?useSSL=false",
                    "root",
                    "31052007"
            );

            return conn;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());

            return null;
        }
    }
}
