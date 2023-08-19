package pl.fitnote.sleep;

import lombok.*;
import pl.fitnote.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SleepDto {

    private Long id;
    private Float rating;
    private Integer awakeningsCount;
    private String note;
    private LocalDateTime measurementDate;
    private User user;
}
