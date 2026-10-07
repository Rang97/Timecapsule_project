package com.example.timecapsule.service;

import com.example.timecapsule.domain.Diary;
import com.example.timecapsule.mapper.DiaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryMapper diaryMapper;

    @Transactional
    public void writeDiary(Long userId, String title, String content, LocalDate openDate) {
        if (openDate == null || !openDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("오늘 이후의 미래 날짜로 설정해야 합니다.");
        }

        Diary diary = Diary.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .openDate(openDate)
                .build();

        diaryMapper.insert(diary);
    }

    @Transactional
    public void deleteDiary(Long diaryId) {
        Diary diary = diaryMapper.findByDiaryId(diaryId);

        if (diary == null) {
            throw new IllegalArgumentException("존재하지 않는 타임캡슐입니다. id=" + diaryId);
        }

        // 현재 시간이 개봉일 이전이면 삭제 불가
        if (LocalDate.now().isBefore(diary.getOpenDate())) {
            throw new IllegalStateException("개봉일 이전에는 타임캡슐을 삭제할 수 없습니다.");
        }

        diaryMapper.deleteById(diaryId); // Mapper에 deleteById 메서드가 필요합니다.
    }

    // 일기 목록 조회
    public List<Diary> getMyDiaries(long userId) {
        List<Diary> diaries = diaryMapper.findByUserId(userId);

        // 현재 시간이 개봉일 이전이면 내용 숨기기
        for (Diary diary : diaries) {
            if (LocalDate.now().isBefore(diary.getOpenDate())) {
                diary.setContent("잠겨있습니다.");
            }
        }
        return diaries;
    }

    // 일기 1개 조회
    public Diary getDiaryDetail(Long diaryId) {
        Diary diary = diaryMapper.findByDiaryId(diaryId);

        if (diary == null) {
            throw new IllegalArgumentException("존재하지 않는 타임캡슐입니다.");
        }
        if (LocalDate.now().isBefore(diary.getOpenDate())) {
            throw new IllegalStateException("잠겨있습니다.");
        }
        return diary;
    }
}