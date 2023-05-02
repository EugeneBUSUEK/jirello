package com.tinkoffworkshop.jirello.service;

import com.tinkoffworkshop.jirello.model.request.BoardRequest;
import com.tinkoffworkshop.jirello.model.response.BoardResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.BoardRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.UserRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.BoardEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.RoleEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;
import com.tinkoffworkshop.jirello.support.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRoleService userRoleService;

    public BoardResponse createBoard(BoardRequest boardRequest) {

        BoardEntity boardEntity = BoardMapper.mapToBoardEntity(
                boardRequest,
                userRoleService.getUserRoles(boardRequest.getUsers())
        );
        BoardEntity boardSaved = boardRepository.save(boardEntity);

        if (boardSaved == null) {
            throw new RuntimeException("board not created");
        }

        return BoardMapper.mapToBoardResponse(boardSaved);
    }

    public BoardResponse getBoardById() {

        List<BoardEntity> boardEntityList = boardRepository.findAll();

        return (BoardResponse) boardEntityList.stream().map(BoardMapper::mapToBoardResponse).toList();
    }

    @Transactional
    public void deleteBoardById(Long boardId) {

        boardRepository.deleteById(boardId);
    }

    public List<BoardResponse> getBoardsByUserId(Long userId) {
        List<BoardEntity> boardEntityList = boardRepository.findBoardEntitiesByUserId(userId);

        return boardEntityList.stream().map(BoardMapper::mapToBoardResponse).toList();
    }
}
