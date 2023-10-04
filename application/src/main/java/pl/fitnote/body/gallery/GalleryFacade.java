package pl.fitnote.body.gallery;

import org.springframework.web.multipart.MultipartFile;
import pl.fitnote.commons.UserDetails;

import java.io.IOException;
import java.util.List;

public interface GalleryFacade {
    Long addGalleryPhoto(MultipartFile image, GalleryPhotoDto photoInfo, UserDetails userDetails) throws IOException;

    List<GalleryPhotoProjection> getAllGalleryPhotos(UserDetails userDetails);
}
