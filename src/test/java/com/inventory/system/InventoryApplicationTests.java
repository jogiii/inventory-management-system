package com.inventory.system;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.mockStatic;

@SpringBootTest
@ActiveProfiles("test")
class InventoryApplicationTests {

    @Test
    void contextLoads() {
        // Simple context load test
    }

    @Test
    void mainMethod_Coverage() {
        try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
            mocked.when(() -> SpringApplication.run(InventoryApplication.class, new String[] {}))
                    .thenReturn(null);

            InventoryApplication.main(new String[] {});

            mocked.verify(() -> SpringApplication.run(InventoryApplication.class, new String[] {}));
        }
    }
}
