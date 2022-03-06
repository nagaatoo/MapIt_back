package ru.numbDev.mapitresource.pojo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import ru.numbDev.mapitresource.mixin.Front;

import java.util.Objects;

@Data
public class FIO implements Front {

    private String name;

    private String lastName;

    private String middleName;

    public boolean isEmpty() {
        return
                StringUtils.isBlank(name) ||
                        StringUtils.isBlank(lastName) ||
                        StringUtils.isBlank(middleName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FIO fio = (FIO) o;
        return Objects.equals(name, fio.name) && Objects.equals(lastName, fio.lastName) && Objects.equals(middleName, fio.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, middleName);
    }
}
