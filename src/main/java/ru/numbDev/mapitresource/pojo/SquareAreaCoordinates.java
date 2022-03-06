package ru.numbDev.mapitresource.pojo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import ru.numbDev.mapitresource.mixin.Front;

@Data
public class SquareAreaCoordinates implements Front {

    private String topLeftLongitude;
    private String topLeftLatitude;

    private String bottomRightLongitude;
    private String bottomRightLatitude;

    @Override
    public boolean isEmpty() {
        return StringUtils.isBlank(topLeftLatitude)
                || StringUtils.isBlank(topLeftLongitude)
                || StringUtils.isBlank(bottomRightLatitude)
                || StringUtils.isBlank(bottomRightLongitude);
    }

}
