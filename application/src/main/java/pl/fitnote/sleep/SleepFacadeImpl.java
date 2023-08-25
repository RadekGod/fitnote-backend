package pl.fitnote.sleep;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.util.List;

@Service
@RequiredArgsConstructor
class SleepFacadeImpl implements SleepFacade {

    private final SleepFactory sleepFactory;
    private final UserFacade userFacade;
    private final SleepQueryRepository sleepQueryRepository;
    private final SleepPersistRepository sleepPersistRepository;

    @Override
    @Transactional
    public Long createSleep(SleepDto command, UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        Sleep sleepToSave = sleepFactory.createSleepFromDto(command, requestingUser);
        return sleepPersistRepository.save(sleepToSave).getId();
    }

    @Override
    @Transactional
    public void updateSleep(Long sleepId, SleepDto command, UserDetails userDetails) {
        Sleep sleepToUpdate = getSleep(sleepId, userDetails, Sleep.class);
        sleepPersistRepository.save(sleepFactory.updateSleepWithDto(sleepToUpdate, command));
    }

    @Override
    public List<SleepProjection> getAllSleeps(UserDetails userDetails) {
        return sleepQueryRepository.findAllSleepsByGivenKeycloakId(userDetails.getKeycloakId());
    }

    @Override
    public <T> T getSleep(Long sleepId, UserDetails userDetails, Class<T> type) {
        return sleepQueryRepository.findSleepByGivenIdAndKeycloakId(sleepId, userDetails.getKeycloakId(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteSleep(Long sleepId, UserDetails userDetails) {
        Sleep sleep = sleepQueryRepository.findSleepByGivenIdAndKeycloakId(sleepId, userDetails.getKeycloakId(), Sleep.class)
                .orElseThrow(EntityNotFoundException::new);
        sleepPersistRepository.delete(sleep);
    }
}
