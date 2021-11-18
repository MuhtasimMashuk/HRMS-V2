package com.ibcs.hr.dto.request;
import com.ibcs.hr.dto.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseEntityDto {

    private String photo;

    private String code;

    private String name;

    private String email;

    private String mobileNo;

    private String password;

    private boolean active;
}
