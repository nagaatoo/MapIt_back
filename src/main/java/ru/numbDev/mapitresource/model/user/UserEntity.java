package ru.numbDev.mapitresource.model.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.numbDev.mapitresource.model.point.PointEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "sex")
    private Boolean sex;

    @Embedded
    private FIOEmbeddable fio;

    @Column(name = "created")
    private Date created;

    @Column(name = "birthday")
    private Date birthday;

    @OneToMany(mappedBy = "user")
    @Fetch(FetchMode.SUBSELECT)
    private Set<PointEntity> points;

    @OneToMany
    @JoinColumn(name="friend_id")
    private Set<FriendsEntity> friends = new HashSet<>();
}
