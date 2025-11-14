package dev.black.simplelysoap.service.mapper;

import dev.black.simplelysoap.domain.Client;
import dev.black.simplelysoap.domain.Transfer;
import dev.black.simplelysoap.service.dto.ClientDTO;
import dev.black.simplelysoap.service.dto.TransferDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transfer} and its DTO {@link TransferDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransferMapper extends EntityMapper<TransferDTO, Transfer> {
    @Mapping(target = "sender", source = "sender", qualifiedByName = "clientNumeroTel")
    @Mapping(target = "receiver", source = "receiver", qualifiedByName = "clientNumeroTel")
    TransferDTO toDto(Transfer s);

    @Named("clientNumeroTel")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "numeroTel", source = "numeroTel")
    ClientDTO toDtoClientNumeroTel(Client client);
}
