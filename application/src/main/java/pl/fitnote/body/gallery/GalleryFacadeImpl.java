package pl.fitnote.body.gallery;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.commons.file.ApplicationFileFacade;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
class GalleryFacadeImpl implements GalleryFacade {

    private final UserFacade userFacade;
    private final ApplicationFileFacade applicationFileFacade;
    private final GalleryPhotoPersistRepository galleryPhotoPersistRepository;
    private final GalleryPhotoQueryRepository galleryPhotoQueryRepository;

    @Override
    @Transactional
    public Long addGalleryPhoto(final MultipartFile image, final GalleryPhotoDto photoInfo, final UserDetails userDetails) throws IOException {
        User requestingUser = userFacade.getUser(userDetails.getEmail(), User.class);
        GalleryPhoto galleryPhotoToSave = GalleryPhoto.builder()
                .applicationFile(applicationFileFacade.saveFile(image))
                .note(photoInfo.getNote())
                .user(requestingUser)
                .build();
        return galleryPhotoPersistRepository.save(galleryPhotoToSave).getId();
    }

    @Override
    @Transactional
    public <T> List<T> getAllGalleryPhotos(final UserDetails userDetails, final Class<T> type) {
        return galleryPhotoQueryRepository.findAllByUserEmailOrderByApplicationFileCreationDateDesc(userDetails.getEmail(), type);
    }

//    @Override
//    @Transactional
//    public List<SimpleGalleryPhotoProjection> getAllGalleryPhotoDescriptions(final UserDetails userDetails) {
//        return galleryPhotoQueryRepository.findAllGalleryPhotoDescriptionsByGivenEmail(userDetails.getEmail());
//    }

//    @Override
//    @Transactional
//    public <T> List<T> getAllGalleryPhotoDescriptions(final UserDetails userDetails, final Class<T> type) {
//        return galleryPhotoQueryRepository.findAllByUserEmail(userDetails.getEmail(), type);
//    }

//    @Override
//    @Transactional
//    public SimpleGalleryPhotoProjection getLatestGalleryPhotoDescription(final UserDetails userDetails) {
//        return galleryPhotoQueryRepository.findLatestGalleryPhotoDescriptionByGivenEmail(userDetails.getEmail())
//                .orElseThrow(EntityNotFoundException::new);
//    }

    @Override
    @Transactional
    public SimpleGalleryPhotoProjection getLatestGalleryPhotoDescription(final UserDetails userDetails) {
        return galleryPhotoQueryRepository.findTop1ByUserEmailOrderByApplicationFileCreationDateDesc(userDetails.getEmail(), SimpleGalleryPhotoProjection.class)
                .orElseThrow(EntityNotFoundException::new);
    }
}
