package com.tinkoffworkshop.jirello.persist.db.postgres;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Override
    Optional<RoleEntity> findById(Long aLong);
}
