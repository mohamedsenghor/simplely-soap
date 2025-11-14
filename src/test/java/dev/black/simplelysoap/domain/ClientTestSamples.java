package dev.black.simplelysoap.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class ClientTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Client getClientSample1() {
        return new Client().id(1L).numeroTel(1L).solde(1L);
    }

    public static Client getClientSample2() {
        return new Client().id(2L).numeroTel(2L).solde(2L);
    }

    public static Client getClientRandomSampleGenerator() {
        return new Client().id(longCount.incrementAndGet()).numeroTel(longCount.incrementAndGet()).solde(longCount.incrementAndGet());
    }
}
