package ru.numbDev.mapitresource.service.impls;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.numbDev.mapitresource.common.ApiException;
import ru.numbDev.mapitresource.convertor.PointConvertor;
import ru.numbDev.mapitresource.model.file.FileEntity;
import ru.numbDev.mapitresource.model.file.FileMongoEntity;
import ru.numbDev.mapitresource.model.point.PointEntity;
import ru.numbDev.mapitresource.pojo.Filter;
import ru.numbDev.mapitresource.pojo.Point;
import ru.numbDev.mapitresource.pojo.Points;
import ru.numbDev.mapitresource.repository.mongo.FileMongoRepository;
import ru.numbDev.mapitresource.repository.postgres.FileRepository;
import ru.numbDev.mapitresource.repository.postgres.PointRepository;
import ru.numbDev.mapitresource.service.apis.CurrentUserService;
import ru.numbDev.mapitresource.service.apis.PointService;
import ru.numbDev.mapitresource.utils.ThrowUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class PointServiceImpl implements PointService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PointRepository pointRepository;
    private final CurrentUserService currentUserService;
    private final FileMongoRepository fileMongoRepository;
    private final FileRepository fileRepository;

    private final PointConvertor pointConvertor;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Points loadPoints(Filter filter) {

        var nick = currentUserService.getNickCurrentUser();

        Set<PointEntity> results = filter.isWithFriends()
                ? pointRepository.loadFriendsAndUserPoints(nick)
                : pointRepository.loadUserPoints(nick);

        Set<Point> points = results
                .stream()
                .map(pointConvertor::entityToPojo)
                .collect(Collectors.toSet());

        return new Points().addPoints(points);
    }

    @Override
    public Point loadPoint(long id) {

        if (id == 0) {
            logger.error("Point id is empty");
            throw ThrowUtils.throwEx("Point id is empty", 400);
        }

        var pointEntity = pointRepository.findById(id).orElseThrow(() -> ThrowUtils.throwEx("Point not found", 404));

        Set<byte[]> files = new HashSet<>();
        for (FileEntity file : pointEntity.getFiles()) {
            fileMongoRepository
                    .findById(file.getFileId())
                    .ifPresent(fileMongoEntity -> files.add(fileMongoEntity.getFile()));
        }

        return pointConvertor.entityToPojo(pointEntity).setFiles(files);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadPoint(Point point) throws IOException {

        if (point.getMultipart() == null || point.getMultipart().length == 0) {
            throw ThrowUtils.throwEx("Request has a files", 400);
        }

        var pointEntity = pointRepository.save(pointConvertor.pojoToEntity(point).setUser(currentUserService.getProfile().getId()));

        for (MultipartFile file : point.getMultipart()) {

            var fileMongo = fileMongoRepository.save(new FileMongoEntity().setFile(file.getBytes()));
            var fileEntity = new FileEntity()
                    .setFileId(fileMongo.getId())
                    .setPoint(pointEntity);

            fileRepository.save(fileEntity);
        }

    }

}
