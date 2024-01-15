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

    public boolean dropCourse(String username, String courseName) {
        try {
            // kontrollon nese perdoruesi eshte tashme i rregjistruar
            String checkQuery = "SELECT * FROM registrations WHERE username = ? AND course_name = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, username);
                checkStatement.setString(2, courseName);

                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        return false; // nuk eshte i rregjistruar
                    }
                }
            }

            // heq perdoruesin nga kursi
            String dropQuery = "DELETE FROM registrations WHERE username = ? AND course_name = ?";
            try (PreparedStatement dropStatement = connection.prepareStatement(dropQuery)) {
                dropStatement.setString(1, username);
                dropStatement.setString(2, courseName);
                dropStatement.executeUpdate();
                return true; //c'rregjistrimi u be me sukses
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<String> getAllCourses() {
        List<String> courses = new ArrayList<>();
        try {
            String query = "SELECT course_name FROM courses";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    courses.add(resultSet.getString("course_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    private int getNumberOfRegisteredStudents(String courseName) {
        try {
            String countQuery = "SELECT COUNT(*) FROM registrations WHERE course_name = ?";
            try (PreparedStatement countStatement = connection.prepareStatement(countQuery)) {
                countStatement.setString(1, courseName);

                try (ResultSet resultSet = countStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Integer> getFeedbackRatings(String courseName) {
        List<Integer> ratings = new ArrayList<>();
        try {
            String ratingsQuery = "SELECT rating FROM feedback WHERE course_name = ?";
            try (PreparedStatement ratingsStatement = connection.prepareStatement(ratingsQuery)) {
                ratingsStatement.setString(1, courseName);

                try (ResultSet resultSet = ratingsStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ratings.add(resultSet.getInt("rating"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }












}
