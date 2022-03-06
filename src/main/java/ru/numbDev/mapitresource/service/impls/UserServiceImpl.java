package ru.numbDev.mapitresource.service.impls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.numbDev.mapitresource.model.user.FriendsEntity;
import ru.numbDev.mapitresource.pojo.Filter;
import ru.numbDev.mapitresource.pojo.Friends;
import ru.numbDev.mapitresource.pojo.SquareAreaCoordinates;
import ru.numbDev.mapitresource.repository.postgres.PointRepository;
import ru.numbDev.mapitresource.repository.postgres.UserRepository;
import ru.numbDev.mapitresource.service.apis.UserService;
import ru.numbDev.mapitresource.utils.ThrowUtils;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final CurrentUserServiceImpl currentUserService;

    private final UserRepository userRepository;
    private final PointRepository pointRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Set<Friends> findFriends(Filter filter) {

        if (filter.getCoordinates() == null || filter.getCoordinates().isEmpty()) {
            return new HashSet<>();
        }

        Set<Friends> result = new HashSet<>();
        SquareAreaCoordinates coordinates = filter.getCoordinates();

        // TODO Друзья по координатам

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFriendToNet(long id) {
        if (id == 0) {
            throw ThrowUtils.throwEx("Friend id is empty", 400);
        }

        var nick = currentUserService.getNickCurrentUser();
        var current = userRepository.findByNick(nick).orElseThrow(() -> ThrowUtils.throwEx("Current does not exists", 404));
        var friend = userRepository.findById(id).orElseThrow(() -> ThrowUtils.throwEx("User does not exists", 404));

        var entity = new FriendsEntity()
                .setUser(current)
                .setFriend(friend);

        current.getFriends().add(entity);
        userRepository.save(current);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFriendFromNet(long id) {
        if (id == 0) {
            throw ThrowUtils.throwEx("Friend id is empty", 400);
        }

        var nick = currentUserService.getNickCurrentUser();
        var current = userRepository.findByNick(nick).orElseThrow(() -> ThrowUtils.throwEx("Current does not exists", 404));
        FriendsEntity entity = current
                .getFriends()
                .stream()
                .filter(f -> f.getFriend().getId().equals(id))
                .findFirst()
                .orElseThrow(null);

        if (entity != null) {
            current
                    .getFriends()
                    .remove(entity);
        }
    }
}
