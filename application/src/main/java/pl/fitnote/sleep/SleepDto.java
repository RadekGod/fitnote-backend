package pl.fitnote.sleep;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
