package ru.numbDev.mapitresource.model.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document("file_point")
public class FileMongoEntity {

    @Id
    private String id;

    private byte[] file;
}
