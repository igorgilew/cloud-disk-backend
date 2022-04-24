package com.storage.cloud.mapper;

public interface ModelMapper <E, D>{
    D getDto (E entity);
}
