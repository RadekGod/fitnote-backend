package pl.fitnote.sleep;

import org.springframework.stereotype.Service;
import pl.fitnote.user.User;

@Service
class SleepFactory {

    Sleep createSleepFromDto(SleepDto source, User user) {
        return Sleep.builder()
                .id(source.getId())
                .startDate(source.getStartDate())
                .finishDate(source.getFinishDate())
                .rating(source.getRating())
                .awakeningsNumber(source.getAwakeningsNumber())
                .note(source.getNote())
                .creationDate(source.getCreationDate())
                .user(user)
                .build();
    }

    Sleep updateSleepWithDto(Sleep toUpdate, SleepDto source) {
        return toUpdate.toBuilder()
                .id(source.getId())
                .startDate(source.getStartDate())
                .finishDate(source.getFinishDate())
                .rating(source.getRating())
                .awakeningsNumber(source.getAwakeningsNumber())
                .note(source.getNote())
                .creationDate(source.getCreationDate())
                .build();
    }
}
