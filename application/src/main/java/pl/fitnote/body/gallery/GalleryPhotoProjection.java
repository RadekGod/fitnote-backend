package pl.fitnote.body.gallery;

import pl.fitnote.commons.file.ApplicationFileProjection;

interface GalleryPhotoProjection {
    Long getId();

    String getNote();

    ApplicationFileProjection getApplicationFile();
}
