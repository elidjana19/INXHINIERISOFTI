import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseCalendarFrame extends JFrame {
    private CourseManager courseManager;
    private List<String> courses;

    private Connection connection;

    public CourseCalendarFrame(CourseManager courseManager, List<String> courses, Connection connection) throws SQLException {
        this.courseManager = courseManager;
        this.courses = courses;
        this.connection=connection;
        initializeGUI();
    }

    private void initializeGUI() throws SQLException {
        setTitle("Kalendari");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String[] timeSlots = {"8:00AM", "9:00AM", "10:00AM", "11:00AM", "12:00PM", "1:00PM", "2:00PM", "3:00PM", "4:00PM", "5:00PM"};

        Object[][] data = new Object[timeSlots.length][daysOfWeek.length + 1]; // +1 for the row header

        JTable calendarTable = new JTable(new CourseTableModel(data, daysOfWeek));
        JScrollPane scrollPane = new JScrollPane(calendarTable);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }


    private static class CourseTableModel extends AbstractTableModel {
        private Object[][] data;
        private String[] columnNames;

        public CourseTableModel(Object[][] data, String[] columnNames) {
            this.data = data;
            this.columnNames = columnNames;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }
}