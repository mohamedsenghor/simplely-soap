package dev.black.simplelysoap.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class TransferTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Transfer getTransferSample1() {
        return new Transfer().id(1L).amountSent(1L);
    }

    public static Transfer getTransferSample2() {
        return new Transfer().id(2L).amountSent(2L);
    }

    public static Transfer getTransferRandomSampleGenerator() {
        return new Transfer().id(longCount.incrementAndGet()).amountSent(longCount.incrementAndGet());
    }
}
