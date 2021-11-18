package com.ibcs.tnl.api;

import com.ibcs.tnl.dto.request.LeaveTypeDto;
import com.ibcs.tnl.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/leave-type")
public class LeaveTypeApi {

    @Autowired
    private LeaveTypeService leaveTypeService;

    @GetMapping("/")
    Page<LeaveTypeDto> all() {

        return leaveTypeService.findAll(PageRequest.of(0, 10), null);
    }
    @GetMapping("/{id}")
    LeaveTypeDto one(@PathVariable Long id) {
        return leaveTypeService.findById(id);
    }
    @PostMapping("/")
    LeaveTypeDto newDesg(@RequestBody LeaveTypeDto newLeaveTypeDto) {
        return leaveTypeService.save(newLeaveTypeDto);
    }

    @GetMapping("/list/")
    List<LeaveTypeDto> getAllList() {

        return leaveTypeService.findAllList();
    }

    @PutMapping("/{id}")
    LeaveTypeDto replaceDesg(@RequestBody LeaveTypeDto newLeaveTypeDto , @PathVariable Long id) {
//    LeaveTypeDto replaceDesg(@RequestBody Desg newDesg, @PathVariable Long id) {

        //return leaveTypeService.update(newLeaveTypeDto);
        return leaveTypeService.update(newLeaveTypeDto, id);

    }

    @DeleteMapping("/{id}")
    void deleteDesg(@PathVariable Long id) {
        leaveTypeService.deleteById(id);
    }
}
