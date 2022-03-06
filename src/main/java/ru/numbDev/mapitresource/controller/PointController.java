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

    @PostMapping("/upload")
    public void uploadPoint(@RequestParam("files") MultipartFile[] files, @RequestBody Point point) throws IOException {
        pointFacade.getPointService().uploadPoint(point, files);
    }

    @PostMapping
    public Points loadPoints(@RequestBody Filter filter) {
        return pointFacade.getPointService().loadPoints(filter);
    }
}
