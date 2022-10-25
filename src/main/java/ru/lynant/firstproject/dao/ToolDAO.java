package ru.lynant.firstproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.lynant.firstproject.models.Person;
import ru.lynant.firstproject.models.Tool;

import java.util.List;

@Component
public class ToolDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ToolDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tool> list() {
        return jdbcTemplate.query("SELECT * FROM tool", new BeanPropertyRowMapper<>(Tool.class));
    }

    public Tool show(int id) {
        return jdbcTemplate.query("SELECT * FROM tool WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Tool.class))
                .stream().findAny().orElse(null);
    }

    public void save(Tool tool) {
        jdbcTemplate.update("INSERT INTO tool VALUES(1, ?, ?, ?)",
                tool.getName(), tool.getBrand(), tool.getPrice());
    }

    public void update(int id, Tool updateTool) {
        jdbcTemplate.update("UPDATE tool SET name=?, brand=?, price=? WHERE id=?",
                updateTool.getName(), updateTool.getBrand(), updateTool.getPrice(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM tool WHERE id=?", id);
    }
}
