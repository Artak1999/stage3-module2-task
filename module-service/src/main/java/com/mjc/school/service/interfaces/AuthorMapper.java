package com.mjc.school.service.interfaces;

import com.mjc.school.repository.model.implementation.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
@org.mapstruct.Mapper
public interface AuthorMapper {
    @Mappings({@Mapping(
            target = "createDate",
            ignore = true
    ), @Mapping(
            target = "lastUpdateDate",
            ignore = true
    )})
    AuthorModel DtoRequestToModel(AuthorDtoRequest dto);

    AuthorModel DTOResponseToModel(AuthorDtoResponse dto);

    AuthorDtoResponse modelToDTOResponse(AuthorModel model);

    List<AuthorDtoResponse> listOfModelsToListOfResponses(List<AuthorModel> modelList);

    List<AuthorModel> listOfResponsesToListOfModel(List<AuthorDtoResponse> responseList);
}
