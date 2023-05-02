package com.tinkoffworkshop.jirello.persist.db.postgres;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.BoardEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.RoleEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Query(
            value = "SELECT * FROM tbl_board WHERE tbl_board.id IN (SELECT board_entity_id FROM tbl_board_users_roles WHERE users_roles_key = :userId)",
            nativeQuery = true
    )
    List<BoardEntity> findBoardEntitiesByUserId(Long userId);
}
