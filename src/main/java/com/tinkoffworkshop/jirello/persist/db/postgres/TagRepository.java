package com.tinkoffworkshop.jirello.persist.db.postgres;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    @Override
    <S extends TagEntity> S save(S entity);

    @Override
    void deleteById(Long aLong);

    List<TagEntity> findAllByBoardEntity_Id(Long boardId);
}
