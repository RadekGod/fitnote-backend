package pl.fitnote.commons.file;

import java.time.LocalDateTime;

public interface ApplicationFileProjection {
    Long getId();
    String  getFileName();
    LocalDateTime getCreationDate();
    byte[] getData();
}
