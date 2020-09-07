package com.delta.storage.server.service;

import com.delta.storage.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.List;

@Service
public class StoreService {
    @Value("${app.upload.dir:${user.home}/storage}")
    String root;

    final static String CONTENT_PREFIX = ".content";
    final static String METADATA_DIR = ".object_metadata";

    final static String MULTIPART_DIR = ".multipart";

    public StoreService() {

    }

    public Path getMultipartUploadDir(String bucket, String uploadId) {
        return Paths.get(root, bucket, MULTIPART_DIR, uploadId);
    }

    public void createBucket(String bucket) throws Exception {
        FileUtils.mkdirs(Paths.get(root, bucket));
    }

    public void storeObject(String bucket, String key, byte[] content) throws Exception {
        FileUtils.mkdir(Paths.get(root, bucket, key));
        FileUtils.writeBytes(Paths.get(root, bucket, key, key + ".content"), content);
    }


    public void initMultipartUpload(String bucket, String uploadId) throws Exception {
        Path uploadLocation = getMultipartUploadDir(bucket, uploadId);
        FileUtils.mkdirs(uploadLocation);
    }

    public String storePart(String bucket, String uploadId, Integer partNumber, byte[] content) throws Exception {
        Path uploadLocation = getMultipartUploadDir(bucket, uploadId);
        Path partLocation = Paths.get(uploadLocation.toString(), partNumber.toString());
        FileUtils.writeBytes(partLocation, content);
        String hash = MessageDigest.getInstance("MD5").digest(content).toString();
        return hash;
    }

    public String completMultipartUpload(String bucket, String key, String uploadId) throws Exception {
        Path uploadDir = getMultipartUploadDir(bucket, uploadId);
        List<Path> files = FileUtils.listFiles(uploadDir);

        Path objectLocation = Paths.get(root, bucket, key);
        for(Path path : files)
        {
            byte [] content = Files.readAllBytes(path);
            System.out.println("Writing part" + path.toString());
            FileUtils.appendBytes(objectLocation, content);
        }
        FileUtils.rmrf(uploadDir);
        return "test";
    }

    public File getObject(String bucket, String key) {
        return new File(Paths.get(root, bucket, key, "content").toString());
    }
}
