package pl.employee.jdbc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.employee.jdbc.db.model.Employee;
import pl.employee.jdbc.rest.dto.EmployeeDTO;
import pl.employee.jdbc.db.EmployeeDAO;
import pl.employee.jdbc.utils.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {
    @Mock
    EmployeeMapper employeeMapper;
    @Mock
    EmployeeDAO employeeDao;

    EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService(employeeDao, employeeMapper);
    }

    @Test
    @DisplayName("should return correct response from get employee by id")
    public void shouldReturnCorrectResponseFromGetEmployeeById() {
        final Employee employee = Employee.builder()
                .employeeId(1L)
                .firstName("test")
                .lastName("test")
                .build();
        final EmployeeDTO expectedResponse = EmployeeDTO.builder()
                .employeeId(1L)
                .firstName("test")
                .lastName("test")
                .build();
        Mockito.when(employeeDao.findById(1L)).thenReturn(employee);
        Mockito.when(employeeMapper.mapEmployeetoDTO(Mockito.any())).thenReturn(expectedResponse);
        final EmployeeDTO employeeResponse = Assertions.assertDoesNotThrow(() -> employeeService.getEmployeeById(1L));
        Assertions.assertEquals(expectedResponse, employeeResponse);
    }

    @Test
    @DisplayName("should return empty array when there is no employee on db")
    public void shouldReturnEmptyArrayWhenThereIsNoEmployeeOnDb() {
        Mockito.when(employeeDao.findAll()).thenReturn(new ArrayList<>());
        final List<EmployeeDTO> employeeResponses = Assertions.assertDoesNotThrow(() -> employeeService.findAll());
        Assertions.assertEquals(new ArrayList<>(), employeeResponses);
    }

    @Test
    @DisplayName("should return array of employees when found them on db")
    public void shouldReturnArrayOfEmployees() {
        final Employee employee1 = Employee.builder()
                .employeeId(1L)
                .firstName("test")
                .lastName("test")
                .build();
        final Employee employee2 = Employee.builder()
                .employeeId(2L)
                .firstName("test1")
                .lastName("test1")
                .build();
        final EmployeeDTO response1 = EmployeeDTO.builder()
                .employeeId(1L)
                .firstName("test")
                .lastName("test")
                .build();
        final EmployeeDTO response2 = EmployeeDTO.builder()
                .employeeId(2L)
                .firstName("test1")
                .lastName("test1")
                .build();
        Mockito.when(employeeMapper.mapEmployeetoDTO(employee1)).thenReturn(response1);
        Mockito.when(employeeMapper.mapEmployeetoDTO(employee2)).thenReturn(response2);
        Mockito.when(employeeDao.findAll()).thenReturn(List.of(employee1, employee2));
        final List<EmployeeDTO> employeeResponses = Assertions.assertDoesNotThrow(() -> employeeService.findAll());
        Assertions.assertEquals(List.of(response1, response2), employeeResponses);
    }

    @Test
    @DisplayName("should return correct response from insert employee")
    public void shouldReturnCorrectResponseFromInsertEmployee() {
        final Employee employee = Employee.builder()
                .employeeId(1L)
                .firstName("test")
                .lastName("test")
                .build();
        Mockito.when(employeeMapper.mapDTOtoEmployee(Mockito.any())).thenReturn(employee);
        Assertions.assertDoesNotThrow(
                () -> employeeService.insertEmployee(EmployeeDTO.builder().firstName("test").lastName("test").employeeId(1L).departmentId(1L).jobTitle("test").build()));
    }

    @Test
    @DisplayName("should return correct response from insert employee")
    public void shouldReturnCorrectResponseOnUpdateEmployee() {
        final Employee employee = Employee.builder()
                .employeeId(1L)
                .firstName("test")
                .lastName("test")
                .build();
        Mockito.when(employeeMapper.mapDTOtoEmployee(Mockito.any())).thenReturn(employee);
        Assertions.assertDoesNotThrow(
                () -> employeeService.updateEmployee(EmployeeDTO.builder().firstName("test").lastName("test").employeeId(1L).departmentId(1L).jobTitle("test").build()));
    }

    @Test
    @DisplayName("should return correct response from delete employee")
    public void shouldReturnCorrectResponseOnDeleteEmployee() {
        Assertions.assertDoesNotThrow(
                () -> employeeService.deleteEmployee(1L));
    }
}