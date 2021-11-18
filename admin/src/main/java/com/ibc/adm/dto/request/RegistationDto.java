package com.ibc.adm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistationDto implements Serializable {

    //private MultipartFile photo;

    //private String photo;  // code name email mobileNo password matchPassword

    private String code;

    private String name;

    private String email;

    private String mobileNo;

    private String password;

    private String matchPassword;

}
