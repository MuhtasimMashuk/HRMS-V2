package com.ibcs.hr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="HR_DESG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Desg extends BaseEntity{

    @Column(unique=true, nullable=false, length=35)
    private String name;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean active;
}
