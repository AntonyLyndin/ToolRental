package ru.lynant.firstproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.lynant.firstproject.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> list() {
        return jdbcTemplate.query("SELECT * FROM client", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM client WHERE client_id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Person nameRental(int id) {
        return jdbcTemplate.query("SELECT client.name FROM tool LEFT JOIN client on " +
                "tool.client_id = client.client_id WHERE tool_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO client(name, yearBirth, email) VALUES(?, ?, ?)",
                person.getName(), person.getYearBirth(), person.getEmail());
    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE client SET name=?, yearBirth=?, email=? WHERE client_id=?",
                updatePerson.getName(), updatePerson.getYearBirth(), updatePerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM client WHERE client_id=?", id);
    }
}
