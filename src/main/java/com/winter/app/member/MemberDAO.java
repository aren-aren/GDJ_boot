package com.winter.app.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberDAO {
    MemberVO getDetail(MemberVO memberVO);

    int add(MemberVO memberVO);

    @Update("UPDATE MEMBER SET PASSWORD=#{password} WHERE USERNAME=#{username}")
    int update(MemberVO memberVO);
}
