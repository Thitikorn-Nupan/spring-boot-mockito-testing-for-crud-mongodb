package com.ttknpdev.learnspringbootcrudmongodb.exception.handler;


public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message) {
        super(message);
    }
}
