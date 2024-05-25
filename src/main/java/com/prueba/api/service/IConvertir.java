package com.prueba.api.service;

public interface IConvertir {

    <T> T conversor(String json,Class<T> clase);
}
