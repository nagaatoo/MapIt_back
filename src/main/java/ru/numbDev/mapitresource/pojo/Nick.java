package ru.numbDev.mapitresource.pojo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import ru.numbDev.mapitresource.mixin.Front;

import java.util.Objects;

@Data
public class Nick implements Front {

    private String nickName;

    @Override
    public boolean isEmpty() {
        return StringUtils.isBlank(nickName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nick nick = (Nick) o;
        return Objects.equals(nickName, nick.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName);
    }
}
