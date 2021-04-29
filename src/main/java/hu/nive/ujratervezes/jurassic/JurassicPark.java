package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        List<String> overpopulated = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "SELECT breed " +
                    "FROM dinosaur " +
                    "WHERE expected < actual " +
                    "ORDER BY breed";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if (!overpopulated.contains(resultSet.getString("breed"))) {
                    overpopulated.add(resultSet.getString("breed"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return overpopulated;
    }
}
