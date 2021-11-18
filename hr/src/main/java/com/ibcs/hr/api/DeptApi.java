package com.ibcs.hr.api;

import com.ibcs.hr.dto.request.DeptDto;
import com.ibcs.hr.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/dept")

public class DeptApi {


    @Autowired
    private DeptService deptService;

    @GetMapping("/")
    Page<DeptDto> all() {

        return deptService.findAll(PageRequest.of(0, 10), null);
    }
    @GetMapping("/list/")
    List<DeptDto> getAllWithoutPage() {

        return deptService.findAllWithoutPage();
    }

    @PostMapping("/")
    DeptDto newDept(@RequestBody DeptDto newDept) {

        return deptService.save(newDept);
    }

    @GetMapping("/{id}")
    DeptDto one(@PathVariable Long id) {

        return deptService.findById(id);
    }

    @PutMapping("/{id}")
    DeptDto replaceDept(@RequestBody DeptDto newDept, @PathVariable Long id) {

        return deptService.update(newDept, id);
    }

    @DeleteMapping("/{id}")
    void deleteDept(@PathVariable Long id) {
        deptService.deleteById(id);
    }

}
