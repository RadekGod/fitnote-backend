package pl.fitnote.sleep;

import pl.fitnote.commons.UserDetails;

import java.util.List;

public interface SleepFacade {
    Long createSleep(SleepDto command, UserDetails userDetails);

    void updateSleep(Long sleepId, SleepDto command, UserDetails userDetails);

    List<SleepProjection> getAllSleeps(UserDetails userDetails);

    <T> T getSleep(Long sleepId, UserDetails userDetails, Class<T> type);

    void deleteSleep(Long sleepId, UserDetails userDetails);
}
