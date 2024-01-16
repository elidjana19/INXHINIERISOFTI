import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Connection connection;

    public LoginFrame(Connection connection) {
        this.connection = connection;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    login();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);

        add(mainPanel);
    }


    private void login() throws SQLException {
        {
            String inputUsername = usernameField.getText();
            String inputPassword = passwordField.getText();

            PreparedStatement ps = connection.prepareStatement("select * from users where username=? and password=?");

            ps.setString(1, inputUsername);  // vendos vlerat ne placeholder
            ps.setString(2, inputPassword);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                dispose();

                // hap CourseFrame
                CourseManager courseManager = new CourseManager(connection);
                FeedbackManager feedbackManager = new FeedbackManager(connection);
                CourseFrame courseFrame = new CourseFrame(courseManager, feedbackManager, inputUsername, connection);
                courseFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Nuk jeni te rregjistruar");
                dispose();
                Main main = new Main();
                main.setVisible(true);
            }
        }

    }






    
}