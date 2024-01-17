import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CourseDetailsFrame extends JFrame {
    private CourseManager courseManager;
    private FeedbackManager feedbackManager;
    private String loggedInUsername;
    private String courseName;

    public CourseDetailsFrame(CourseManager courseManager, FeedbackManager feedbackManager, String loggedInUsername, String courseName) {
        this.courseManager = courseManager;
        this.feedbackManager = feedbackManager;
        this.loggedInUsername = loggedInUsername;
        this.courseName = courseName;

        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Course Details: " + courseName);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel detailsPanel = new JPanel(new BorderLayout());

        // krijon nje tabele
        DefaultTableModel model = new DefaultTableModel();
        JTable detailsTable = new JTable(model);

        // hidh te dhenat ne tabele
        model.addColumn("Attribute");
        model.addColumn("Value");

        String courseDetails = courseManager.getCourseDetails(courseName);
        String[] detailsArray = courseDetails.split("\n");

        for (String detail : detailsArray) {
            String[] parts = detail.split(":");
            if (parts.length == 2) {
                model.addRow(new Object[]{parts[0].trim(), parts[1].trim()});
            }
        }

        // pershtat madhesine e tabeles
        detailsTable.setRowHeight(30);
        detailsTable.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(detailsTable);
        detailsPanel.add(scrollPane, BorderLayout.CENTER);

        add(detailsPanel);
        setLocationRelativeTo(null); // qenderzo frame-in
        setVisible(true);
    }
}