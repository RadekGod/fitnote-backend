package pl.fitnote.commons.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ApplicationFileFacade {
    ApplicationFile saveFile(final MultipartFile file) throws IOException;
    void updateFile(final Long fileId, final MultipartFile file) throws IOException;
}
