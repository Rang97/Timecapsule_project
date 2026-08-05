package com.example.timecapsule.controller;

import com.example.timecapsule.domain.Diary;
import com.example.timecapsule.service.DiaryService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/write")
    public String writeDiary(@RequestBody WriteRequest req) {
        diaryService.writeDiary(req.getUserId(), req.getTitle(), req.getContent(), req.getOpenDate());
        return "타임캡슐이 안전하게 보관되었습니다.";
    }

    @GetMapping("/list/{userID}")
    public List<Diary> getMyDiariesUSerID(@PathVariable Long userID) {
        return diaryService.getMyDiaries(userID);
    }

    @Data
    public static class WriteRequest {
        private Long userId;
        private String title;
        private String content;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate openDate;
    }
}