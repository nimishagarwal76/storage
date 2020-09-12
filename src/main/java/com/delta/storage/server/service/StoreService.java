package com.delta.storage.server.service;

import com.delta.storage.server.exceptions.BucketAlreadyExistsException;
import com.delta.storage.server.exceptions.NoSuchKeyException;
import com.delta.storage.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.List;

@Service
public class StoreService {
    @Value("${app.upload.dir:${user.home}/storage}")
    String root;

    private static final Logger logger = LoggerFactory.getLogger(StoreService.class);

    final static String CONTENT_FILE = "content";
    final static String METADATA_DIR = "metadata";

    final static String MULTIPART_DIR = ".multipart";

    public StoreService() {
    }

    public Path getMultipartUploadDir(String bucket, String uploadId) {
        return Paths.get(root, bucket, MULTIPART_DIR, uploadId);
    }

    public void createBucket(String bucket) throws Exception {
        Path location = Paths.get(root, bucket);
        if (FileUtils.exists(location)) {
            System.out.println("Error creating bucket. Already Exists");
            throw new BucketAlreadyExistsException(bucket);
        }
        FileUtils.mkdirs(location);
    }

    public void storeObject(String bucket, String key, byte[] content) throws Exception {
        Path location = Paths.get(root, bucket, key);
        FileUtils.mkdir(location);
        FileUtils.writeBytes(Paths.get(root, bucket, key, CONTENT_FILE), content);
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
        FileUtils.mkdir(objectLocation);

        Path objectContentLocation = Paths.get(root, bucket, key, CONTENT_FILE);

        MessageDigest md5 = MessageDigest.getInstance("MD5");

        for (Path path : files) {
            byte[] content = Files.readAllBytes(path);
            md5.update(content);
            System.out.println("Writing part" + path.toString());
            FileUtils.appendBytes(objectContentLocation, content);
        }
        FileUtils.rmrf(uploadDir);
        String hash = md5.digest().toString();
        return hash;
    }

    /**
     * Returns a file instance for the specified object
     *
     * @param bucket
     * @param key
     * @return File
     */
    public File getObjectFile(String bucket, String key) {
        Path location = Paths.get(root, bucket, key, CONTENT_FILE);
        if (!FileUtils.exists(location)) {
            throw new NoSuchKeyException(String.format("%s/%s", bucket, key));
        }
        return new File(location.toString());
    }
}
