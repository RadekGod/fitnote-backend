package pl.fitnote.sleep;

import org.springframework.stereotype.Service;
import pl.fitnote.user.User;

@Service
class SleepFactory {

    Sleep createSleepFromDto(SleepDto source, User user) {
        return Sleep.builder()
                .id(source.getId())
                .rating(source.getRating())
                .awakeningsCount(source.getAwakeningsCount())
                .note(source.getNote())
                .measurementDate(source.getMeasurementDate())
                .user(user)
                .build();
    }

    Sleep updateSleepWithDto(Sleep toUpdate, SleepDto source) {
        return toUpdate.toBuilder()
                .id(source.getId())
                .rating(source.getRating())
                .awakeningsCount(source.getAwakeningsCount())
                .note(source.getNote())
                .measurementDate(source.getMeasurementDate())
                .build();
    }
}
