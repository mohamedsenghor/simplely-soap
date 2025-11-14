package dev.black.simplelysoap.domain;

import static dev.black.simplelysoap.domain.ClientTestSamples.*;
import static dev.black.simplelysoap.domain.TransferTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import dev.black.simplelysoap.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TransferTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transfer.class);
        Transfer transfer1 = getTransferSample1();
        Transfer transfer2 = new Transfer();
        assertThat(transfer1).isNotEqualTo(transfer2);

        transfer2.setId(transfer1.getId());
        assertThat(transfer1).isEqualTo(transfer2);

        transfer2 = getTransferSample2();
        assertThat(transfer1).isNotEqualTo(transfer2);
    }

    @Test
    void senderTest() {
        Transfer transfer = getTransferRandomSampleGenerator();
        Client clientBack = getClientRandomSampleGenerator();

        transfer.setSender(clientBack);
        assertThat(transfer.getSender()).isEqualTo(clientBack);

        transfer.sender(null);
        assertThat(transfer.getSender()).isNull();
    }

    @Test
    void receiverTest() {
        Transfer transfer = getTransferRandomSampleGenerator();
        Client clientBack = getClientRandomSampleGenerator();

        transfer.setReceiver(clientBack);
        assertThat(transfer.getReceiver()).isEqualTo(clientBack);

        transfer.receiver(null);
        assertThat(transfer.getReceiver()).isNull();
    }
}
