package org.example.model.entity;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class BaseEntity  {
    private long id;
    private Timestamp registerDate = Timestamp.valueOf(LocalDateTime.now());
}


