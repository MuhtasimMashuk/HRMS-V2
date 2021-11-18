package com.ibcs.hr.api;

import com.ibcs.hr.dto.request.DesgDto;
import com.ibcs.hr.dto.request.UserDto;
import com.ibcs.hr.service.DesgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/api/desg")

public class DesgApi {

    @Autowired
    private DesgService desgService;

    @GetMapping("/")
    Page<DesgDto> all() {

        return desgService.findAll(PageRequest.of(0, 10), null);
    }
    @GetMapping("/list/")
    List<DesgDto> getAllWithoutPage() {


        return desgService.findAllwihoutPage();
    }

    @GetMapping(value = "/{id}")
    Mono<UserDto> IdByWeb(@PathVariable("id") Integer  id) {
        return  desgService.findByUserWebId(id);
    }

    @PostMapping("/")
    DesgDto newDesg(@RequestBody DesgDto newDesg) {

        return desgService.save(newDesg);
    }

    @GetMapping("/desg/{id}")
    DesgDto one(@PathVariable Long id) {

        return desgService.findById(id);
    }

    @PutMapping("/{id}")
    DesgDto replaceDept(@RequestBody DesgDto newDesg, @PathVariable Long id) {

        return desgService.update(newDesg,id);
    }

    @DeleteMapping("/{id}")
    void deleteDesg(@PathVariable Long id) {

        desgService.deleteById(id);
    }


}
