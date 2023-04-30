package com.tinkoffworkshop.jirello.persist.db.postgres;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

//    @Override
//    <S extends TaskEntity> S save(S entity);
//
//    @Override
//    <S extends TaskEntity> List<S> saveAllAndFlush(Iterable<S> entities);
//
//    @Override
//    Optional<TaskEntity> findById(Long aLong);
//
//    @Override
//    void deleteById(Long aLong);

    List<TaskEntity> findAllByColumnEntity_Id(Long columnEntity_id);

    List<TaskEntity> getTaskEntitiesByColumnEntity_IdOrderByPositionAsc(Long columnEntity_id);
}
