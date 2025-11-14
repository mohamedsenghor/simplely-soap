package dev.black.simplelysoap.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link dev.black.simplelysoap.domain.Payment} entity.
 */
@Schema(description = "Entité de Paiement (basée sur paymentInfo).\nLe Client Initiateur est la source des fonds.")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentDTO implements Serializable {

    private Long id;

    @NotNull
    private Long numberReceive;

    @NotNull
    @Min(value = 1L)
    private Long amountSent;

    @NotNull
    private Instant dateTransaction;

    @NotNull
    private ClientDTO initiator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberReceive() {
        return numberReceive;
    }

    public void setNumberReceive(Long numberReceive) {
        this.numberReceive = numberReceive;
    }

    public Long getAmountSent() {
        return amountSent;
    }

    public void setAmountSent(Long amountSent) {
        this.amountSent = amountSent;
    }

    public Instant getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Instant dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public ClientDTO getInitiator() {
        return initiator;
    }

    public void setInitiator(ClientDTO initiator) {
        this.initiator = initiator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentDTO)) {
            return false;
        }

        PaymentDTO paymentDTO = (PaymentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paymentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentDTO{" +
            "id=" + getId() +
            ", numberReceive=" + getNumberReceive() +
            ", amountSent=" + getAmountSent() +
            ", dateTransaction='" + getDateTransaction() + "'" +
            ", initiator=" + getInitiator() +
            "}";
    }
}
