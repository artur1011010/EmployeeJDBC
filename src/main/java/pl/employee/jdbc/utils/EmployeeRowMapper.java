package pl.employee.jdbc.utils;

import org.springframework.jdbc.core.RowMapper;
import pl.employee.jdbc.db.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getLong("employee_id"));
        employee.setDepartmentId(rs.getLong("department_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setJobTitle(rs.getString("job_title"));
        return employee;
    }
}
