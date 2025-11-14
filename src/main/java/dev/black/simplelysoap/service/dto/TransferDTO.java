package dev.black.simplelysoap.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link dev.black.simplelysoap.domain.Transfer} entity.
 */
@Schema(
    description = "Entité de Virement (basée sur transferInfo).\nLe numberSender et le numberReceive du XSD sont remplacés par des relations Client.\nLes champs numberSender et numberReceive du XSD deviennent les noms des relations."
)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TransferDTO implements Serializable {

    private Long id;

    @NotNull
    @Min(value = 1L)
    private Long amountSent;

    @NotNull
    private Instant dateTransaction;

    @NotNull
    private ClientDTO sender;

    @NotNull
    private ClientDTO receiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ClientDTO getSender() {
        return sender;
    }

    public void setSender(ClientDTO sender) {
        this.sender = sender;
    }

    public ClientDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(ClientDTO receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransferDTO)) {
            return false;
        }

        TransferDTO transferDTO = (TransferDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, transferDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransferDTO{" +
            "id=" + getId() +
            ", amountSent=" + getAmountSent() +
            ", dateTransaction='" + getDateTransaction() + "'" +
            ", sender=" + getSender() +
            ", receiver=" + getReceiver() +
            "}";
    }
}
