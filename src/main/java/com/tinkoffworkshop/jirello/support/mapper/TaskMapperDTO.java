package com.tinkoffworkshop.jirello.support.mapper;

import com.tinkoffworkshop.jirello.model.request.TaskRequest;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TaskEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TaskMapperDTO {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTaskEntityFromDto(TaskRequest taskRequest, @MappingTarget TaskEntity taskEntity);
}
