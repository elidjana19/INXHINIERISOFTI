import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Connection connection;

    public RegistrationFrame(Connection connection) {
        this.connection = connection;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Registration");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton registerButton = new JButton("Register");


        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(registerButton);

        add(mainPanel);
    }

    private void registerAccount() throws SQLException {
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();

        PreparedStatement ps = connection.prepareStatement("select * from users where username=? and password=?");

        ps.setString(1, inputUsername);  // Set the value for the first placeholder
        ps.setString(2, inputPassword);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            JOptionPane.showMessageDialog(this, "Jeni i regjistruar");
            dispose();
            Main main = new Main();
        }
        else{
            PreparedStatement ps1 = connection.prepareStatement("insert into users (username, password) values (?,?)");

            ps1.setString(1, inputUsername);
            ps1.setString(2, inputPassword);
            if( ps1.executeUpdate()==1)
            {
                JOptionPane.showMessageDialog(this,"Rregjistrimi u krye me sukses");
                dispose();
                Main main=new Main();

            }
        }

    }
}
