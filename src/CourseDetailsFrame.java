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

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton joinButton = new JButton("Join Course");
        JButton leaveButton = new JButton("Leave Course");
        JButton feedbackButton = new JButton("Leave Feedback");
        JButton viewFeedbackButton = new JButton("View Feedback");

        // pershtat paraqitjen e butonave
        joinButton.setPreferredSize(new Dimension(150, 40));
        leaveButton.setPreferredSize(new Dimension(150, 40));
        feedbackButton.setPreferredSize(new Dimension(150, 40));
        viewFeedbackButton.setPreferredSize(new Dimension(150, 40));

        // vendos action listeners butonave
        joinButton.addActionListener(e -> handleJoinCourse());
        leaveButton.addActionListener(e -> handleLeaveCourse());
        feedbackButton.addActionListener(e -> handleLeaveFeedback());
        viewFeedbackButton.addActionListener(e -> handleViewFeedback());

        buttonPanel.add(joinButton);
        buttonPanel.add(leaveButton);
        buttonPanel.add(feedbackButton);
        buttonPanel.add(viewFeedbackButton);

        detailsPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(detailsPanel);
        setLocationRelativeTo(null); // qenderzo frame-in
        setVisible(true);
    }

    private void handleJoinCourse() {
        if (courseManager.joinCourse(loggedInUsername, courseName)) {
            JOptionPane.showMessageDialog(this, "You have successfully joined the course.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "You are already registered for this course.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLeaveCourse() {
        if (courseManager.dropCourse(loggedInUsername, courseName)) {
            JOptionPane.showMessageDialog(this, "You have successfully left the course.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "You are not registered for this course.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLeaveFeedback() {
        String description = JOptionPane.showInputDialog(this, "Enter feedback description (max 1000 characters):");

        if (description != null && description.length() > 1000) {
            JOptionPane.showMessageDialog(this, "Feedback description should be at most 1000 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int rating = -1;
        while (rating < 1 || rating > 5) {
            String ratingInput = JOptionPane.showInputDialog(this, "Enter feedback rating (1 to 5):");
            try {
                rating = Integer.parseInt(ratingInput);
            } catch (NumberFormatException ex) {

            }
        }

        if (feedbackManager.leaveFeedback(loggedInUsername, courseName, description, rating)) {
            JOptionPane.showMessageDialog(this, "Feedback submitted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error submitting feedback.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleViewFeedback() {
        List<String> feedbackList = feedbackManager.viewFeedback(courseName);

        StringBuilder feedbackText = new StringBuilder();
        for (String feedback : feedbackList) {
            feedbackText.append(feedback).append("\n\n");
        }

        if (feedbackList.isEmpty()) {
            feedbackText.append("Nuk ka feedback per kete kurs.");
        }

        JTextArea feedbackArea = new JTextArea(feedbackText.toString());
        feedbackArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(feedbackArea);

        JOptionPane.showMessageDialog(this, scrollPane, "Feedback for " + courseName, JOptionPane.INFORMATION_MESSAGE);
    }
}