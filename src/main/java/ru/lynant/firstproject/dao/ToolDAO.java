package ru.lynant.firstproject.dao;

import org.springframework.stereotype.Component;
import ru.lynant.firstproject.models.Person;
import ru.lynant.firstproject.models.Tool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ToolDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/tool_rental";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "2198";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tool> list() {
        List<Tool> toolList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM tool";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Tool tool = new Tool();

                tool.setId(resultSet.getInt("id"));
                tool.setName(resultSet.getString("name"));
                tool.setBrand(resultSet.getString("brand"));
                tool.setPrice(resultSet.getInt("price"));

                toolList.add(tool);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toolList;
    }

    public Tool show(int id) {
        Tool tool = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM tool WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            tool = new Tool();

            tool.setId(resultSet.getInt("id"));
            tool.setName(resultSet.getString("name"));
            tool.setBrand(resultSet.getString("brand"));
            tool.setPrice(resultSet.getInt("price"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tool;
    }

    public void save(Tool tool) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO tool VALUES(1, ?, ?, ? )");

            preparedStatement.setString(1, tool.getName());
            preparedStatement.setString(2, tool.getBrand());
            preparedStatement.setInt(3, tool.getPrice());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Tool updateTool) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE tool SET name=?, brand=?, price=? WHERE id=?");

            preparedStatement.setString(1, updateTool.getName());
            preparedStatement.setString(2, updateTool.getBrand());
            preparedStatement.setInt(3, updateTool.getPrice());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM  tool WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
