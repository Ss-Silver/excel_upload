package com.back_log.excel_upload.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExcelData {

    private Integer num;
    private String name;
    private String buyer;
    private List<Order> orderList;
}
