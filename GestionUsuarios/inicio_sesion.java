import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    // Declaramos los componentes de la ventana
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JButton loginButton;
    private JLabel mensajeLabel;

    // Constructor - aquí configuramos la ventana
    public LoginFrame() {
        // Configuración básica de la ventana
        setTitle("Iniciar Sesión");               // Título de la ventana
        setSize(400, 300);                        // Tamaño: ancho 400, alto 300
        setLocationRelativeTo(null);              // Centrada en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar programa al cerrar ventana
        setResizable(false);                      // No se puede redimensionar

        // Creamos el panel principal con espacio entre elementos
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Elementos verticales
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Espacios
        panel.setBackground(new Color(240, 240, 240)); // Color gris claro

        // Título
        JLabel titleLabel = new JLabel("Bienvenido");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20)); // Espacio

        // Etiqueta y campo de usuario
        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(usuarioLabel);
        usuarioField = new JTextField(15);
        usuarioField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(usuarioField);
        panel.add(Box.createVerticalStrut(15)); // Espacio

        // Etiqueta y campo de contraseña
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(contrasenaLabel);
        contrasenaField = new JPasswordField(15);
        contrasenaField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(contrasenaField);
        panel.add(Box.createVerticalStrut(20)); // Espacio

        // Botón de login
        loginButton = new JButton("Entrar");
        loginButton.setBackground(new Color(70, 130, 180)); // Azul
        loginButton.setForeground(Color.WHITE);             // Texto blanco
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        loginButton.addActionListener(new LoginAction()); // Acción al presionar
        panel.add(loginButton);
        panel.add(Box.createVerticalStrut(15)); // Espacio

        // Etiqueta para mostrar mensajes
        mensajeLabel = new JLabel("");
        mensajeLabel.setForeground(Color.RED); // Texto rojo para errores
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(mensajeLabel);

        // Agregamos el panel a la ventana
        add(panel);

        // Hacemos visible la ventana
        setVisible(true);
    }

    // Clase interna para manejar el click del botón
    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtenemos los valores de los campos
            String usuario = usuarioField.getText();
            String contrasena = new String(contrasenaField.getPassword());

            // Validamos que los campos no estén vacíos
            if (usuario.isEmpty() || contrasena.isEmpty()) {
                mensajeLabel.setText("⚠ Completa todos los campos");
                mensajeLabel.setForeground(Color.RED);
                return;
            }

            // DEMO: Usuarios válidos con contraseña "123"
            // En un proyecto real, verificarías en una base de datos
            if ((usuario.equals("admin") || usuario.equals("usuario")) && contrasena.equals("123")) {
                mensajeLabel.setText("✓ ¡Bienvenido " + usuario + "!");
                mensajeLabel.setForeground(new Color(0, 128, 0)); // Verde
                
                // Después de 1.5 segundos, limpiamos y esperamos
                Timer timer = new Timer(1500, evt -> {
                    usuarioField.setText("");
                    contrasenaField.setText("");
                    mensajeLabel.setText("");
                });
                timer.setRepeats(false);
                timer.start();
            } else {
                mensajeLabel.setText("✗ Usuario o contraseña incorrectos");
                mensajeLabel.setForeground(Color.RED);
                contrasenaField.setText(""); // Limpiamos la contraseña
            }
        }
    }

    // Método principal - aquí iniciamos el programa
    public static void main(String[] args) {
        // Ejecutamos la interfaz en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
} 