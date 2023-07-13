package com.levdoc.m20service.mapper;

import com.levdoc.m20service.dto.UserDTO;
import com.levdoc.m20service.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = PatientMapper.class)
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserDTO modelToDto(UserModel userModel);

    UserModel DtoToModel(UserDTO userDTO);

    List<UserDTO> modelsToDtos(List<UserModel> userModels);

    List<UserModel> dtosToModels(List<UserDTO> userDTOS);


}
