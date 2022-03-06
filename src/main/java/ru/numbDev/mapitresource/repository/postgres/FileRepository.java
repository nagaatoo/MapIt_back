package ru.numbDev.mapitresource.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.numbDev.mapitresource.model.file.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
