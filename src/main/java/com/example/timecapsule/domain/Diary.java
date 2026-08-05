package com.example.timecapsule.domain;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diary {
    private Long diaryId;
    private Long userId;
    private String title;
    private String content;
    private LocalDate openDate;
    private LocalDate createdAt;

}
