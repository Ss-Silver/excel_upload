package com.back_log.excel_upload.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String prodName;
    private int quantity;
}
