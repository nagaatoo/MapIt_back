package ru.numbDev.mapitresource.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.numbDev.mapitresource.pojo.Filter;
import ru.numbDev.mapitresource.pojo.Point;
import ru.numbDev.mapitresource.pojo.Points;
import ru.numbDev.mapitresource.service.facade.PointFacade;

import java.io.IOException;

@RestController
@RequestMapping("/point")
public class PointController {

    private final PointFacade pointFacade;

    public PointController(PointFacade pointFacade) {
        this.pointFacade = pointFacade;
    }

    @PostMapping(path = "/upload", consumes = {"multipart/form-data"})
    public void uploadPoint(@ModelAttribute Point point) throws IOException {
        pointFacade.getPointService().uploadPoint(point);
    }

    @PostMapping(path = "/get/all")
    public Points loadPoints(@RequestBody Filter filter) {
        return pointFacade.getPointService().loadPoints(filter);
    }

    @PostMapping(path = "/get/{id}")
    public Point loadPoint(@PathVariable("id") long id) {
        return pointFacade.getPointService().loadPoint(id);
    }
}
