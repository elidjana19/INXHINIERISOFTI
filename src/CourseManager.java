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




}
