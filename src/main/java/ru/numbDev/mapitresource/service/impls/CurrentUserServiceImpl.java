package ru.numbDev.mapitresource.service.impls;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.numbDev.mapitresource.common.ApiException;
import ru.numbDev.mapitresource.convertor.UserConvertor;
import ru.numbDev.mapitresource.model.user.UserEntity;
import ru.numbDev.mapitresource.pojo.Auth;
import ru.numbDev.mapitresource.pojo.User;
import ru.numbDev.mapitresource.pojo.UserDetailsImpl;
import ru.numbDev.mapitresource.repository.postgres.UserRepository;
import ru.numbDev.mapitresource.service.apis.CurrentUserService;
import ru.numbDev.mapitresource.utils.JwtUtils;
import ru.numbDev.mapitresource.utils.ThrowUtils;

@AllArgsConstructor
@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private final UserRepository userRepository;
    private final UserConvertor userConvertor;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public User getProfile() {
        var user = getUserEntity(getNickCurrentUser(), 403);
        return userConvertor.entityToPojo(user);
    }

    private UserEntity getUserEntity(String nick, int code) {
        return userRepository.findByNick(nick).orElseThrow(() -> new ApiException("User not exists", code));
    }

    @Override
    public String getNickCurrentUser() {
        return (
                (UserDetailsImpl) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
        ).getUsername();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(User user) throws Exception {

        if (userRepository.nickIsExist(user.getNickname())) {
            throw ThrowUtils.throwEx("Nicks is exists", 400);
        }

        userRepository.save(userConvertor.pojoToEntity(user));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String authUser(Auth auth) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(auth.nickname(), auth.password())
        );

        return jwtUtils.generateJwtToken(authentication);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(User user) {
        var currentUser = getUserEntity(getNickCurrentUser(), 400);

        userConvertor.updateEntityFromPojo(currentUser, user);
        userRepository.save(currentUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeNickName(String oldNick, String newNick) {
        if (StringUtils.isBlank(oldNick) || StringUtils.isBlank(newNick)) {
            throw ThrowUtils.throwEx("Nicks is empty", 400);
        }

        if (oldNick.equals(newNick)) {
            throw ThrowUtils.throwEx("Nicknames is same", 400);
        }

        if (!userRepository.changeNick(newNick, getProfile().getId())) {
            throw ThrowUtils.throwEx("Nicknames is exists", 400);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser() {
        userRepository.delete(getNickCurrentUser());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean nickIsExist(String nick) {
        return userRepository.nickIsExist(nick);
    }
}
