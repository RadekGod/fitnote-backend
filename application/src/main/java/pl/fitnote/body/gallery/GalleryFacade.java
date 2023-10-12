package pl.fitnote.body.gallery;

import org.springframework.web.multipart.MultipartFile;
import pl.fitnote.commons.UserDetails;

import java.io.IOException;
import java.util.List;

public interface GalleryFacade {
    Long addGalleryPhoto(MultipartFile image, GalleryPhotoDto command, UserDetails userDetails) throws IOException;

    <T> List<T> getAllGalleryPhotos(UserDetails userDetails, Class<T> type);
    <T> T getGalleryPhoto(Long galleryPhotoId, UserDetails userDetails, Class<T> type);
//    List<SimpleGalleryPhotoProjection> getAllGalleryPhotoDescriptions(UserDetails userDetails);
    SimpleGalleryPhotoProjection getLatestGalleryPhotoDescription(UserDetails userDetails);
}
