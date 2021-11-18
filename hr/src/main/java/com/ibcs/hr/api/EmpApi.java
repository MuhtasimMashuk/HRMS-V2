package com.ibcs.hr.api;

import com.ibcs.hr.dto.request.EmpDto;
import com.ibcs.hr.dto.request.UserDto;
import com.ibcs.hr.dto.response.ResponsFeignClientDto;
import com.ibcs.hr.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/emp")
@Slf4j
public class EmpApi {
    @Autowired
    private EmpService empService;

    @GetMapping("/")
    public Page<EmpDto> all() {
        return empService.findAll(PageRequest.of(0, 10), null);
    }


    @GetMapping("/list/")
    public List<EmpDto> getAllWithoutPage() {

        return empService.findAllWithoutPage();
    }
    @GetMapping("/userDetails/{id}")
    public ResponsFeignClientDto userView(@PathVariable Long id) {
        log.info("Request received for get user details for id {}", id);
        ResponsFeignClientDto response = empService.findUserFeignClient(id);
        log.info("Response return for {} object {}", id, response);
        return response;
    }

    @GetMapping("/with-user/{id}")
    UserDto ByWeb(@PathVariable Long id) {
        return  empService.findByUserWebId(id);
    }
    @GetMapping("/{id}")
    public EmpDto EmpOneView(@PathVariable Long id) {

        log.info("Request received for get user details for id {}", id);
        EmpDto response = empService.findById(id);
        log.info("Response return for {} object {}", id, response);
        return response;

    }
    @PostMapping("/")
    public EmpDto newEmp(@RequestBody EmpDto newEmp) {
        return empService.save(newEmp);
    }



    @PutMapping("/{id}")
    public EmpDto replaceEmp(@RequestBody EmpDto newEmp, @PathVariable Long id) {
        return empService.update(newEmp, id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmp(@PathVariable Long id) {
        empService.deleteById(id);
    }

}
