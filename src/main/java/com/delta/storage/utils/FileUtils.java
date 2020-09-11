package com.delta.storage.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static boolean exists(Path location) {
        return Files.exists(location);
    }

    public static void mkdir(Path location) throws IOException {
        Files.createDirectory(location);
    }

    public static void mkdirs(Path location) throws IOException {
        Files.createDirectories(location);
    }

    public static void writeBytes(Path location, byte[] content) throws IOException {
        Files.write(location, content);
    }

    public static void appendBytes(Path location, byte[] content) throws IOException {
        Files.write(location, content, Files.exists(location) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
    }


    public static List<Path> listFiles(Path location) throws IOException {
        List<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(location, "*")) {
            for (Path path : ds) {
                files.add(path);
            }
        }
        Comparator<Path> compare = new Comparator<Path>() {
            @Override
            public int compare(Path o1, Path o2) {
                String[] p1 = o1.toString().split("/");
                String[] p2 = o2.toString().split("/");
                return Integer.valueOf(p1[p1.length - 1]).compareTo(Integer.valueOf(p2[p2.length - 1]));
            }
        };
        Collections.sort(files, compare);
        return files;
    }

    public static void rmrf(Path location) throws IOException {
        try (Stream<Path> walk = Files.walk(location)) {
            walk.map(Path::toFile)
                    .forEach(File::delete);
        }
    }
}
