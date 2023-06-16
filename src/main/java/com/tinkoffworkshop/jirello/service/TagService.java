package com.tinkoffworkshop.jirello.service;

import com.tinkoffworkshop.jirello.model.request.TagOnBoardRequest;
import com.tinkoffworkshop.jirello.model.response.TagResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.BoardRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.TagRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.BoardEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TagEntity;
import com.tinkoffworkshop.jirello.support.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final BoardRepository boardRepository;
    private final TagRepository tagRepository;

    public List<TagResponse> getBoardTags(Long boardId) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(boardId);

        if (boardEntityOptional.isEmpty()) {
            throw new RuntimeException("board with id = " + boardId + " not found");
        }

        List<TagEntity> tagEntityList = tagRepository.findAllByBoardEntity_Id(boardId);

        if (tagEntityList == null) {
            return new ArrayList<TagResponse>();
        }

        return tagEntityList.stream().map(TagMapper::mapToTagResponse).toList();
    }

    public TagResponse createTagOnBoard(TagOnBoardRequest tagOnBoardRequest) {
        TagEntity tagEntity = TagMapper.mapToTagEntity(
                tagOnBoardRequest,
                boardRepository.findById(tagOnBoardRequest.getBoardId()).get()
        );
        var savedTagEntity = tagRepository.save(tagEntity);

        if (savedTagEntity == null) {
            throw new RuntimeException("tag not saved");
        }

        return TagMapper.mapToTagResponse(savedTagEntity);
    }
}
