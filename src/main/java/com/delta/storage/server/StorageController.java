package com.delta.storage.server;

import com.delta.storage.server.responses.InitMultipartResponse;
import com.delta.storage.server.service.BucketService;
import com.delta.storage.server.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;


@RestController
@RequestMapping()
public class StorageController {
    @Autowired
    BucketService bucketService;

    @Autowired
    ObjectService objectService;

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        return loggingFilter;
    }

    @GetMapping("/")
    public String getBuckets() {
        return "hello";
    }

    @GetMapping("/{bucket}/{key}")
    public void getBucketByName(@PathVariable String bucket, @PathVariable String key, HttpServletResponse response) throws Exception {
        System.out.println("Getting resource " + bucket + "/" + key);
        File content = objectService.getObject(bucket, key);
        InputStream inputStream = new BufferedInputStream(new FileInputStream(content));
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        long length = content.length();
        if(length <= Integer.MAX_VALUE)
        {
            response.setContentLength((int) length);
        }
        FileCopyUtils.copy(inputStream, response.getOutputStream());
        return;
    }


    @PostMapping(value = "/{bucket}/{key}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> postObject(@PathVariable String bucket, @PathVariable String key,
                                        @RequestParam Map<String, String> queryMethod) throws Exception {
        if (queryMethod.size() != 1) {
            // throw error
        }

        String method = queryMethod.entrySet().iterator().next().getKey();
        String value = queryMethod.entrySet().iterator().next().getValue();

        switch (method) {
            case "uploads": {
                System.out.println("Starting Multipart Upload");
                String uploadId = objectService.initMultipartUpload(bucket);
                InitMultipartResponse initMultipartResponse = new InitMultipartResponse(bucket, key, uploadId);
                return new ResponseEntity<>(initMultipartResponse, HttpStatus.OK);
            }
            case "uploadId": {
                System.out.println("Finishing multipart upload");
                String uploadId = value;
                System.out.println(uploadId);
                objectService.completeMultipart(bucket, key, uploadId);
            }
        }
        return new ResponseEntity<>("Not Implemented", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{bucket}", produces = {"application/xml", "text/xml"})
    public void createBucket(@PathVariable String bucket) throws Exception {
        // ask store to create bucket
        System.out.println("Creating bucket");
        bucketService.createBucket(bucket);
        return;
    }

    @PutMapping(value = "/{bucket}/{key}", consumes = "application/octet-stream", produces = "application/xml")
    public void putObject(@PathVariable("bucket") String bucket, @PathVariable("key") String key,
                          @RequestBody byte[] content,
                          @RequestParam(required = false) String uploadId,
                          @RequestParam(required = false) Integer partNumber,
                          HttpServletResponse response) throws Exception {
        if (uploadId == null) {
            objectService.storeObject(bucket, key, content);
        } else if (uploadId != null && partNumber != null) {
            System.out.println("Doing multipart upload");
            String hash = objectService.storePart(bucket, uploadId, partNumber, content);
            response.setHeader("ETag", hash);
        }
    }


}
