package com.ibcs.hr.service;

import com.ibcs.hr.dto.request.DesgDto;
import com.ibcs.hr.dto.request.UserDto;
import com.ibcs.hr.model.Desg;
import com.ibcs.hr.repo.DesgRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Slf4j
@Service
public class DesgService {

    @Autowired
    private DesgRepo desgRepo;


    private DesgDto conv(Desg desg) {
        DesgDto desgDto = new DesgDto();
        BeanUtils.copyProperties(desg, desgDto);
        return desgDto;
    }

    public DesgDto update(DesgDto desgDto, Long id) {

        Desg desg = desgRepo.getById(id);
        BeanUtils.copyProperties(desgDto, desg, "id");

        return conv(desgRepo.save(desg));
    }

    public DesgDto save(DesgDto desgDto) {

        Desg desg = new Desg();
        BeanUtils.copyProperties(desgDto, desg);
        return conv(desgRepo.save(desg));
    }

    public Page<DesgDto> findAll(Pageable pageable, String sText) {
        Page<Desg> desg = desgRepo.findAllCustom(pageable, sText);


        List<DesgDto> ssp = new ArrayList(pageable.getPageSize());
        for (Desg pp : desg.getContent()) {
            ssp.add(conv(pp));
        }

        Page<DesgDto> desgDtos = new PageImpl(ssp, pageable, desg.getTotalElements());

        return desgDtos;
    }
    public List<DesgDto> findAllwihoutPage() {

        List<Desg> desgs= desgRepo.findAll();
        List<DesgDto> desgDtos=new ArrayList<>();

        for (Desg desg: desgs)
        {
            DesgDto desgDto= new DesgDto();
            BeanUtils.copyProperties(desg, desgDto);
            desgDtos.add(desgDto);
        }


        return desgDtos;
    }
    public Mono<UserDto> findByUserWebId(Integer id) {

        UserDto userDto = new UserDto();


        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9091")
                .build();

        return webClient.get()
                .uri("/admin/user/" + id)
                .retrieve()
                .bodyToMono(UserDto.class);
    }

    public DesgDto findById(Long id) {

        try {
            DesgDto desgDto = new DesgDto();
            Desg desg = desgRepo.getById(id);
            if (desg == null) {
                return new DesgDto(null, false, "User not found");
            } else {

                BeanUtils.copyProperties(desg, desgDto);
                desgDto.setUserMessage("Successfully get user information.");

                return desgDto;
            }
        } catch (Exception e) {
            log.error("Exception occurred during getting user info", e);
            return new DesgDto(null, false, "User not found");
        }

    }

    public void deleteById(Long id) {

        desgRepo.deleteById(id);
    }


}
