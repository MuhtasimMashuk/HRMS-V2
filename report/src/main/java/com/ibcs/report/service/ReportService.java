package com.ibcs.report.service;



import com.ibcs.report.dto.request.ReportRequestDto;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

@Slf4j
@Service
public class ReportService {

    @Autowired
    private DataSource dataSource;

    public ResponseEntity exportReport(ReportRequestDto ReportRequestDto) throws FileNotFoundException, JRException, SQLException {

        File file = ResourceUtils.getFile("D:\\Projects\\JavaProject\\IBCS-TNL\\report\\report\\" + ReportRequestDto.getName());
        System.out.println("FILE NAME:::"+file);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, ReportRequestDto.getParams(), dataSource.getConnection());

        String fileName = System.currentTimeMillis() + ".pdf";

        // if (reportDto.getFormat().equals("html")) {
        //     JasperExportManager.exportReportToHtmlFile(jasperPrint, fileName);
        // } else {
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
        // }
        File file1 = new File(fileName);

        try {
            return generateResponse(Files.readAllBytes(Paths.get(file1.getAbsolutePath())), fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ResponseEntity<byte[]> generateResponse(byte[] bytes, String filename) {

        var contentDisposition = ContentDisposition.builder("attachment").filename(filename).build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf; charset=UTF-8")
                .headers(headers)
                .body(bytes);
    }
}
