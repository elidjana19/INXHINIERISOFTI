import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FeedbackManager {
    private static Connection connection;

    public FeedbackManager(Connection connection) {

        this.connection = connection;

    }

    public static boolean leaveFeedback(String username, String courseName, String description, int rating) {
        try {
            // kontrollon nese perdoruesi eshte i rregjistruar
            String checkQuery = "SELECT * FROM registrations WHERE username = ? AND course_name = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, username);
                checkStatement.setString(2, courseName);

                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        return false; //nuk eshte i rregjistruar ne kurs
                    }
                }
            }

            // kontrollon nese perdoruesi ka lene feedback per kursin
            String existingFeedbackQuery = "SELECT * FROM feedback WHERE username = ? AND course_name = ?";
            try (PreparedStatement existingFeedbackStatement = connection.prepareStatement(existingFeedbackQuery)) {
                existingFeedbackStatement.setString(1, username);
                existingFeedbackStatement.setString(2, courseName);

                try (ResultSet resultSet = existingFeedbackStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return false; // ka lene njehere feedback
                    }
                }
            }

            // le nje feedback
            String leaveFeedbackQuery = "INSERT INTO feedback (username, course_name, description, rating) VALUES (?, ?, ?, ?)";
            try (PreparedStatement leaveFeedbackStatement = connection.prepareStatement(leaveFeedbackQuery)) {
                leaveFeedbackStatement.setString(1, username);
                leaveFeedbackStatement.setString(2, courseName);
                leaveFeedbackStatement.setString(3, description);
                leaveFeedbackStatement.setInt(4, rating);
                leaveFeedbackStatement.executeUpdate();
                return true; // feedbacku ruhet me sukses
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
