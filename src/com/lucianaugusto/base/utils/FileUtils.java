package com.lucianaugusto.base.utils;

import java.io.File;
import java.util.Optional;

public class FileUtils {

    public static Optional<String> getFileExtension(File file) {
        String name = file.getName();
        return Optional.ofNullable(name).filter(s -> s.contains(".")).map(s -> s.substring(name.lastIndexOf(".") +1 ));
    }
}
