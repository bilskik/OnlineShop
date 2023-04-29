package com.bilskik.onlineshop.mapper;

public interface Mapper<E,D> {
    D toDTO(E entity);

    E toEntity(D dto);

}
