package cafeteraapp;

import cafeteraapp.dao.UsuarioDAO;
import cafeteraapp.model.Usuario;

import java.util.Scanner;

public class LoginTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        System.out.println("=== Módulo Básico de Login ===");

        System.out.print("Ingrese usuario: ");
        String username = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();

        // Para simplicidad, asumimos que el password es ingresado en texto plano y comparado así.
        // En producción, usar hashing seguro y almacenar hashes.

        Usuario usuario = usuarioDAO.login(username, password);

        if (usuario != null) {
            System.out.println("Login exitoso. Bienvenido, " + usuario.getUsername());
            System.out.println("Rol: " + usuario.getRol());
        } else {
            System.out.println("Error: Usuario o contraseña incorrectos.");
        }

        scanner.close();
    }
}
