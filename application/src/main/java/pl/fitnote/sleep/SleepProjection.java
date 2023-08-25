package pl.fitnote.sleep;

import pl.fitnote.user.User;

import java.time.LocalDateTime;
public interface SleepProjection {

    Long getId();
    Float getRating();
    Integer getawakeningsNumber();
    String getNote();
    LocalDateTime getCreationDate();
    User getUser();
}
