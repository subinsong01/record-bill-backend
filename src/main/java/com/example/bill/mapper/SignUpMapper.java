package com.example.bill.mapper;

import com.example.bill.dto.request.SignUpRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SignUpMapper {
  int signUp(SignUpRequest req);
}
