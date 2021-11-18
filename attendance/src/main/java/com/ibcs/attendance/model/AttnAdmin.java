package com.ibcs.attendance.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@Entity
@Table(name = "ATTN_ADMIN")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AttnAdmin extends BaseEntity{

    @Temporal(TemporalType.DATE)
    @Column(name = "APP_DATE", nullable=false)
//    private LocalDate appDate;
    private Date appDate;

    @Column(name = "ADM_USER_ID", nullable = false)
    private Long userId;

    @Column(name = "HR_EMP_ID", nullable = false)
    private Long empId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ON_MOMENT", nullable=false)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "YYYY-MM-dd HH:mm:ss.Z")
//    private LocalDateTime OnMoment;
    private Date OnMoment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "_IN", nullable=false)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "YYYY-MM-dd HH:mm:ss.Z")
//    private LocalDateTime in;
    private Date in;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "_OUT", nullable=false)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "YYYY-MM-dd HH:mm:ss.Z")
//    private LocalDateTime out;
    private Date out;

    @Column(length=254)
    private String remark;


}
