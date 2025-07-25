package com.users_ms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponseDto(String id, String title, String content, String id_user, LocalDateTime created, LocalDateTime updated) {
}
