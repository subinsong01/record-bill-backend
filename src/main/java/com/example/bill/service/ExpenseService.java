package com.example.bill.service;

import com.example.bill.dto.request.ExpenseRequest;
import com.example.bill.dto.response.ExpenseResponse;
import com.example.bill.mapper.ExpenseMapper;
import com.example.bill.vo.ExpenseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

  private final ExpenseMapper expenseMapper;

  /** 지출 등록 */
  public ExpenseResponse createExpense(Long userId, ExpenseRequest req) {
    ExpenseVO vo = new ExpenseVO();
    vo.setUserId(userId);
    vo.setAmount(req.getAmount());
    vo.setNote(req.getNote());
    vo.setSpendDate(req.getSpendDate());
    expenseMapper.insertExpense(vo);

    return new ExpenseResponse(vo.getId(), vo.getAmount(), vo.getNote(), vo.getSpendDate());
  }

  /** 월간 지출 조회 */
  public List<ExpenseResponse> getExpensesByMonth(Long userId, int year, int month) {
    return expenseMapper.findByMonth(userId, year, month).stream()
        .map(vo -> new ExpenseResponse(vo.getId(), vo.getAmount(), vo.getNote(), vo.getSpendDate()))
        .collect(Collectors.toList());
  }

  /** 주간 지출 조회 */
  public List<ExpenseResponse> getExpensesByWeek(Long userId, int year, int week) {
    return expenseMapper.findByWeek(userId, year, week).stream()
        .map(vo -> new ExpenseResponse(vo.getId(), vo.getAmount(), vo.getNote(), vo.getSpendDate()))
        .collect(Collectors.toList());
  }

  /** 일간 지출 조회 */
  public List<ExpenseResponse> getExpensesByDay(Long userId, String date) {
    return expenseMapper.findByDay(userId, date).stream()
        .map(vo -> new ExpenseResponse(vo.getId(), vo.getAmount(), vo.getNote(), vo.getSpendDate()))
        .collect(Collectors.toList());
  }

  /** 단건 조회 */
  public ExpenseResponse getExpense(Long id) {
    ExpenseVO vo = expenseMapper.findById(id);
    return new ExpenseResponse(vo.getId(), vo.getAmount(), vo.getNote(), vo.getSpendDate());
  }

  /** 삭제 */
  public void deleteExpense(Long id) {
    expenseMapper.deleteExpense(id);
  }
}
