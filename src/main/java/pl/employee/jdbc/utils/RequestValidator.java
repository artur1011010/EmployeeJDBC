package pl.employee.jdbc.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.employee.jdbc.rest.dto.EmployeeDTO;
import pl.employee.jdbc.rest.exception.BadRequestException;

@Component
@Slf4j
public class RequestValidator {

    public void validatePost(final EmployeeDTO employeeDTO) {
        if (employeeDTO.getEmployeeId() != null) {
            log.error("Post new employee cannot contain employee ID!");
            throw new BadRequestException("Post new employee cannot contain employee ID!", HttpStatus.BAD_REQUEST.name());
        }
        if (employeeDTO.getDepartmentId() == null || StringUtils.isBlank(employeeDTO.getFirstName()) || StringUtils.isBlank(employeeDTO.getLastName()) || StringUtils.isBlank(employeeDTO.getJobTitle())) {
            log.error("Missing required input, request: {}", employeeDTO);
            throw new BadRequestException("Missing required input!", HttpStatus.BAD_REQUEST.name());
        }
    }

    public void validatePut(final EmployeeDTO employeeDTO) {
        if (employeeDTO.getEmployeeId() == null || employeeDTO.getDepartmentId() == null || employeeDTO.getFirstName() == null || employeeDTO.getLastName() == null || employeeDTO.getJobTitle() == null) {
            log.error("Missing required input, request: {}", employeeDTO);
            throw new BadRequestException("Missing required input!", HttpStatus.BAD_REQUEST.name());
        }
    }
}
