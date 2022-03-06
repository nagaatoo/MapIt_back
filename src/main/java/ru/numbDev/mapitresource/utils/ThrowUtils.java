package ru.numbDev.mapitresource.utils;

import ru.numbDev.mapitresource.common.ApiException;

public class ThrowUtils {

    public static ApiException throwEx(String message, int code) {
        return new ApiException(message, code);
    }

}
