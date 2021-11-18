package com.ibcs.report.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDto implements Serializable {

    private String format;//pdf,xls

    private String name;//report name

    private Map<String, Object> params;

}
