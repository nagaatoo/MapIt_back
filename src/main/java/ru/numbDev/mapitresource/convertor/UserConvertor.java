package ru.numbDev.mapitresource.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.numbDev.mapitresource.model.user.FIOEmbeddable;
import ru.numbDev.mapitresource.model.user.UserEntity;
import ru.numbDev.mapitresource.pojo.FIO;
import ru.numbDev.mapitresource.pojo.User;

@Component
public class UserConvertor implements Convertor<UserEntity, User> {

    public User entityToPojo(UserEntity entity) {
        return modelMapper.map(entity, User.class);
    }

    public UserEntity pojoToEntity(User pojo) {
        return modelMapper.map(pojo, UserEntity.class);
    }

    public void updateEntityFromPojo(UserEntity entity, User pojo) {
        var proto = modelMapper.map(pojo, UserEntity.class);
        modelMapper.map(proto, entity);
    }

    public FIO fioPojoToEntity(FIOEmbeddable entity) {
        return modelMapper.map(entity, FIO.class);
    }

}
