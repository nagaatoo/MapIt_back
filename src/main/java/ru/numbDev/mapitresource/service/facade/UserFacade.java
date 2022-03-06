package ru.numbDev.mapitresource.service.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.numbDev.mapitresource.service.apis.CurrentUserService;
import ru.numbDev.mapitresource.service.apis.UserService;

@Component
@AllArgsConstructor
public class UserFacade {

    private final CurrentUserService currentUserService;
    private final UserService userService;

    public CurrentUserService getCurrentUserService() {
        return currentUserService;
    }

    public UserService getUserService() {
        return userService;
    }
}
