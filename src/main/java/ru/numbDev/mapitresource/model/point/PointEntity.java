package ru.numbDev.mapitresource.model.point;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.numbDev.mapitresource.model.file.FileEntity;
import ru.numbDev.mapitresource.model.user.UserEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "point")
@Getter
@Setter
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "point_generator")
    @SequenceGenerator(name = "point_generator", sequenceName = "point_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @OneToMany(mappedBy = "point")
    @Fetch(FetchMode.SUBSELECT)
    private Set<FileEntity> files;

    @ManyToOne
    private UserEntity user;

}
