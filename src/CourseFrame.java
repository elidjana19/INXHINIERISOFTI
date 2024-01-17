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

        List<String> courses = courseManager.getAllCourses();
        CourseTableModel model = new CourseTableModel(courses);
        JTable courseTable = new JTable(model);
        // courseTable.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());
        // courseTable.addMouseListener(new ButtonClickListener(courseTable, loggedInUsername));

        JScrollPane scrollPane = new JScrollPane(courseTable);

        // butona per top rated courses dhe kurset ne kalendar
        JButton topRatedButton = new JButton("Top Rated Courses");
        JButton calendarButton = new JButton("Courses in Calendar");
        calendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new CourseCalendarFrame(courseManager, courses, connection);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        topRatedButton.addActionListener(e -> viewTopRatedCourses());


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(topRatedButton);
        buttonPanel.add(calendarButton);

        // shtimi i komponenteve ne frame
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void viewTopRatedCourses() {
        List<String> topRatedCourses = courseManager.getTopRatedCourses();

        // Creating a new frame to display top-rated courses
        JFrame topRatedFrame = new JFrame("Top Rated Courses");
        topRatedFrame.setSize(600, 400);
        topRatedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // tabela me top rated courses
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Course Name");

        for (int i=0;i< topRatedCourses.size();i++) {
            model.addRow(new Object[]{topRatedCourses.get(i)});
            if(i==7)
                break;
        }

        JTable topRatedTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(topRatedTable);

        topRatedFrame.add(scrollPane);
        topRatedFrame.setVisible(true);
    }



    private class CourseTableModel extends AbstractTableModel {
        private List<String> courses;
        private String[] columnNames = {"Course Name", "Details"};

        public CourseTableModel(List<String> courses) {
            this.courses = courses;
        }

        @Override
        public int getRowCount() {
            return courses.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return courses.get(rowIndex);
            } else {
                JButton button = new JButton("View Details");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle view details action
                        String courseName = courses.get(rowIndex);
                        viewCourseDetails(courseName);
                    }
                });
                return button;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

    private void viewCourseDetails(String courseName) {

        new CourseDetailsFrame(courseManager, feedbackManager, loggedInUsername, courseName);
    }
}