package ru.numbDev.mapitresource.convertor;

import org.springframework.stereotype.Component;
import ru.numbDev.mapitresource.model.point.PointEntity;
import ru.numbDev.mapitresource.pojo.Point;

@Component
public class PointConvertor implements Convertor<PointEntity, Point> {

    @Override
    public Point entityToPojo(PointEntity entity) {
//        return modelMapper.map(entity, Point.class);
        return new Point()
                .setLatitude(entity.getLatitude())
                .setLongitude(entity.getLongitude())
                .setComments("foo");
    }

    @Override
    public PointEntity pojoToEntity(Point pojo) {
        return modelMapper.map(pojo, PointEntity.class);
    }

    @Override
    public void updateEntityFromPojo(PointEntity entity, Point pojo) {
        var proto = modelMapper.map(pojo, PointEntity.class);
        modelMapper.map(proto, entity);
    }

}
