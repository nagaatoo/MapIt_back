package ru.numbDev.mapitresource.model.file;

import lombok.Getter;
import lombok.Setter;
import ru.numbDev.mapitresource.model.point.PointEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "files_generator")
    @SequenceGenerator(name = "files_generator", sequenceName = "files_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;

    @Column(name = "file_id")
    private String fileId;

    @ManyToOne
    private PointEntity point;
}
