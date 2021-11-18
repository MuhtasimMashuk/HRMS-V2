package com.ibcs.hr.dto.response;

import com.ibcs.hr.dto.request.EmpDto;
import com.ibcs.hr.dto.request.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsWebClientDto {

    private String userMessage;
    private EmpDto empDto;
    private UserDto userDto;
}
