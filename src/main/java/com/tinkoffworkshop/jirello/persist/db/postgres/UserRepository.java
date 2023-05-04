package com.tinkoffworkshop.jirello.persist.db.postgres;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Override
    <S extends UserEntity> S save(S entity);

    @Override
    Optional<UserEntity> findById(Long aLong);

    Optional<UserEntity> findByEmail(String email);

    @Override
    List<UserEntity> findAll();

    @Override
    void deleteById(Long aLong);
}
