package com.tinkoffworkshop.jirello.persist.db.postgres;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColumnRepository extends JpaRepository<ColumnEntity, Long> {
    List<ColumnEntity> findColumnEntitiesByBoardEntity_IdOrderByPositionAsc(Long boardId);

    List<ColumnEntity> findColumnEntitiesByBoardEntity_IdAndPositionIsBetweenOrderByPositionAsc(Long boardEntity_id, Integer position, Integer position2);

    Optional<ColumnEntity> searchTopByBoardEntity_IdOrderByPositionDesc(Long boardEntity_id);


}
