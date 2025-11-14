package dev.black.simplelysoap.repository;

import dev.black.simplelysoap.domain.Client;
import dev.black.simplelysoap.service.dto.ClientDTO;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Client entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByNumeroTel(Long numeroTel);
}
