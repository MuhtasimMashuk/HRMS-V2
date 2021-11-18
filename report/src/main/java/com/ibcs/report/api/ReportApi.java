package com.ibcs.report.api;


import com.ibcs.report.dto.request.ReportRequestDto;
import com.ibcs.report.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/report")
@Slf4j
public class ReportApi {

    @Autowired
    private ReportService reportService;


    @PostMapping("/")
    public ResponseEntity exportReport( @RequestBody ReportRequestDto ReportRequestDto) throws FileNotFoundException,
            JRException, SQLException {
        System.out.println("reportDto"+ ReportRequestDto);

        return reportService.exportReport(ReportRequestDto);
    }

}
