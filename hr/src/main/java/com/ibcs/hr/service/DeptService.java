package com.ibcs.hr.service;

import com.ibcs.hr.dto.request.DeptDto;
import com.ibcs.hr.model.Dept;
import com.ibcs.hr.repo.DeptRepo;
import com.ibcs.hr.repo.EmpRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DeptService {

    @Autowired
    private DeptRepo deptRepo;
    @Autowired
    private EmpRepo empRepo;

    private DeptDto conv(Dept dept) {
        DeptDto deptDto = new DeptDto();
        BeanUtils.copyProperties(dept, deptDto, "headOfId");
        deptDto.setHeadOfId(dept.getHod().getId());
        return deptDto;
    }

    public DeptDto update(DeptDto deptDto, Long id) {

        Dept dept = deptRepo.getById(id);
        BeanUtils.copyProperties(deptDto, dept, "id", "headOfId");

        dept.setHod(empRepo.getById(deptDto.getHeadOfId()));

        return conv(deptRepo.save(dept));
    }

    public DeptDto save(DeptDto deptDto) {

        Dept dept = new Dept();
        BeanUtils.copyProperties(deptDto, dept, "headOfId");
        dept.setHod(empRepo.getById(deptDto.getHeadOfId()));
        return conv(deptRepo.save(dept));
    }

    public Page<DeptDto> findAll(Pageable pageable, String sText) {
        Page<Dept> dept = deptRepo.findAllCustom(pageable, sText);

        List<DeptDto> sss = new ArrayList(pageable.getPageSize());
        for (Dept pp : dept.getContent()) {
            sss.add(conv(pp));
        }

        Page<DeptDto> deptDtos = new PageImpl(sss, pageable, dept.getTotalElements());

        return deptDtos;
    }
    public List<DeptDto> findAllWithoutPage(){

        List<DeptDto> deptDtoList = new ArrayList<>();
        List<Dept> deptList = deptRepo.findAll();

        for(Dept dept: deptList){
            DeptDto deptDto=new DeptDto();
            BeanUtils.copyProperties(dept, deptDto, "headOfId");
            deptDto.setHeadOfId(dept.getHod().getId());
            deptDtoList.add(deptDto);
        }
        return deptDtoList;
    }
    public DeptDto findById(Long id) {
        try {
            DeptDto deptDto = new DeptDto();
            Dept dept = deptRepo.getById(id);
            if (!deptRepo.existsById(id)) {
                return new DeptDto(null, null, null, false, "User not found");
            } else {
                BeanUtils.copyProperties(dept, deptDto,"headOfId");
                deptDto.setHeadOfId(dept.getHod().getId());
                deptDto.setUserMessage("Successfully get user information.");

                return deptDto;
            }

        } catch (Exception e) {
            log.error("Exception occurred during getting user info", e);
            return new DeptDto(null, null, null, false, "User not found");
        }
    }

    public void deleteById(Long id) {

        deptRepo.deleteById(id);
    }

}
