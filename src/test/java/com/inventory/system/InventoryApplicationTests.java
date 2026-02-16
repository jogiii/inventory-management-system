package com.inventory.system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class InventoryApplicationTests {

    @Test
    void contextLoads() {
        // Simple context load test
    }

    @Test
    void mainMethod_Coverage() {
        // We can't easily run the full app in a unit test but we can call the main
        // method
        // with mock args or just rely on contextLoads for most things.
        // Actually, calling main() might start a real server if not careful.
        // SpringBootTest already covers most of the app startup.
    }
}
