package pl.fitnote.sleep;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SleepDto {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private Float rating;
    private Integer awakeningsNumber;
    private String note;
    private LocalDateTime creationDate;
}
