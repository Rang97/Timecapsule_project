package com.example.timecapsule.mapper;

import com.example.timecapsule.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    Member findByUsername(String username);

    int countByUsername(String username);

    void insert(Member member);
}
