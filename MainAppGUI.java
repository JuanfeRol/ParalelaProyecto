import javax.swing.*;
import java.awt.*;

public class MainAppGUI {
    private SistemaTarjetas sistemaTarjetas;
    private JTextField codigoTextField, montoTextField;
    private JTextArea displayArea;

    public MainAppGUI() {
        sistemaTarjetas = new SistemaTarjetas(5, 100); // 5 tarjetas con saldo inicial de 100
        initializeUI();
    }

    private void initializeUI() {
        // Crear el marco principal
        JFrame frame = new JFrame("Tarjetronic - SGTD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500); // 500x500
        frame.setResizable(false); // Deshabilitar redimensionamiento
        frame.setLayout(new BorderLayout());

        // Centrar la ventana
        frame.setLocationRelativeTo(null);

        // Colores de la paleta
        Color darkGreen = Color.decode("#1F4529");
        Color mediumGreen = Color.decode("#47663B");
        Color lightGreen = Color.decode("#E8ECD7");
        Color beige = Color.decode("#EED3B1");

        // Crear panel para los controles
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.setBackground(mediumGreen); // Fondo del panel

        JLabel codigoLabel = new JLabel("Código de tarjeta (000-004):");
        JLabel montoLabel = new JLabel("Monto:");
        codigoLabel.setForeground(beige); // Texto claro
        montoLabel.setForeground(beige);

        codigoTextField = new JTextField();
        montoTextField = new JTextField();

        JButton cargarButton = new JButton("Cargar saldo");
        JButton pagarButton = new JButton("Realizar pago");
        JButton consultarButton = new JButton("Consultar saldo");
        JButton salirButton = new JButton("Salir");
        JButton crearTarjetaButton = new JButton("Crear tarjeta");

        // Personalizar botones
        cargarButton.setBackground(lightGreen);
        pagarButton.setBackground(lightGreen);
        consultarButton.setBackground(lightGreen);
        salirButton.setBackground(beige);
        crearTarjetaButton.setBackground(lightGreen);
        cargarButton.setForeground(darkGreen);
        pagarButton.setForeground(darkGreen);
        consultarButton.setForeground(darkGreen);
        salirButton.setForeground(darkGreen);
        crearTarjetaButton.setForeground(darkGreen);

        // Crear área de texto para mostrar resultados
        displayArea = new JTextArea(10, 20);
        displayArea.setEditable(false);
        displayArea.setBackground(lightGreen);
        displayArea.setForeground(darkGreen);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        

        // Agregar componentes al panel

        panel.add(codigoLabel);
        panel.add(codigoTextField);
        panel.add(montoLabel);
        panel.add(montoTextField);
        panel.add(crearTarjetaButton);
        panel.add(cargarButton);
        panel.add(pagarButton);
        panel.add(consultarButton);
        panel.add(salirButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Acciones para los botones
        cargarButton.addActionListener(e -> realizarAccion("cargar"));
        pagarButton.addActionListener(e -> realizarAccion("pagar"));
        consultarButton.addActionListener(e -> realizarAccion("consultar"));
        crearTarjetaButton.addActionListener(e -> realizarAccion("crear"));
        salirButton.addActionListener(e -> System.exit(0));
    

        // Hacer visible el marco
        frame.setVisible(true);
    }

    private void realizarAccion(String accion) {
        String codigo = codigoTextField.getText();
        String montoText = montoTextField.getText();
        double monto = montoText.isEmpty() ? 0 : Double.parseDouble(montoText);

        try {
            switch (accion) {
                case "cargar":
                    sistemaTarjetas.getTarjeta(codigo).cargarSaldo(monto);
                    displayArea.append("Se cargaron " + monto + " a la tarjeta " + codigo + "\n");
                    break;
                case "pagar":
                    sistemaTarjetas.getTarjeta(codigo).realizarPago(monto);
                    displayArea.append("Se realizó un pago de " + monto + " con la tarjeta " + codigo + "\n");
                    break;
                case "consultar":
                    double saldo = sistemaTarjetas.getTarjeta(codigo).consultarSaldo();
                    displayArea.append("El saldo actual de la tarjeta con número " + codigo + " es: " + saldo + "\n");
                    break;
                case "crear":  
                    sistemaTarjetas.crearTarjeta(codigo, 100);
                    displayArea.append("Se creó una tarjeta con código " + codigo + "\n");
                    break;
            }
        } catch (IllegalArgumentException e) {
            displayArea.append(e.getMessage() + "\n");
        } finally {
            // Limpiar los campos de texto
            codigoTextField.setText("");
            montoTextField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainAppGUI::new);
    }
}
