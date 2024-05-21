package com.jonasSmendes.livraria.service;

public interface InterfaceConverteDados {
    <T> T obterDados (String json, Class<T> tClass);
}
