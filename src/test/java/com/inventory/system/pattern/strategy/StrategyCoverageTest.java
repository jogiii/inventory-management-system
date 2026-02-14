package com.inventory.system.pattern.strategy;

import com.inventory.system.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(MockitoExtension.class)
class StrategyCoverageTest {

    @InjectMocks
    private LogAlertStrategy logAlertStrategy;

    @Test
    void logAlertStrategy_ShouldLog() {
        Product product = new Product();
        product.setName("P1");

        assertThatCode(() -> logAlertStrategy.sendAlert(product, "msg"))
                .doesNotThrowAnyException();
    }
}
