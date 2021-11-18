package com.ibcs.hr.client;

import com.ibcs.hr.dto.request.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ADMIN-SERVICE")
public interface UserConsumer {

    @GetMapping("/admin/user/{id}")
    public UserDto getUser(@PathVariable Long id);

}
