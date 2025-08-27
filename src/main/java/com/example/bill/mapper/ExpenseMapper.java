package com.example.bill.mapper;

import com.example.bill.vo.ExpenseVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExpenseMapper {
  ExpenseVO findById(Long id);
  List<ExpenseVO> findByMonth(@Param("userId") Long userId,
      @Param("year") int year,
      @Param("month") int month);

  List<ExpenseVO> findByWeek(@Param("userId") Long userId,
      @Param("year") int year,
      @Param("week") int week);

  List<ExpenseVO> findByDay(@Param("userId") Long userId,
      @Param("date") String date);

  void insertExpense(ExpenseVO expense);   // ✅ 하나만 남기기
  void deleteExpense(Long id);
}

