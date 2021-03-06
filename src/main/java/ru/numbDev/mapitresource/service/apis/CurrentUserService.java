package ru.numbDev.mapitresource.service.apis;

import ru.numbDev.mapitresource.pojo.Auth;
import ru.numbDev.mapitresource.pojo.User;

public interface CurrentUserService {

    User getProfile();

    String getNickCurrentUser();

    void createUser(User user) throws Exception;

    String authUser(Auth auth);

    void updateUser(User user);

    void changeNickName(String oldNick, String newNick);

    void deleteUser();

    boolean nickIsExist(String nick);

}
