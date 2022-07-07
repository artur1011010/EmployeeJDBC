package pl.employee.jdbc.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.employee.jdbc.rest.dto.EmployeeDTO;
import pl.employee.jdbc.rest.exception.BadRequestException;
import pl.employee.jdbc.service.EmployeeService;
import pl.employee.jdbc.utils.RequestValidator;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class EmployeeControllerTest {
    @Mock
    EmployeeService employeeService;

    RequestValidator requestValidator = new RequestValidator();

    EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        employeeController = new EmployeeController(employeeService, requestValidator);
    }

    @Test
    @DisplayName("should throw BadRequestException when all fields are empty - post new employee")
    public void shouldThrowBadRequestExceptionWhenAllFieldsAreEmptyPostEmployee() {
        final EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .build();
        final BadRequestException exception = assertThrows(BadRequestException.class,
                () -> employeeController.postEmployee(employeeDTO));
        Assertions.assertEquals("Missing required input!", exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), exception.getCode());
    }

    @Test
    @DisplayName("should throw BadRequestException when id is provided - post new employee")
    public void shouldThrowBadRequestExceptionWhenIdIsProvidedPostEmployee() {
        final EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .employeeId(1L)
                .build();
        final BadRequestException exception = assertThrows(BadRequestException.class,
                () -> employeeController.postEmployee(employeeDTO));
        Assertions.assertEquals("Post new employee cannot contain employee ID!", exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), exception.getCode());
    }

    @Test
    @DisplayName("should throw BadRequestException when department id is missing")
    public void shouldThrowBadRequestExceptionWhenDepIdIsMissingPostEmployee() {
        final EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .jobTitle("test")
                .firstName("test")
                .lastName("test")
                .build();
        final BadRequestException exception = assertThrows(BadRequestException.class,
                () -> employeeController.postEmployee(employeeDTO));
        Assertions.assertEquals("Missing required input!", exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), exception.getCode());
    }

    @Test
    @DisplayName("should throw BadRequestException when last name is missing")
    public void shouldThrowBadRequestExceptionWhenLastNameIsMissingPostEmployee() {
        final EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .jobTitle("test")
                .firstName("test")
                .departmentId(1L)
                .build();
        final BadRequestException exception = assertThrows(BadRequestException.class,
                () -> employeeController.postEmployee(employeeDTO));
        Assertions.assertEquals("Missing required input!", exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), exception.getCode());
    }

    @Test
    @DisplayName("should throw BadRequestException when first name is missing")
    public void shouldThrowBadRequestExceptionWhenFirstNameIsMissingPostEmployee() {
        final EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .jobTitle("test")
                .lastName("test")
                .departmentId(1L)
                .build();
        final BadRequestException exception = assertThrows(BadRequestException.class,
                () -> employeeController.postEmployee(employeeDTO));
        Assertions.assertEquals("Missing required input!", exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), exception.getCode());
    }

    @Test
    @DisplayName("should throw BadRequestException when job title is missing")
    public void shouldThrowBadRequestExceptionWhenJobTitleIsMissingPostEmployee() {
        final EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .lastName("test")
                .lastName("test")
                .departmentId(1L)
                .build();
        final BadRequestException exception = assertThrows(BadRequestException.class,
                () -> employeeController.postEmployee(employeeDTO));
        Assertions.assertEquals("Missing required input!", exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), exception.getCode());
    }

    @Test
    @DisplayName("should throw BadRequestException when id is missing")
    public void shouldThrowBadRequestExceptionWhenIdIsMissingUpdateEmployee() {
        final EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .jobTitle("test")
                .firstName("test")
                .lastName("test")
                .departmentId(1L)
                .build();
        final BadRequestException exception = assertThrows(BadRequestException.class,
                () -> employeeController.updateEmployee(employeeDTO));
        Assertions.assertEquals("Missing required input!", exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), exception.getCode());
    }

    @Test
    @DisplayName("should throw BadRequestException when department id is missing")
    public void shouldThrowBadRequestExceptionWhenDepIdIsMissingUpdateEmployee() {
        final EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .employeeId(1L)
                .jobTitle("test")
                .firstName("test")
                .lastName("test")
                .build();
        final BadRequestException exception = assertThrows(BadRequestException.class,
                () -> employeeController.updateEmployee(employeeDTO));
        Assertions.assertEquals("Missing required input!", exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), exception.getCode());
    }

    @Test
    @DisplayName("should throw BadRequestException when last name is missing")
    public void shouldThrowBadRequestExceptionWhenLastNameIsMissingUpdateEmployee() {
        final EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .employeeId(1L)
                .jobTitle("test")
                .firstName("test")
                .departmentId(1L)
                .build();
        final BadRequestException exception = assertThrows(BadRequestException.class,
                () -> employeeController.updateEmployee((employeeDTO)));
        Assertions.assertEquals("Missing required input!", exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), exception.getCode());
    }

    @Test
    @DisplayName("should throw BadRequestException when first name is missing")
    public void shouldThrowBadRequestExceptionWhenFirstNameIsMissingUpdateEmployee() {
        final EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .employeeId(1L)
                .jobTitle("test")
                .lastName("test")
                .departmentId(1L)
                .build();
        final BadRequestException exception = assertThrows(BadRequestException.class,
                () -> employeeController.updateEmployee((employeeDTO)));
        Assertions.assertEquals("Missing required input!", exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), exception.getCode());
    }

    @Test
    @DisplayName("should throw BadRequestException when job title is missing")
    public void shouldThrowBadRequestExceptionWhenJobTitleIsMissingUpdateEmployee() {
        final EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .employeeId(1L)
                .firstName("test")
                .lastName("test")
                .departmentId(1L)
                .build();
        final BadRequestException exception = assertThrows(BadRequestException.class,
                () -> employeeController.updateEmployee(employeeDTO));
        Assertions.assertEquals("Missing required input!", exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), exception.getCode());
    }

}