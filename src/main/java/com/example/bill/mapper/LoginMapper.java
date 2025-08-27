package com.example.bill.mapper;

import com.example.bill.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
  UserVO findByUsername(String username);
}
