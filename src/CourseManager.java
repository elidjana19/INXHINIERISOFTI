import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseManager {
    private Connection connection;
    private Map<String, List<Integer>> feedbackRatings;
    public CourseManager(Connection connection) {
        this.connection = connection;
        feedbackRatings = new HashMap<>();
    }


    public boolean joinCourse(String username, String courseName) {
        try {
            // kontrollon nese perdoruesi eshte i regjistruar tashme
            String checkQuery = "SELECT * FROM registrations WHERE username = ? AND course_name = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, username);
                checkStatement.setString(2, courseName);

                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return false; // Already registered for the course
                    }
                }
            }

            // regjistro perdoruesin ne kurs
            String joinQuery = "INSERT INTO registrations (username, course_name) VALUES (?, ?)";
            try (PreparedStatement joinStatement = connection.prepareStatement(joinQuery)) {
                joinStatement.setString(1, username);
                joinStatement.setString(2, courseName);
                joinStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}
