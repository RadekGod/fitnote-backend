package pl.fitnote.commons.file;

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
@Builder(toBuilder = true)
public class ApplicationFileDto {
    private Long id;
    private String fileName;
    private LocalDateTime creationDate;
    private byte[] data;
}

