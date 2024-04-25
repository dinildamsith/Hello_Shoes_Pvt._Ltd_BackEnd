package lk.ijse.hello_shoes_shop_backend.Service;

import lk.ijse.hello_shoes_shop_backend.Dto.EmployeeDto;

import java.util.ArrayList;

public interface EmployeeService {
    String saveEmployee(EmployeeDto employeeDto);
    void updateEmployee(String updateEmpId , EmployeeDto employeeDto);

}
