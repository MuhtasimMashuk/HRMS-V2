package com.ibcs.attendance.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "ATTN_STAGE")
@NoArgsConstructor
@AllArgsConstructor
public class AttnStage extends BaseEntity{

    @Column(nullable = false, length = 30)
    private String empCode;

    @Column(nullable = false)
    private Long onMoment;

    @Column(nullable = false)
    private String deviceId;

    @Column(length = 200)
    private String remark;

}
