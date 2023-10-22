package pl.fitnote.body.gallery;

import pl.fitnote.commons.file.SimpleApplicationFileProjection;

interface SimpleGalleryPhotoProjection {
    Long getId();

    String getNote();

    SimpleApplicationFileProjection getApplicationFile();
}
