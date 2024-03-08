package com.winter.app.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
    MemberVO getDetail(MemberVO memberVO);

    int add(MemberVO memberVO);
}