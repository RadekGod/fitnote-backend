package pl.fitnote.body.gallery;

import org.springframework.data.jpa.repository.JpaRepository;

interface GalleryPhotoPersistRepository extends JpaRepository<GalleryPhoto, Long> {
}
