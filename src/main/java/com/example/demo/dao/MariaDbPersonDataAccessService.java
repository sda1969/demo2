package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mariaDao")
public class MariaDbPersonDataAccessService implements PersonDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertPerson(UUID id, Person person) {
        String sql = "INSERT INTO person(id, name) VALUES(?,?)";
        return jdbcTemplate.update(sql, id.toString(), person.getName());
    }

    @Override
    public List<Person> getAllPersons() {
        String sql = "SELECT * FROM person";

        return jdbcTemplate.query(sql, new PersonMapper());
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        String sql = "SELECT * FROM person where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                sql,
                new Object[]{id.toString()},
                new int[]{Types.VARCHAR},
                new PersonMapper()));
    }

    @Override
    public int updatePersonById(UUID id, Person person) {

        Optional<Person> p = getPersonById(id);
        if (p.isEmpty()) {
            return 0;
        } else {
            deletePersonById(id);
            return insertPerson(id, person);
        }
    }

    @Override
    public int deletePersonById(UUID id) {
        return jdbcTemplate.update(
        "DELETE FROM person WHERE id = ?", id.toString());
    }

    private static final class PersonMapper implements RowMapper<Person> {

        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Person(
                    rs.getString("name"),
                    UUID.fromString(rs.getString("id"))
            );

        }
    }
}
