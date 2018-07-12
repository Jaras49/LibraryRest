package com.library.repository.rent;

import com.library.domain.rent.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface RentDao extends CrudRepository<Rent, Long> {

    @Override
    List<Rent> findAll();
}

