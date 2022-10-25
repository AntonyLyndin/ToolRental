package ru.lynant.firstproject.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.lynant.firstproject.models.Tool;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToolMapper implements RowMapper<Tool> {

    @Override
    public Tool mapRow(ResultSet resultSet, int i) throws SQLException {
        Tool tool = new Tool();

        tool.setId(resultSet.getInt("id"));
        tool.setName(resultSet.getString("name"));
        tool.setBrand(resultSet.getString("brand"));
        tool.setPrice(resultSet.getInt("price"));

        return tool;
    }
}
