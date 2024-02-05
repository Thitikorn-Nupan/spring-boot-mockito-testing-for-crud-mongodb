package com.ttknpdev.learnspringbootcrudmongodb.service;

import java.util.List;

public interface LayerService <T> {
    List<T> reads();
    Boolean create(T obj);
    Boolean delete(Long id);
    Boolean update(T obj , Long id);
    T createForTesting(T obj);
    T read(Long id);
}
