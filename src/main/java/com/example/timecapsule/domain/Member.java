package com.example.timecapsule.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    private Long userId;
    private String username;
    private String password;
    private String name;
    private String role;
    private LocalDate createdAt;
}
