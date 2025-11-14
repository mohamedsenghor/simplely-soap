package dev.black.simplelysoap.service.mapper;

import dev.black.simplelysoap.domain.Client;
import dev.black.simplelysoap.domain.Payment;
import dev.black.simplelysoap.service.dto.ClientDTO;
import dev.black.simplelysoap.service.dto.PaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Payment} and its DTO {@link PaymentDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {
    @Mapping(target = "initiator", source = "initiator", qualifiedByName = "clientNumeroTel")
    PaymentDTO toDto(Payment s);

    @Named("clientNumeroTel")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "numeroTel", source = "numeroTel")
    ClientDTO toDtoClientNumeroTel(Client client);
}
