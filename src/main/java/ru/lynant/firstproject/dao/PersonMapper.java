package ru.lynant.firstproject.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.lynant.firstproject.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setYearBirth(resultSet.getInt("yearBirth"));
        person.setEmail(resultSet.getString("email"));

        return person;
    }
}
