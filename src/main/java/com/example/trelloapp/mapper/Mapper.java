package com.example.trelloapp.mapper;

import com.example.trelloapp.model.Table;
import com.example.trelloapp.model.Task;
import com.example.trelloapp.model.User;
import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    Table toModel(com.example.trelloapp.dto.Table dto);
    List<Table> toModelList(List<com.example.trelloapp.dto.Table> dto);

    com.example.trelloapp.dto.Table toDto(Table model);
    List<com.example.trelloapp.dto.Table> toDtoList(List<Table> model);

    Task toModel(com.example.trelloapp.dto.Task dto);
    com.example.trelloapp.dto.Task toDto(Task model);

    User toModel(com.example.trelloapp.dto.User dto);

    com.example.trelloapp.dto.User toDto(User user);
}
