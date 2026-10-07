package com.example.timecapsule.mapper;

import com.example.timecapsule.domain.Diary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryMapper {

    List<Diary> findByUserId(Long userId);

    Diary findByDiaryId(Long diaryId);

    void insert(Diary diary);

    void deleteById(Long diaryId);

}
