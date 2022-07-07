package pl.employee.jdbc.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.employee.jdbc.rest.dto.EmployeeDTO;
import pl.employee.jdbc.service.EmployeeService;
import pl.employee.jdbc.utils.RequestValidator;

import java.util.List;

@RequestMapping("/api")
@RestController
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final RequestValidator requestValidator;

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeList() {
        log.info("get-/employee");
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> employeeByID(@PathVariable long id) {
        log.info("get-/employee/{}", id);
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
        log.info("delete-/employee/{}", id);
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/employee")
    public ResponseEntity<Void> postEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("post-/employee");
        requestValidator.validatePost(employeeDTO);
        employeeService.insertEmployee(employeeDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/employee")
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("put-/employee");
        requestValidator.validatePut(employeeDTO);
        employeeService.updateEmployee(employeeDTO);
        return ResponseEntity.ok().build();
    }


}
