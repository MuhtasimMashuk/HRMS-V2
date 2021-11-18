package com.ibc.adm.api;

import com.ibc.adm.dto.request.UserDto;
import com.ibc.adm.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/user")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    Page<UserDto> all() {
        return userService.findAll(PageRequest.of(0, 10), null);
    }

    @PostMapping("/")
    UserDto newUser(@RequestBody UserDto newUser) {
        return userService.save(newUser);
    }

    @GetMapping("/{id}")
    UserDto getByID(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    UserDto replaceUser(@RequestBody UserDto newUser, @PathVariable Long id) {
        return userService.update(newUser,id);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

}
