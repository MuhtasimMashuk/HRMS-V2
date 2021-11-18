package com.ibcs.hr.service;

import com.ibcs.hr.client.UserConsumer;
import com.ibcs.hr.dto.request.EmpDto;
import com.ibcs.hr.dto.response.ResponsFeignClientDto;
import com.ibcs.hr.dto.request.UserDto;
import com.ibcs.hr.model.Emp;
import com.ibcs.hr.repo.DeptRepo;
import com.ibcs.hr.repo.DesgRepo;
import com.ibcs.hr.repo.EmpRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import javax.activation.DataSource;
import java.util.*;

@Slf4j
@Service
public class EmpService {

    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private DeptRepo deptRepo;
    @Autowired
    private DesgRepo desgRepo;
    @Autowired
    private UserConsumer consumer;

//    @Autowired
//    private DataSource dataSource;

    private EmpDto conv(Emp emp) {
        EmpDto empDto = new EmpDto();
        BeanUtils.copyProperties(emp, empDto, "deptId", "desgId", "supervisorId", "gender");
        empDto.setDeptId(emp.getDept().getId());
        empDto.setDesgId(emp.getDesg().getId());
        empDto.setSupervisorId(emp.getSupervisor().getId());
        empDto.setGender(emp.getGender().name());

        return empDto;
    }

    public EmpDto update(EmpDto empDto, Long id) {

        Emp emp = empRepo.getById(id);
        BeanUtils.copyProperties(empDto, emp, "id", "deptId", "desgId", "supervisorId", "gender");
        emp.setDept(deptRepo.getById(empDto.getDeptId()));
        emp.setDesg(desgRepo.getById(empDto.getDesgId()));
        emp.setSupervisor(empRepo.getById(empDto.getSupervisorId()));
        emp.setGender(Emp.Gender.valueOf(empDto.getGender()));

        return conv(empRepo.save(emp));
    }

    public EmpDto save(EmpDto empDto) {

        Emp emp = new Emp();
        BeanUtils.copyProperties(empDto, emp, "deptId", "desgId", "supervisorId", "gender");
        emp.setDept(deptRepo.getById(empDto.getDeptId()));
        emp.setDesg(desgRepo.getById(empDto.getDesgId()));
        emp.setSupervisor(empRepo.getById(empDto.getSupervisorId()));
        emp.setGender(Emp.Gender.valueOf(empDto.getGender()));

        return conv(empRepo.save(emp));
    }

    public List<EmpDto> findAllWithoutPage(){

        List<EmpDto> empDtoList = new ArrayList<>();
        List<Emp> empList = empRepo.findAll();

        for(Emp emp: empList){
            EmpDto empDto=new EmpDto();
            BeanUtils.copyProperties(emp, empDto, "deptId", "desgId", "supervisorId","gender","responseDto");
            empDto.setDeptId(emp.getDept().getId());
            empDto.setDesgId(emp.getDesg().getId());
            empDto.setSupervisorId(emp.getSupervisor().getId());
            empDto.setGender(emp.getGender().name());
            empDtoList.add(empDto);
        }
        return empDtoList;
    }

    public Page<EmpDto> findAll(Pageable pageable, String sText) {
        Page<Emp> emp = empRepo.findAllCustom(pageable, sText);
        //PageRequest<EmpDto> empDtos= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        List<EmpDto> ss = new ArrayList(pageable.getPageSize());
        for (Emp pp : emp.getContent()) {
            ss.add(conv(pp));
        }

        Page<EmpDto> empDtos = new PageImpl(ss, pageable, emp.getTotalElements());


        return empDtos;
    }


    public ResponsFeignClientDto findUserFeignClient(Long id) {
        try {
            ResponsFeignClientDto responsFeignClientDto = new ResponsFeignClientDto();
            Emp emp = empRepo.getById(id);
            if ( !empRepo.existsById(id)) {
                return new ResponsFeignClientDto("User not found", null, null);
            } else {

                responsFeignClientDto.setEmpDto(conv(emp));
                System.out.println("UserID:: " + emp.getUserId());
                UserDto userDto = consumer.getUser(emp.getUserId());
                System.out.println("UserConsumerID:: " + userDto);
                responsFeignClientDto.setUserDto(userDto);

                responsFeignClientDto.setUserMessage("Successfully get user information.");

                return responsFeignClientDto;                                       
            }
        } catch (Exception e) {
            log.error("Exception occurred during getting user info", e);
            return new ResponsFeignClientDto(e.getMessage(), null, null);
        }
    }

    public UserDto findByUserWebId(Long id) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9091")
                .build();

        return webClient.get()
                .uri("/admin/user/" + id)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    public EmpDto findById(Long id) {


            EmpDto empDto = new EmpDto();

            if(!empRepo.existsById(id)){

                return new EmpDto(null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, "User not found");
            } else {

                Emp emp= empRepo.getById(id);
                BeanUtils.copyProperties(emp, empDto, "deptId", "desgId", "supervisorId","gender","responseDto");
                empDto.setDeptId(emp.getDept().getId());
                empDto.setDesgId(emp.getDesg().getId());
                empDto.setSupervisorId(emp.getSupervisor().getId());
                empDto.setGender(emp.getGender().name());

                return empDto;
            }




        }


    public void deleteById(Long id) {
        empRepo.deleteById(id);
    }

}
