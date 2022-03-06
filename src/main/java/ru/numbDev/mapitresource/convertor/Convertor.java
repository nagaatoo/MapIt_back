package ru.numbDev.mapitresource.convertor;

import org.modelmapper.ModelMapper;
import ru.numbDev.mapitresource.model.user.FIOEmbeddable;
import ru.numbDev.mapitresource.model.user.UserEntity;
import ru.numbDev.mapitresource.pojo.FIO;
import ru.numbDev.mapitresource.pojo.User;

public interface Convertor<E, F> {

    ModelMapper modelMapper = new ModelMapper();

    F entityToPojo(E entity);

    E pojoToEntity(F pojo);

    void updateEntityFromPojo(E entity, F pojo);

}
