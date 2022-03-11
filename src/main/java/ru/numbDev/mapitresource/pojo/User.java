package ru.numbDev.mapitresource.pojo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import ru.numbDev.mapitresource.mixin.Front;

import java.util.Date;
import java.util.Objects;

@Data
public class User implements Front {

    private long id;
    private String nickname;
    private String password;
    private Boolean sex;
    private String fio;
    private Date birthday;
    private Date created;

    public boolean isEmpty() {
        return StringUtils.isBlank(nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return sex == user.sex &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(fio, user.fio) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(created, user.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, sex, fio, birthday, created);
    }

}
