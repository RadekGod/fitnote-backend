package pl.fitnote.commons.file;

import java.time.LocalDateTime;

public interface SimpleApplicationFileProjection {
    Long getId();
    String  getFileName();
    LocalDateTime getCreationDate();
}
