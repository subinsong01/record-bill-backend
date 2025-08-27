package com.example.bill.controller;

import com.example.bill.dto.request.ExpenseRequest;
import com.example.bill.dto.response.ExpenseResponse;
import com.example.bill.dto.response.LoginResponse;
import com.example.bill.service.ExpenseService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExpenseController {

  private final ExpenseService expenseService;

  /** 지출 등록 */
  @PostMapping("/bill/record")
  public ExpenseResponse create(
      @RequestBody ExpenseRequest req,
      HttpSession session
  ) {
    Long userId = getLoginUserId(session);
    return expenseService.createExpense(userId, req);
  }

  /** 리스트 조회 (view=month|week|day 로 구분) */
  @GetMapping("/expense")
  public List<ExpenseResponse> list(
      @RequestParam String view,
      @RequestParam(required = false) Integer year,
      @RequestParam(required = false) Integer month,
      @RequestParam(required = false) Integer week,
      @RequestParam(required = false) String date,
      HttpSession session
  ) {
    Long userId = getLoginUserId(session);

    return switch (view) {
      case "month" -> expenseService.getExpensesByMonth(userId, year, month);
      case "week"  -> expenseService.getExpensesByWeek(userId, year, week);
      case "day"   -> expenseService.getExpensesByDay(userId, date);
      default -> throw new IllegalArgumentException("지원하지 않는 view 값입니다: " + view);
    };
  }

  /** 단건 조회 */
  @GetMapping("/expense/{id}")
  public ExpenseResponse getOne(@PathVariable Long id) {
    return expenseService.getExpense(id);
  }

  /** 삭제 */
  @DeleteMapping("/expense/{id}")
  public void delete(@PathVariable Long id) {
    expenseService.deleteExpense(id);
  }

  private Long getLoginUserId(HttpSession session) {
    Object user = session.getAttribute("LOGIN_USER");
    if (user == null) {
      throw new IllegalStateException("로그인이 필요합니다.");
    }
    return ((LoginResponse) user).getId();
  }
}
