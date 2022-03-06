package ru.numbDev.mapitresource.pojo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Points {

    private Set<Point> points = new HashSet<>();

    public Points addPoints(Set<Point> points) {
        this.points.addAll(points);
        return this;
    }


}
