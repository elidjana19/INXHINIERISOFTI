import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseFrame extends JFrame {
    private CourseManager courseManager;
    private FeedbackManager feedbackManager;
    private String loggedInUsername;

    private Connection connection;

    public CourseFrame(CourseManager courseManager, FeedbackManager feedbackManager, String loggedInUsername, Connection connection) {
        this.courseManager = courseManager;
        this.feedbackManager = feedbackManager;
        this.loggedInUsername = loggedInUsername;
        this.connection=connection;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Available courses");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFrame frame = new JFrame("panel");

        add(frame);
        setVisible(true);
    }


}