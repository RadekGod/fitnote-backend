package pl.fitnote.body.gallery;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.fitnote.commons.userSessionUtils.SecurityContextUtils;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photo-gallery")
class GalleryController {

    private final GalleryFacade galleryFacade;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> addGalleryPhoto(@RequestParam(name = "image") MultipartFile image, @RequestPart(name = "photoInfo") String photoInfo) throws IOException {
        GalleryPhotoDto galleryPhotoDto = new ObjectMapper().readValue(photoInfo, GalleryPhotoDto.class);
        return new ResponseEntity<>(galleryFacade.addGalleryPhoto(image, galleryPhotoDto, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<List<GalleryPhotoProjection>> getAllGalleryPhotos() {
//        return new ResponseEntity<>(galleryFacade.getAllGalleryPhotos(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleGalleryPhotoProjection> getGalleryPhotoDescription(@PathVariable("id") Long galleryPhotoId) {

        try {
            return new ResponseEntity<>(galleryFacade.getGalleryPhoto(galleryPhotoId, SecurityContextUtils.getLoggedUserDetails(), SimpleGalleryPhotoProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found with given id");
        }
    }

    @GetMapping
    public ResponseEntity<List<SimpleGalleryPhotoProjection>> getAllGalleryPhotoDescriptions() {
        return new ResponseEntity<>(galleryFacade.getAllGalleryPhotos(SecurityContextUtils.getLoggedUserDetails(), SimpleGalleryPhotoProjection.class), HttpStatus.OK);
    }

    @GetMapping("/latest")
    ResponseEntity<?> getLatestGalleryPhotoDescription() {
        try {
            return new ResponseEntity<>(galleryFacade.getLatestGalleryPhotoDescription(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>("Photo not found", HttpStatus.NOT_FOUND);
        }
    }
}
