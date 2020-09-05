package com.delta.storage.server;

import com.delta.storage.server.service.BucketService;
import com.delta.storage.server.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.CommonsRequestLoggingFilter;


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

    @GetMapping("/{bucket}")
    public String getBucketByName(@PathVariable String bucket) {
        System.out.println(bucket);
        return "hello bucket";
    }

    @PostMapping("/{bucket}/{key}")
    public void postObject(@PathVariable String bucket, @PathVariable String key) {
        System.out.println("here");
    }

    @PutMapping(value = "/{bucket}", produces = {"application/xml", "text/xml"})
    public void createBucket(@PathVariable String bucket) throws Exception {
        // ask store to create bucket
        System.out.println("Creating bucket");
        bucketService.createBucket(bucket);
        return;
    }

    @PutMapping(value = "/{bucket}/{key}", consumes = "application/octet-stream", produces = "application/xml")
    public void putObject(RequestEntity<byte[]> requestEntity, @PathVariable("bucket") String bucket, @PathVariable("key") String key) throws Exception {
        objectService.storeObject(bucket, key, requestEntity.getBody());
    }


}
