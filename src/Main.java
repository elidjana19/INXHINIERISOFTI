import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Main extends JFrame {
    private Connection connection;

    public Main() {
        initializeGUI();
        connection = DatabaseConnector.connect();
        if (connection == null) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    private void initializeGUI() {
        setTitle("Main Application");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(2, 1));

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main mainApp = new Main();
                mainApp.setVisible(true);
                mainApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}