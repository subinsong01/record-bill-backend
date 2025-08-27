package com.example.bill.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {
  private Long id;
  private Double amount;
  private String note;
  private String spendDate;
}
