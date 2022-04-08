package ru.numbDev.mapitresource.service.apis;

import org.springframework.web.multipart.MultipartFile;
import ru.numbDev.mapitresource.pojo.Filter;
import ru.numbDev.mapitresource.pojo.Point;
import ru.numbDev.mapitresource.pojo.Points;

import java.io.IOException;

public interface PointService {

    Points loadPoints(Filter filter);

    Point loadPoint(long id);

    void uploadPoint(Point point) throws IOException;

}
