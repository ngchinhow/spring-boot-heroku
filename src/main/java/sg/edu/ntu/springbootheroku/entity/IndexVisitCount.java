package sg.edu.ntu.springbootheroku.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class IndexVisitCount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long count;

    private LocalDateTime createdAt;
}
