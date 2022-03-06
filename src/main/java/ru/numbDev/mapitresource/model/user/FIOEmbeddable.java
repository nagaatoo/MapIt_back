package ru.numbDev.mapitresource.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class FIOEmbeddable {

    private String name;

    private String lastName;

    private String middleName;

}
