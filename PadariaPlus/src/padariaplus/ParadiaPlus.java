package padariaplus;

import dao.UsuarioDAO;
import persistencia.Usuario;
import java.util.Scanner;

public class ParadiaPlus {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Login
        System.out.println("===   PADARIAPLUS   ===\n");
        System.out.println("Ja tem cadastro? (y/n)");
        char resposta = (scanner.nextLine()).toLowerCase().charAt(0);

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        switch (resposta) {
            case 'y':
                System.out.println("Qual o seu nome?");
                String nomeLogin = scanner.nextLine().toLowerCase();
                System.out.println("Qual a sua senha?");
                String senhaLogin = scanner.nextLine();
                
                if (usuarioDAO.login(nomeLogin, senhaLogin)) {
                    
                } else {
                    System.out.println("Credenciais incorretas! Fechando sistema...");
                    System.exit(0);
                }

                break;
            case 'n':
                System.out.println("\n=  CADASTRO DE USUARIO  =\n");

                System.out.println("Qual o seu nome?");
                String nomeCadastro = scanner.nextLine().toLowerCase();
                System.out.println("Qual o seu cargo? ('gerente', 'programador', 'padeiro' ou 'caixa')");
                String cargoCadastro = scanner.nextLine().toLowerCase();
                System.out.println("Qual sera a sua senha?");
                String senhaCadastro = scanner.nextLine();

                Usuario usuario = new Usuario(nomeCadastro, cargoCadastro, senhaCadastro);
                if (usuarioDAO.jaExiste(usuario.getNome()) == false) {
                    usuarioDAO.cadastrarUsuario(usuario);
                }
                break;
        }

        System.out.println("Teste concluído com êxito");
    }

}
