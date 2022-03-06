package ru.numbDev.mapitresource.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.numbDev.mapitresource.model.file.FileMongoEntity;

@Repository
public interface FileMongoRepository extends MongoRepository<FileMongoEntity, String> {
}
