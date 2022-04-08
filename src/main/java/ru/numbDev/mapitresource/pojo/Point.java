package ru.numbDev.mapitresource.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@NoArgsConstructor
public class Point {

    private String longitude;
    private String latitude;

    // Для upload
    private MultipartFile[] multipart;

    // Для download
    private Set<byte[]> files;
    private String comments;

}
