package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.EmployeeRepo;
import lk.ijse.hello_shoes_shop_backend.Dao.UserRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.EmployeeDto;
import lk.ijse.hello_shoes_shop_backend.Dto.MailDto;
import lk.ijse.hello_shoes_shop_backend.Service.AuthenticationService;
import lk.ijse.hello_shoes_shop_backend.Service.EmployeeService;
import lk.ijse.hello_shoes_shop_backend.Service.JWTService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.EmployeeEntity;
import lk.ijse.hello_shoes_shop_backend.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceIMPL implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JWTService jwtService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    DataConvert dataConvert;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeEntity> allEmployees = employeeRepo.findAll();
        return dataConvert.employeeEntityListConvertEmployeeDtoList(allEmployees);

    }

    @Override
    public String saveEmployee(EmployeeDto employeeDto) {
        EmployeeEntity savedEmployee = employeeRepo.save(dataConvert.employeeDtoConvertEmployeeEntity(employeeDto));

        if (savedEmployee != null) {

            String password = employeeDto.getEmployeeName() + "1234";
            UserEntity userEntity = new UserEntity();
            EmployeeEntity employeeEntity = new EmployeeEntity();
            MailDto mailDto = new MailDto();

            userEntity.setEmail(employeeDto.getEmail());
            userEntity.setName(employeeDto.getEmployeeName());
            userEntity.setPassword(passwordEncoder.encode(password));
            userEntity.setRole(employeeDto.getRole());
            employeeEntity.setEmployeeCode(employeeDto.getEmployeeCode());
            userEntity.setEmployeeEntity(employeeEntity);

            System.out.println(employeeEntity);
            System.out.println(userEntity);

            String token = jwtService.generateToken(userEntity);
            userRepo.save(userEntity);

            mailDto.setTo(employeeDto.getEmail());
            mailDto.setSubject("Hello "+employeeDto.getEmployeeName() + " You Register Done Hello Shoe System Management");
            mailDto.setMsg("Token : "+token + "\n" + "Password  : " + password + "\n"+ "Email  : " + employeeDto.getEmail());
            Thread thread = new Thread(mailDto);
            thread.start();
            return "Employee Save Done.";
        } else {
            System.out.println("Employee save unsuccessful");
        }

        return null;


    }

    @Override
    public EmployeeDto searchEmployee(String searchEmployeeId) {
        EmployeeEntity employeeEntity = employeeRepo.findById(searchEmployeeId).orElse(null);
        if (employeeEntity !=null){
            return dataConvert.employeeEntityConvertEmployeeDto(employeeEntity);
        }else {
            System.out.println("this id have no supplier");
        }return null;

    }

    @Override
    public void updateEmployee(String updateEmpId, EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = employeeRepo.findById(updateEmpId).orElse(null);
        UserEntity userEntity = userRepo.findByEmail(employeeEntity.getEmail()).orElse(null);

        if (employeeEntity != null) {
            employeeEntity.setEmployeeName(employeeDto.getEmployeeName());
            employeeEntity.setEmployeePic(employeeDto.getEmployeePic());
            employeeEntity.setGender(employeeDto.getGender());
            employeeEntity.setStatus(employeeDto.getStatus());
            employeeEntity.setDesignation(employeeDto.getDesignation());
            employeeEntity.setRole(employeeDto.getRole());
            employeeEntity.setBirthDay(employeeDto.getBirthDay());
            employeeEntity.setJoinDate(employeeDto.getJoinDate());
            employeeEntity.setAttachedBranch(employeeDto.getAttachedBranch());
            employeeEntity.setAddress1(employeeDto.getAddress1());
            employeeEntity.setAddress2(employeeDto.getAddress2());
            employeeEntity.setAddress3(employeeDto.getAddress3());
            employeeEntity.setAddress4(employeeDto.getAddress4());
            employeeEntity.setAddress5(employeeDto.getAddress5());
            employeeEntity.setContact(employeeDto.getContact());
            employeeEntity.setGuardianName(employeeDto.getGuardianName());
            employeeEntity.setEmergencyContact(employeeDto.getEmergencyContact());

            userEntity.setRole(employeeDto.getRole());

            employeeRepo.save(employeeEntity);
            userRepo.save(userEntity);
        }
    }

    @Override
    public String deleteEmployee(String email, String id)  {
        UserEntity userEntity = userRepo.findByEmail(email).orElse(null);
        EmployeeEntity employeeEntity = employeeRepo.findById(id).orElse(null);


        if (userEntity == null && employeeEntity == null){
            return "this Id have no Employee";
        }else{
            userRepo.delete(userEntity);
            employeeRepo.delete(employeeEntity);
            return "Delete Employee";
        }


    }
}