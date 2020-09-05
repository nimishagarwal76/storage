package com.delta.storage.utils;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static void mkdirs(Path location) throws Exception
    {
        Files.createDirectories(location);
    }

    public static void writeBytes(Path location, byte[] content) throws Exception
    {
        Files.write(location, content);
    }
}
