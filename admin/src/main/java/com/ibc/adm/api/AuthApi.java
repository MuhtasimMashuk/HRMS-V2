package com.ibc.adm.api;

import com.ibc.adm.dto.request.ChangePasswordDto;
import com.ibc.adm.dto.request.LoginDto;
import com.ibc.adm.dto.request.RegistationDto;
import com.ibc.adm.dto.reponse.ResponseDto;
import com.ibc.adm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")

public class AuthApi {
    @Autowired
    private AuthService authService;

    // consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}
    @PostMapping(path = "/img")
    public ResponseDto photoPost( @RequestPart("photo") MultipartFile photo) {
        return authService.photo( photo);
    }

    // consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}
    @PostMapping(path = "/registation")
    public ResponseDto registation(@RequestBody RegistationDto aa) {
        return authService.registation(aa);
    }

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginDto aa) {
        return authService.login(aa);
    }

    @PostMapping("/changePassword")
    public ResponseDto changePassword(@RequestBody ChangePasswordDto aa) {
        return authService.changePassword(aa);
    }
}
