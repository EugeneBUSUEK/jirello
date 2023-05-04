package com.tinkoffworkshop.jirello.persist.db.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblColumn")
public class ColumnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer position;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE, mappedBy = "columnEntity")
    private List<TaskEntity> tasks;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private BoardEntity boardEntity;
}
