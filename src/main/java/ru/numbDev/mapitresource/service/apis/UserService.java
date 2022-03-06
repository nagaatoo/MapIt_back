package ru.numbDev.mapitresource.service.apis;

import ru.numbDev.mapitresource.pojo.Filter;
import ru.numbDev.mapitresource.pojo.Friends;

import java.util.Set;

public interface UserService {

    Set<Friends> findFriends(Filter filter);

    void addFriendToNet(long id);

    void deleteFriendFromNet(long id);

}
