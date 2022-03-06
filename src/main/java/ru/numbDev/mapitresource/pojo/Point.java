package ru.numbDev.mapitresource.pojo;

import lombok.Data;

import java.util.Set;

@Data
public class Point {

    private String longitude;
    private String latitude;

    private Set<byte[]> files;
    private String comments;

}
