package pl.employee.jdbc.utils;

import org.springframework.stereotype.Component;
import pl.employee.jdbc.db.model.Employee;
import pl.employee.jdbc.rest.dto.EmployeeDTO;

@Component
public class EmployeeMapper {

    public EmployeeDTO mapEmployeetoDTO(Employee source){
        return EmployeeDTO.builder()
                .employeeId(source.getEmployeeId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .jobTitle(source.getJobTitle())
                .departmentId(source.getDepartmentId())
                .build();
    }

    public Employee mapDTOtoEmployee(EmployeeDTO source){
        return Employee.builder()
                .employeeId(source.getEmployeeId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .jobTitle(source.getJobTitle())
                .departmentId(source.getDepartmentId())
                .build();
    }
}
