package com.example.bill.vo;

import lombok.Data;

@Data
public class ExpenseVO {
  private Long id;
  private Long userId;
  private String note;
  private Double amount;
  private String spendDate;
}
