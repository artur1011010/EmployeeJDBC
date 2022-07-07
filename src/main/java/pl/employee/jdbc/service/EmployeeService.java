package pl.employee.jdbc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.employee.jdbc.db.EmployeeDAO;
import pl.employee.jdbc.rest.dto.EmployeeDTO;
import pl.employee.jdbc.utils.EmployeeMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final EmployeeMapper employeeMapper;

    public EmployeeDTO getEmployeeById(long id) {
        return employeeMapper.mapEmployeetoDTO(employeeDAO.findById(id));
    }

    public List<EmployeeDTO> findAll() {
        return employeeDAO.findAll()
                .stream()
                .map(employeeMapper::mapEmployeetoDTO)
                .collect(Collectors.toList());
    }

    public void deleteEmployee(long id) {
        employeeDAO.deleteEmployee(id);
    }

    public void updateEmployee(EmployeeDTO employeeDTO) {
        employeeDAO.updateEmployee(employeeMapper.mapDTOtoEmployee(employeeDTO));
    }

    public void insertEmployee(EmployeeDTO employeeDTO) {
        employeeDAO.insertEmployee(employeeMapper.mapDTOtoEmployee(employeeDTO));
    }

}

