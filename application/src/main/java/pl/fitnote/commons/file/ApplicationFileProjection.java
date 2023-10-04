package pl.fitnote.commons.file;

import java.time.LocalDateTime;

public interface ApplicationFileProjection {
    Long getId();
    LocalDateTime getCreationDate();
    byte[] getData();
}
