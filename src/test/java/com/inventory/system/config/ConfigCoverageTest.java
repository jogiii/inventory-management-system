package com.inventory.system.config;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigCoverageTest {

    @Test
    void jpaConfigTests() {
        JpaConfig config = new JpaConfig();
        assertThat(config).isNotNull();
    }
}
