package com.delta.storage.utils;

import java.util.regex.Pattern;

public class BucketUtils {
    public static boolean validateBucketName(String bucket) {
        if(bucket.length() < 3 && bucket.length() > 63) return false;
        return bucket.matches("^[a-z0-9][a-z0-9-.]+[a-z0-9]$");
    }
}
