//package com.ibcs.attendance.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "ATTN_Daiy_Proc")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class AttnDailyProc extends BaseEntity {
//
//    public enum Src {
//        BIOMETRIC,ADMIN
//    }
//
//    @Enumerated(EnumType.STRING)
//    @Column(length = 9, nullable = false)
//    private Src src;
//
//    @Column(name = "HR_EMP_ID", nullable = false)
//    private Long employeeId;
//
//    @Column(name = "On_Date", nullable = false)
//    private LocalDate onDate;
//
//    /*inMoment;
//
//    outMoment;
//
//    TotalWorkingHr;
//
//    LateMin;
//
//    earlyOutMin;*/
//
//
//
//
//
//}
