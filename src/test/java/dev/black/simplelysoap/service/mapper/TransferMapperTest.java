package dev.black.simplelysoap.service.mapper;

import static dev.black.simplelysoap.domain.TransferAsserts.*;
import static dev.black.simplelysoap.domain.TransferTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransferMapperTest {

    private TransferMapper transferMapper;

    @BeforeEach
    void setUp() {
        transferMapper = new TransferMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTransferSample1();
        var actual = transferMapper.toEntity(transferMapper.toDto(expected));
        assertTransferAllPropertiesEquals(expected, actual);
    }
}
