package com.ibcs.tnl.service;

import com.ibcs.tnl.dto.request.LeaveTypeDto;
import com.ibcs.tnl.model.LeaveType;
import com.ibcs.tnl.repo.LeaveTypeRepo;
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
public class LeaveTypeService {


    @Autowired
    private LeaveTypeRepo leaveTypeRepo;

    private LeaveTypeDto conv(LeaveType leaveType) {
        LeaveTypeDto leaveTypeDto = new LeaveTypeDto();
        BeanUtils.copyProperties(leaveType, leaveTypeDto);
        return leaveTypeDto;
    }

    public List<LeaveTypeDto> findAllList() { //get all list<----------

        List<LeaveType> leaveType = leaveTypeRepo.findAll();
        System.out.println(leaveType);
        //PageRequest<LeaveTypeDto> leaveTypeDtos= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        List<LeaveTypeDto> ss = new ArrayList();
        for (LeaveType pp : leaveType) {
            ss.add(conv(pp));
        }



        return ss;
    }

    public LeaveTypeDto update(LeaveTypeDto leaveTypeDto, Long id) {

        LeaveType leaveType = leaveTypeRepo.getById(id);
        BeanUtils.copyProperties(leaveTypeDto, leaveType, "id");

        return conv(leaveTypeRepo.save(leaveType));
    }

    public LeaveTypeDto save(LeaveTypeDto leaveTypeDto) {

        LeaveType leaveType = new LeaveType();
        BeanUtils.copyProperties(leaveTypeDto, leaveType);
        return conv(leaveTypeRepo.save(leaveType));
    }

    public Page<LeaveTypeDto> findAll(Pageable pageable, String sText) {
        Page<LeaveType> leaveType = leaveTypeRepo.findAllCustom(pageable, sText);

        List<LeaveTypeDto> sss = new ArrayList(pageable.getPageSize());
        for (LeaveType pp : leaveType.getContent()) {
            sss.add(conv(pp));
        }

        Page<LeaveTypeDto> leaveTypeDtos = new PageImpl(sss, pageable, leaveType.getTotalElements());
        return leaveTypeDtos;
    }

    public LeaveTypeDto findById(Long id) {
        try {
            LeaveTypeDto leaveTypeDto = new LeaveTypeDto();
            LeaveType leaveType = leaveTypeRepo.getById(id);
            if (leaveType == null) {
                return new LeaveTypeDto(null, null, null, false, "User not found");
            } else {
                BeanUtils.copyProperties(leaveType, leaveTypeDto);
                leaveTypeDto.setUserMessage("Successfully get user information.");

                return leaveTypeDto;
            }

        } catch (Exception e) {
            log.error("Exception occurred during getting user info", e);
            return new LeaveTypeDto(null, null, null, false, "User not found");
        }

    }

    public void deleteById(Long id) {
        leaveTypeRepo.deleteById(id);
    }



}
