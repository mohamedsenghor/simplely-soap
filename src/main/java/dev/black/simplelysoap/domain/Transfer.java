package dev.black.simplelysoap.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Entité de Virement (basée sur transferInfo).
 * Le numberSender et le numberReceive du XSD sont remplacés par des relations Client.
 * Les champs numberSender et numberReceive du XSD deviennent les noms des relations.
 */
@Entity
@Table(name = "transfer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Transfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Min(value = 1L)
    @Column(name = "amount_sent", nullable = false)
    private Long amountSent;

    @NotNull
    @Column(name = "date_transaction", nullable = false)
    private Instant dateTransaction;

    @ManyToOne(optional = false)
    @NotNull
    private Client sender;

    @ManyToOne(optional = false)
    @NotNull
    private Client receiver;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Transfer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmountSent() {
        return this.amountSent;
    }

    public Transfer amountSent(Long amountSent) {
        this.setAmountSent(amountSent);
        return this;
    }

    public void setAmountSent(Long amountSent) {
        this.amountSent = amountSent;
    }

    public Instant getDateTransaction() {
        return this.dateTransaction;
    }

    public Transfer dateTransaction(Instant dateTransaction) {
        this.setDateTransaction(dateTransaction);
        return this;
    }

    public void setDateTransaction(Instant dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public Client getSender() {
        return this.sender;
    }

    public void setSender(Client client) {
        this.sender = client;
    }

    public Transfer sender(Client client) {
        this.setSender(client);
        return this;
    }

    public Client getReceiver() {
        return this.receiver;
    }

    public void setReceiver(Client client) {
        this.receiver = client;
    }

    public Transfer receiver(Client client) {
        this.setReceiver(client);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transfer)) {
            return false;
        }
        return getId() != null && getId().equals(((Transfer) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transfer{" +
            "id=" + getId() +
            ", amountSent=" + getAmountSent() +
            ", dateTransaction='" + getDateTransaction() + "'" +
            "}";
    }
}
