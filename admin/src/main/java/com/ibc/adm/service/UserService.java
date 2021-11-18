package com.ibc.adm.service;


import com.ibc.adm.dto.request.UserDto;
import com.ibc.adm.model.User;
import com.ibc.adm.repo.UserRepo;
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
public class UserService {

    @Autowired
    private UserRepo userRepo;


    private UserDto conv(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public UserDto update(UserDto userDto, Long id) {

        User user = userRepo.getById(id);
        BeanUtils.copyProperties(userDto, user, "id");

        return conv(userRepo.save(user));
    }

    public UserDto save(UserDto userDto) {

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return conv(userRepo.save(user));
    }

    public Page<UserDto> findAll(Pageable pageable, String sText) {
        Page<User> user = userRepo.findAllCustom(pageable, sText);


        List<UserDto> sss = new ArrayList(pageable.getPageSize());
        for (User pp : user.getContent()) {
            sss.add(conv(pp));
        }

        Page<UserDto> userDtos = new PageImpl(sss, pageable, user.getTotalElements());

        return userDtos;
    }

    public UserDto findById(Long id) {

        try{
            UserDto userDto = new UserDto();
            User user = userRepo.getById(id);
            if(user == null){
                return new UserDto(null, null, null,null,null,null,false,"User not found");
            }else {
                BeanUtils.copyProperties(user, userDto);

                userDto.setUserMessage("Successfully get user information.");

                return userDto;
            }

        }  catch (Exception e) {
            log.error("Exception occurred during getting user info", e);
            return new UserDto(null, null, null,null,null,null,false,"User not found");
        }
    }

    public void deleteById(Long id) {

        userRepo.deleteById(id);
    }
}
