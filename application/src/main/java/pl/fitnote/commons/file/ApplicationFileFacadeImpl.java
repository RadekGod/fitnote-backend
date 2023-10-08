package pl.fitnote.commons.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
class ApplicationFileFacadeImpl implements ApplicationFileFacade {
    private final ApplicationFileQueryRepository applicationFileQueryRepository;
    private final ApplicationFilePersistRepository applicationFilePersistRepository;

    @Override
    public ApplicationFile saveFile(final MultipartFile file) throws IOException {
        ApplicationFile applicationFile = ApplicationFile.builder()
                .data(file.getBytes())
                .fileName(file.getOriginalFilename())
                .creationDate(LocalDateTime.now())
                .build();
        return applicationFilePersistRepository.save(applicationFile);
    }
}
