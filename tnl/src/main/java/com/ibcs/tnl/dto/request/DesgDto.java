package com.ibcs.tnl.dto.request;

import com.ibcs.tnl.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DesgDto extends BaseDto {

    private String name;

    private boolean active;
}
