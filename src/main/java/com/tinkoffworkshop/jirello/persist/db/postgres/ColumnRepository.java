package com.tinkoffworkshop.jirello.persist.db.postgres;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.BoardEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ColumnRepository extends JpaRepository<ColumnEntity, Long> {

    @Override
    <S extends ColumnEntity> S save(S entity);

    @Override
    Optional<ColumnEntity> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends ColumnEntity> List<S> saveAllAndFlush(Iterable<S> entities);

    List<ColumnEntity> findColumnEntitiesByBoardEntity_IdOrderByPositionAsc(Long boardId);

    List<ColumnEntity> findColumnEntitiesByBoardEntity_IdAndPositionIsBetweenOrderByPositionAsc(Long boardEntity_id, Integer position, Integer position2);

    Optional<ColumnEntity> searchTopByBoardEntity_IdOrderByPositionDesc(Long boardEntity_id);


}
