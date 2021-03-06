package com.back_log.excel_upload.controller;

import com.back_log.excel_upload.dto.ExcelData;
import com.back_log.excel_upload.dto.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"엑셀 업로드"})
@RequiredArgsConstructor
@RestController
public class ExcelUploadController {

    public void upload(@RequestPart(required = false) MultipartFile file) throws IOException{


    }



    @PostMapping(path = "/test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "파일 업로드 API")
    public void test(@RequestPart(required = false) MultipartFile file) throws IOException {

        List<ExcelData> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4

            Row row = worksheet.getRow(i);

            ExcelData data = new ExcelData();

            data.setNum((int) row.getCell(0).getNumericCellValue());
            data.setName(row.getCell(1).getStringCellValue());
            data.setBuyer(row.getCell(2).getStringCellValue());

            List<Order> orderList = new ArrayList<>();
            Order order = new Order();
            order.setProdName(row.getCell(3).getStringCellValue());
            order.setQuantity((int) row.getCell(4).getNumericCellValue());
            orderList.add(order);
            data.setOrderList(orderList);

            dataList.add(data);
        }
        Map<String, ExcelData> map = new HashMap<>();

        for(int i = 0; i<dataList.size(); i++){
            StringBuilder sb = new StringBuilder();

            String buyer = dataList.get(i).getBuyer();
            String name = dataList.get(i).getName();
            sb.append(name);
            sb.append(buyer);


            map.put(sb.toString(), dataList.get(i));
        }
        System.out.println(map.get(0).toString());
        System.out.println(map.get(3).toString());
        System.out.println(map.get(5).toString());



        for (ExcelData excelData : dataList) {
            System.out.println(excelData.getName());
            System.out.println(excelData.getOrderList().toString());
        }
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello_world";
    }

}
