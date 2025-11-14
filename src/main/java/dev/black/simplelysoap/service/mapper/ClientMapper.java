package dev.black.simplelysoap.service.mapper;

import dev.black.simplelysoap.domain.Client;
import dev.black.simplelysoap.service.dto.ClientDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {}
