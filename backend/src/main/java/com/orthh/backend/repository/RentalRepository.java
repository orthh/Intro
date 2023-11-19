package com.orthh.backend.repository;

import org.apache.ibatis.annotations.Mapper;

import com.orthh.backend.domain.Rental;

@Mapper
public interface RentalRepository {
    public void save(Rental request);

}
