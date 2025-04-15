package org.example.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class Util {
    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    public static File getCurrentThreadResource(String fileName) {
        URL ret = Thread.currentThread().getContextClassLoader().getResource(fileName);
        try{
            assert ret != null: "getCurrentThreadResource, URL is null for, " + fileName;
            String decodedPath = URLDecoder.decode(ret.getPath(),"UTF-8");
            logger.debug("Decode URL: {}",decodedPath);
            return new File(decodedPath);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
