package com.tinkoffworkshop.jirello.persist.db.postgres;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findAllByColumnEntity_Id(Long columnEntity_id);

    List<TaskEntity> getTaskEntitiesByColumnEntity_IdOrderByPositionAsc(Long columnEntity_id);
}
