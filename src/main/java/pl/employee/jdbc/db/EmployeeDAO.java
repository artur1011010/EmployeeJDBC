package pl.employee.jdbc.db;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.employee.jdbc.db.model.Employee;
import pl.employee.jdbc.rest.exception.NotFoundException;
import pl.employee.jdbc.utils.EmployeeRowMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EmployeeDAO {
    private final JdbcTemplate jdbcTemplate;

    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employee", new EmployeeRowMapper());
    }

    public Employee findById(final long employeeId) {
        try {
            final String sql = "select * from employee where employee_id = ?";
            return jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), employeeId);
        } catch (final EmptyResultDataAccessException e) {
            log.error("Employee with id: {} not found", employeeId);
            throw new NotFoundException("Employee not found", HttpStatus.NOT_FOUND.name());
        }
    }

    public void deleteEmployee(final long employeeId) {
        final String deleteQuery = "delete from employee where employee_id = ?";
        int updated = jdbcTemplate.update(deleteQuery, employeeId);
        if (updated == 0) {
            log.error("Employee with id: {} not found", employeeId);
            throw new NotFoundException("Employee with id: " + employeeId + " not found", HttpStatus.NOT_FOUND.name());
        }
    }

    public void updateEmployee(final Employee employeeRequest) {
        final String updateQuery = "update employee set first_name = ?, last_name =  ?, department_id = ?, job_title = ? where employee_id = ?";
        int updated = jdbcTemplate.update(updateQuery,
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartmentId(),
                employeeRequest.getJobTitle(),
                employeeRequest.getEmployeeId());
        if (updated == 0) {
            log.error("Employee with id: {} not found", employeeRequest.getEmployeeId());
            throw new NotFoundException("Employee with id: " + employeeRequest.getEmployeeId() + " not found", HttpStatus.NOT_FOUND.name());
        }
    }

    public void insertEmployee(final Employee employeeRequest) {
        final String insertQuery = "insert into employee (first_name, last_name, department_id, job_title) values (?, ?, ?, ?)";
        jdbcTemplate.update(insertQuery,
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartmentId(),
                employeeRequest.getJobTitle());
    }
}
