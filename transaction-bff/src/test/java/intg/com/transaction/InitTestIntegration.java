package com.transaction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

@ExtendWith(MockitoExtension.class)
public class InitTestIntegration {

    @Test
    void testInitial() {

        Assert.isTrue(true, "ok");

    }

}
