package com.tinkoffworkshop.jirello.persist.db.postgres;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Override
    <S extends UserEntity> S save(S entity);

    @Override
    Optional<UserEntity> findById(Long aLong);

    @Override
    List<UserEntity> findAll();

    @Override
    void deleteById(Long aLong);
}
