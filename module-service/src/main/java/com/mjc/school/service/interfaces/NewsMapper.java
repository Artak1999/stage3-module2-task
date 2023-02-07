package com.mjc.school.service.interfaces;

import com.mjc.school.repository.model.implementation.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@org.mapstruct.Mapper
public interface NewsMapper {
    @Mappings({@Mapping(
            target = "createDate",
            ignore = true
    ), @Mapping(
            target = "lastUpdateDate",
            ignore = true
    )})
    NewsModel DtoRequestToModel(NewsDtoRequest dto);

    NewsModel DTOResponseToModel(NewsDtoResponse dto);

    NewsDtoResponse modelToDTOResponse(NewsModel model);

    List<NewsDtoResponse> listOfModelsToListOfResponses(List<NewsModel> modelList);

    List<NewsModel> listOfResponsesToListOfModel(List<NewsDtoResponse> responseList);
}
