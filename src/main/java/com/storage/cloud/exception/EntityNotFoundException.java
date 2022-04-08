package com.storage.cloud.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> clazz) {
        super(String.format("Сущность %s не найдена.", clazz.getName()));
    }
}
