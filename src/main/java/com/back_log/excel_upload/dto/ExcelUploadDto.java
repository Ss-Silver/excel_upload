package com.back_log.excel_upload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExcelUploadDto {
    private String orderDate;
    private String orderNum;
    private String hName;
    private String name;
    private String phone;
    private String memo1;

}
