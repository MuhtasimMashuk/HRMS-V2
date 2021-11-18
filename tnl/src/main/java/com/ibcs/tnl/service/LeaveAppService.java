package com.ibcs.tnl.service;

import com.ibcs.tnl.dto.request.EmpDto;
import com.ibcs.tnl.dto.request.LeaveAppDto;
import com.ibcs.tnl.dto.response.ResponsFeignClientDto;
import com.ibcs.tnl.model.LeaveApp;
import com.ibcs.tnl.repo.LeaveAppRepo;
import com.ibcs.tnl.repo.LeaveTypeRepo;
import com.ibcs.tnl.client.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class LeaveAppService {

    @Autowired
    private LeaveAppRepo leaveAppRepo;

    @Autowired
    private LeaveTypeRepo leaveTypeRepo;

    @Autowired
    private Consumer consumer;



    private LeaveAppDto conv(LeaveApp leaveApp) {//converting<-------------
        LeaveAppDto leaveAppDto = new LeaveAppDto();
        BeanUtils.copyProperties(leaveApp, leaveAppDto, "leaveTypeId");
        leaveAppDto.setLeaveTypeId(leaveApp.getLeaveTypeId().getId());
        return leaveAppDto;
    }

    private LeaveApp conv(LeaveAppDto leaveAppDto) {//converting<--------------

        LeaveApp leaveApp = new LeaveApp();

        BeanUtils.copyProperties(leaveAppDto, leaveApp, "leaveTypeId");
        leaveApp.setLeaveTypeId(leaveTypeRepo.getById(leaveAppDto.getLeaveTypeId()));

        return leaveApp;
    }


    public Page<LeaveAppDto> findAll(Pageable pageable, String sText) {//all
        Page<LeaveApp> leaveApp = leaveAppRepo.findAllCustom(pageable, sText);
        //PageRequest<LeaveAppDto> deptDtos= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        List<LeaveAppDto> ss = new ArrayList(pageable.getPageSize());
        for (LeaveApp pp : leaveApp.getContent()) {
            ss.add(conv(pp));
        }

        Page<LeaveAppDto> deptDtos = new PageImpl(ss, pageable, leaveApp.getTotalElements());

        return deptDtos;
    }
    public List<LeaveAppDto> findAllList() {//---------------all

        List<LeaveApp> leaveApp = leaveAppRepo.findAll();
        //PageRequest<LeaveAppDto> deptDtos= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        List<LeaveAppDto> ss = new ArrayList();
        for (LeaveApp pp : leaveApp) {
            ss.add(conv(pp));
        }
        //List<LeaveAppDto> deptDtos = new PageImpl(ss, pageable, leaveApp.getTotalElements());
        return ss;
    }
    public ResponsFeignClientDto findUserFeignClient(Long id){
        try {
            ResponsFeignClientDto responsFeignClientDto = new ResponsFeignClientDto();
            LeaveApp leaveApp = leaveAppRepo.getById(id);
            if(leaveApp == null){
                return new ResponsFeignClientDto("User not found", null, null);
            }else {

                responsFeignClientDto.setLeaveAppDto(conv(leaveApp));

                EmpDto empDto = consumer.getEmp(leaveApp.getId());
                responsFeignClientDto.setEmpDto(empDto);
                responsFeignClientDto.setUserMessage("Successfully get user information.");

                return responsFeignClientDto;
            }
        }
        catch (Exception e) {
            log.error("Exception occurred during getting user info", e);
            return new ResponsFeignClientDto(e.getMessage(), null, null);
        }
    }

    public LeaveAppDto findById(Long id) { //byId

        try {
            LeaveAppDto leaveAppDto = new LeaveAppDto();
            LeaveApp leaveApp = leaveAppRepo.getById(id);
            if (leaveApp == null) {
                return new LeaveAppDto(null, null, null, null, null, null, null, null, null, false, null, "User not found");
            } else {
                BeanUtils.copyProperties(leaveApp, leaveAppDto);
                leaveAppDto.setUserMessage("Successfully get user information.");

                return leaveAppDto;
            }

        } catch (Exception e) {
            log.error("Exception occurred during getting user info", e);
            return new LeaveAppDto(null, null, null, null, null, null, null, null, null, false, null, "User not found");
        }
    }

    public LeaveAppDto save(LeaveAppDto leaveAppDto) {//post
        return conv(leaveAppRepo.save(conv(leaveAppDto)));
    }

    public LeaveAppDto update(LeaveAppDto leaveAppDto, Long id) {

        LeaveApp leaveApp = leaveAppRepo.getById(id);
        BeanUtils.copyProperties(leaveAppDto, leaveApp, "id");

        return conv(leaveAppRepo.save(leaveApp));
    }

    public void deleteById(Long id) {//delete
        leaveAppRepo.deleteById(id);
    }

}
