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

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               openLoginFrame();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               openRegistrationFrame();
            }
        });


        mainPanel.add(registerButton);
        mainPanel.add(loginButton);
        add(mainPanel);
    }


    private void openRegistrationFrame() {
        RegistrationFrame registrationFrame = new RegistrationFrame(connection);
        registrationFrame.setVisible(true);
    }

    private void openLoginFrame() {
        LoginFrame loginFrame = new LoginFrame(connection);
        dispose();
        loginFrame.setVisible(true);

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