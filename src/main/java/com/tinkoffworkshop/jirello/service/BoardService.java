package com.tinkoffworkshop.jirello.service;

import com.tinkoffworkshop.jirello.model.request.BoardRequest;
import com.tinkoffworkshop.jirello.model.response.BoardByIdResponse;
import com.tinkoffworkshop.jirello.model.response.BoardResponse;
import com.tinkoffworkshop.jirello.model.response.UserBoardResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.BoardRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.UserRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.BoardEntity;
import com.tinkoffworkshop.jirello.support.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
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

    public BoardByIdResponse getBoardById(Long boardId) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(boardId);

        if (boardEntityOptional.isEmpty()) {
            throw new RuntimeException("board with id = " + boardId + " not found");
        }

        BoardEntity boardEntity = boardEntityOptional.get();

        return BoardMapper.mapToBoardByIdResponse(boardEntity);
    }

    @Transactional
    public void deleteBoardById(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public List<UserBoardResponse> getBoardsByUserId(Long userId) {
        List<BoardEntity> boardEntityList = boardRepository.findBoardEntitiesByUserId(userId);

        return boardEntityList.stream().map(boardEntity ->  BoardMapper.mapToUserBoardResponse(boardEntity, userId)).toList();
    }

    public BoardResponse updateBoardName(Long boardId, BoardRequest boardRequest) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(boardId);

        if (boardEntityOptional.isEmpty()) {
            throw new RuntimeException("board with id = " + boardId + " not found");
        }

        BoardEntity boardEntity = boardEntityOptional.get();

        boardEntity.setTitle(boardRequest.getTitle());

        boardEntity = boardRepository.save(boardEntity);

        return BoardMapper.mapToBoardResponse(boardEntity);
    }

//    public BoardResponse updateBoardParticipants(Long boardId, Long userId) {
//        Optional<BoardEntity> boardEntity = boardRepository.findById(boardId);
//        Optional<UserEntity> userEntity = userRepository.findById(userId);
//
//        if (userEntity.isEmpty() || boardEntity.isEmpty()) {
//            throw new RuntimeException("user or board not found");
//        }
//
//        BoardEntity board = boardEntity.get();
//        board.getUsersRoles().
//    }
}
