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
        String query = "SELECT breed " +
                "FROM dinosaur " +
                "WHERE expected < actual " +
                "ORDER BY breed ASC";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                overpopulated.add(resultSet.getString("breed"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return overpopulated;
    }
}
