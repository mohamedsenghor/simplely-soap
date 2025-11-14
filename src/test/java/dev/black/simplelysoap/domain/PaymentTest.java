package dev.black.simplelysoap.domain;

import static dev.black.simplelysoap.domain.ClientTestSamples.*;
import static dev.black.simplelysoap.domain.PaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import dev.black.simplelysoap.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Payment.class);
        Payment payment1 = getPaymentSample1();
        Payment payment2 = new Payment();
        assertThat(payment1).isNotEqualTo(payment2);

        payment2.setId(payment1.getId());
        assertThat(payment1).isEqualTo(payment2);

        payment2 = getPaymentSample2();
        assertThat(payment1).isNotEqualTo(payment2);
    }

    @Test
    void initiatorTest() {
        Payment payment = getPaymentRandomSampleGenerator();
        Client clientBack = getClientRandomSampleGenerator();

        payment.setInitiator(clientBack);
        assertThat(payment.getInitiator()).isEqualTo(clientBack);

        payment.initiator(null);
        assertThat(payment.getInitiator()).isNull();
    }
}
