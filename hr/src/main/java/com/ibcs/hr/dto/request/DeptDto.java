package com.ibcs.hr.dto.request;

import com.ibcs.hr.dto.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptDto extends BaseEntityDto {

    private String code;

    private String name;

    private Long headOfId;

    private boolean active;

    private String userMessage;
}
