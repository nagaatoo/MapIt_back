package ru.numbDev.mapitresource.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.numbDev.mapitresource.pojo.*;
import ru.numbDev.mapitresource.service.facade.UserFacade;

import java.util.Set;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/auth")
    public String auth(@RequestBody Auth auth) {
        return userFacade.getCurrentUserService().authUser(auth);
    }

    @GetMapping
    public User getProfile() {
        return userFacade.getCurrentUserService().getProfile();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void create(@RequestBody User user) throws Exception {
        userFacade.getCurrentUserService().createUser(user);
    }

    @PostMapping("/check")
    public boolean nickIsExists(@RequestBody Nick nick) {
        return userFacade.getCurrentUserService().nickIsExist(nick.getNickName());
    }

    @PostMapping("/friends")
    public Set<Friends> findFriends(Filter filter) {
        return userFacade.getUserService().findFriends(filter);
    }
}
