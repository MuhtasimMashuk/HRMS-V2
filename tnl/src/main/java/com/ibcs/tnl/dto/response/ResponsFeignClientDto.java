package com.ibcs.tnl.dto.response;

import com.ibcs.tnl.dto.request.EmpDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsFeignClientDto {

    private String userMessage;

    private com.ibcs.tnl.dto.request.LeaveAppDto LeaveAppDto;

    private EmpDto empDto;

}
