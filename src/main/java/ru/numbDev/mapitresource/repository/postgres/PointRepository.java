package ru.numbDev.mapitresource.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.numbDev.mapitresource.model.point.PointEntity;

import java.util.Set;

@Repository
public interface PointRepository extends JpaRepository<PointEntity, Long> {

    @Query(nativeQuery = true, value = "select p.* from point p " +
            "inner join users u on (p.user_id = u.id) " +
            "inner join friends f on (f.user_id = p.id) " +
            "where u.nick_name = :nick")
    Set<PointEntity> loadFriendsAndUserPoints(@Param("nick") String nick);

    @Query(nativeQuery = true, value = "select p.* from user u " +
            "inner join point p on (u.id = p.user_id) " +
            "where u.nick_name = :nick")
    Set<PointEntity> loadUserPoints(@Param("nick") String nick);

    @Query(nativeQuery = true, value = "select p.* from user u " +
            "inner join point p on (u.id = p.user_id) " +
            "where u.nick_name = :nick")
    Set<PointEntity> findFriendsPoints(@Param("nick") String nick);


}
