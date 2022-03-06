package ru.numbDev.mapitresource.service.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.numbDev.mapitresource.service.apis.PointService;

@Component
@AllArgsConstructor
public class PointFacade {

    private final PointService pointService;

    public PointService getPointService() {
        return pointService;
    }
}
