package dev.black.simplelysoap.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Entité de Paiement (basée sur paymentInfo).
 * Le Client Initiateur est la source des fonds.
 */
@Entity
@Table(name = "payment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "number_receive", nullable = false)
    private Long numberReceive;

    @NotNull
    @Min(value = 1L)
    @Column(name = "amount_sent", nullable = false)
    private Long amountSent;

    @NotNull
    @Column(name = "date_transaction", nullable = false)
    private Instant dateTransaction;

    @ManyToOne(optional = false)
    @NotNull
    private Client initiator;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Payment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberReceive() {
        return this.numberReceive;
    }

    public Payment numberReceive(Long numberReceive) {
        this.setNumberReceive(numberReceive);
        return this;
    }

    public void setNumberReceive(Long numberReceive) {
        this.numberReceive = numberReceive;
    }

    public Long getAmountSent() {
        return this.amountSent;
    }

    public Payment amountSent(Long amountSent) {
        this.setAmountSent(amountSent);
        return this;
    }

    public void setAmountSent(Long amountSent) {
        this.amountSent = amountSent;
    }

    public Instant getDateTransaction() {
        return this.dateTransaction;
    }

    public Payment dateTransaction(Instant dateTransaction) {
        this.setDateTransaction(dateTransaction);
        return this;
    }

    public void setDateTransaction(Instant dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public Client getInitiator() {
        return this.initiator;
    }

    public void setInitiator(Client client) {
        this.initiator = client;
    }

    public Payment initiator(Client client) {
        this.setInitiator(client);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        return getId() != null && getId().equals(((Payment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Payment{" +
            "id=" + getId() +
            ", numberReceive=" + getNumberReceive() +
            ", amountSent=" + getAmountSent() +
            ", dateTransaction='" + getDateTransaction() + "'" +
            "}";
    }
}
