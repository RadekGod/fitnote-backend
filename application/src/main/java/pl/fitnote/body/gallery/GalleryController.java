package pl.fitnote.body.gallery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.fitnote.commons.userSessionUtils.SecurityContextUtils;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photo-gallery")
class GalleryController {

    private final GalleryFacade galleryFacade;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> addGalleryPhoto(@RequestParam(name = "image") MultipartFile image, @RequestPart(name = "photoInfo") String photoInfo) throws JsonProcessingException, IOException {
        GalleryPhotoDto galleryPhotoDto = new ObjectMapper().readValue(photoInfo, GalleryPhotoDto.class);
//
//
//        List<MultipartFile> multipartFileList = new ArrayList<>();
//        multipartFileList.add(file1);
//        multipartFileList.add(file2);
//
//        List<File> fileList = new ArrayList<>();
//
//        for (MultipartFile multipartFile : multipartFileList) {
//            fileList.add(new FileUploader(cvService).uploadCvFile(user, multipartFile));
//        }
//
//        cvService.saveUser(user, fileList);

        return new ResponseEntity<>(galleryFacade.addGalleryPhoto(image, galleryPhotoDto, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GalleryPhotoProjection>> getAllGalleryPhotos() {
        return new ResponseEntity<>(galleryFacade.getAllGalleryPhotos(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }
}
