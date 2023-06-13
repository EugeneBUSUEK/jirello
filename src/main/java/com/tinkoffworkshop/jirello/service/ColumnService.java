package com.tinkoffworkshop.jirello.service;

import com.tinkoffworkshop.jirello.model.request.ColumnRequest;
import com.tinkoffworkshop.jirello.model.response.ColumnResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.BoardRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.ColumnRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.BoardEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.ColumnEntity;
import com.tinkoffworkshop.jirello.support.mapper.ColumnMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColumnService {
    private final ColumnRepository columnRepository;
    private final BoardRepository boardRepository;

    public ColumnResponse createColumnOnBoard(ColumnRequest columnRequest) {
        Optional<BoardEntity> boardEntity = boardRepository.findById(columnRequest.getBoardId());

        if (boardEntity.isEmpty()) {
            throw new RuntimeException("board not find");
        }

        Optional<ColumnEntity> latestColumn = columnRepository.searchTopByBoardEntity_IdOrderByPositionDesc(columnRequest.getBoardId());

        if (latestColumn.isPresent()) {
            columnRequest.setPosition(latestColumn.get().getPosition() + 1);
        } else {
            columnRequest.setPosition(1);
        }

        ColumnEntity columnEntity = ColumnMapper.mapToColumnEntity(columnRequest);
        var savedEntity = columnRepository.save(columnEntity);

        return ColumnMapper.mapToColumnResponse(savedEntity);
    }

    public List<ColumnResponse> getColumnsOnBoard(Long boardId) {
        List<ColumnEntity> columnEntityList = columnRepository.findColumnEntitiesByBoardEntity_IdOrderByPositionAsc(boardId);

        return columnEntityList.stream().map(ColumnMapper::mapToColumnResponse).toList();
    }


    @Transactional
    public List<ColumnResponse> deleteColumnFromBoard(Long columnId, Long boardId) {
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardId);

        if (boardEntity.isEmpty()) {
            throw new RuntimeException("board not find");
        }

        Optional<ColumnEntity> columnEntityForDelete = columnRepository.findById(columnId);

        if (columnEntityForDelete.isEmpty()) {
            throw new RuntimeException("column not find");
        }

        Integer emptyPosition = columnEntityForDelete.get().getPosition();
        List<ColumnEntity> columnEntityList = columnRepository.findColumnEntitiesByBoardEntity_IdOrderByPositionAsc(boardId);
        List<ColumnEntity> newColumnEntityList = columnEntityList.stream().filter(columnEntity -> columnEntity.getPosition() > emptyPosition).toList();

        newColumnEntityList.forEach(columnEntity -> columnEntity.setPosition(columnEntity.getPosition() - 1));

        List<ColumnEntity> savedEntities = columnRepository.saveAllAndFlush(newColumnEntityList);

        columnRepository.deleteById(columnId);

        return savedEntities.stream().map(ColumnMapper::mapToColumnResponse).toList();
    }

    public ColumnResponse updateColumnOnBoard(Long columnId, Long boardId, ColumnRequest columnRequest) {
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardId);

        if (boardEntity.isEmpty()) {
            throw new RuntimeException("board not find");
        }

        Optional<ColumnEntity> columnEntity = columnRepository.findById(columnId);

        if (columnEntity.isEmpty()) {
            throw new RuntimeException("column not find");
        }

        ColumnEntity column = ColumnMapper.mapToColumnEntity(columnRequest);

        column.setId(columnEntity.get().getId());

        ColumnEntity cE = columnRepository.save(column);

        return ColumnMapper.mapToColumnResponse(cE);
    }

    public ColumnResponse swapColumnPositions(
            Long columnId,
            Long boardId,
//            Integer positionBefore,
            Integer positionAfter) {
        Optional<ColumnEntity> columnEntity = columnRepository.findById(columnId);

        if (columnEntity.isEmpty()) {
            throw new RuntimeException("column not found");
        }

        ColumnEntity updatedColumnEntity = columnEntity.get();
        List<ColumnEntity> columnEntityList;
        Integer positionBefore = updatedColumnEntity.getPosition();

        if (positionAfter > positionBefore) {
            columnEntityList = columnRepository.findColumnEntitiesByBoardEntity_IdAndPositionIsBetweenOrderByPositionAsc(boardId, positionBefore, positionAfter);

            columnEntityList.forEach(columnEntity1 -> columnEntity1.setPosition(columnEntity1.getPosition() - 1));
        } else {
            columnEntityList = columnRepository.findColumnEntitiesByBoardEntity_IdAndPositionIsBetweenOrderByPositionAsc(boardId, positionAfter, positionBefore);

            columnEntityList.forEach(columnEntity1 -> columnEntity1.setPosition(columnEntity1.getPosition() + 1));
        }

        columnRepository.saveAllAndFlush(columnEntityList);
        updatedColumnEntity.setPosition(positionAfter);

        updatedColumnEntity = columnRepository.save(updatedColumnEntity);

        return ColumnMapper.mapToColumnResponse(updatedColumnEntity);
    }
}
