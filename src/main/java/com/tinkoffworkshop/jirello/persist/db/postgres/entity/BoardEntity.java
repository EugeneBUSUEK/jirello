package com.tinkoffworkshop.jirello.persist.db.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblBoard")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy="boardEntity")
    private List<ColumnEntity> columns;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy="boardEntity")
    private List<TagEntity> tags;

    @ManyToMany
    private Map<UserEntity, RoleEntity> usersRoles;
}
