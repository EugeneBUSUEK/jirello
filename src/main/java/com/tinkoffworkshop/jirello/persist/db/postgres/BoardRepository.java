package com.tinkoffworkshop.jirello.persist.db.postgres;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Override
    <S extends BoardEntity> S save(S entity);

    @Override
    Optional<BoardEntity> findById(Long aLong);

    @Override
    List<BoardEntity> findAll();

    @Override
    void deleteById(Long aLong);
}
