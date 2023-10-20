package pl.fitnote.commons.file;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
class ApplicationFileFacadeImpl implements ApplicationFileFacade {
    private final ApplicationFileQueryRepository applicationFileQueryRepository;
    private final ApplicationFilePersistRepository applicationFilePersistRepository;

    @Override
    @Transactional
    public ApplicationFile saveFile(final MultipartFile file) throws IOException {
        ApplicationFile applicationFile = ApplicationFile.builder()
                .data(file.getBytes())
                .fileName(file.getOriginalFilename())
                .creationDate(LocalDateTime.now())
                .build();
        return applicationFilePersistRepository.save(applicationFile);
    }

    @Override
    @Transactional
    public void updateFile(final Long fileId, final MultipartFile file) throws IOException {
        ApplicationFile toUpdate = applicationFileQueryRepository.findById(fileId).orElseThrow(EntityNotFoundException::new);
        toUpdate = toUpdate.toBuilder()
                .data(file.getBytes())
                .fileName(file.getOriginalFilename())
                .creationDate(LocalDateTime.now())
                .build();

        applicationFilePersistRepository.save(toUpdate);
    }
}
