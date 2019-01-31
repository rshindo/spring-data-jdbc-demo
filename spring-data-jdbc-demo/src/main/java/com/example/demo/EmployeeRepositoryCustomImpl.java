package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

//@Repository
@AllArgsConstructor
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> findByFirstNameLike(String firstName) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("firstName", "%" + firstName + "%");
        return jdbcTemplate.query("SELECT * FROM employee WHERE first_name LIKE :firstName",
                parameterSource,
                (rs, rowNum) -> Employee
                        .of(rs.getString("first_name"), rs.getString("last_name"))
                        .withId(rs.getLong("id")));
    }
}
