package com.ibcs.attendance.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibcs.attendance.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttnAdminDto extends BaseDto {

    private LocalDate appDate;

    private Long userId;

    private Long empId;

    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime onMoment;

    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime in;

    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime out;

    private String remarks;

}
