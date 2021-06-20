package com.rifcoder.hackerrank.validIP;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.rifcoder.hackerrank.validIP.ValidIPAddress.ensureValidIP;
import static org.assertj.core.api.Assertions.assertThat;

class ValidIPAddressTest {

    @Test
    @DisplayName("Check simple valid IP")
    void checkSimpleValidIp() {
        assertThat(ensureValidIP("1.1.1.1")).isTrue();
        assertThat(ensureValidIP("12.11.10.19")).isTrue();
        assertThat(ensureValidIP("000.12.12.034")).isTrue();
    }

    @Test
    @DisplayName("Check invalid IPs")
    void checkInvalidIPs() {
        assertThat(ensureValidIP("1234.1222.44.44")).isFalse();
        assertThat(ensureValidIP("123.1222.44.44")).isFalse();
        assertThat(ensureValidIP("123.122.4422.44")).isFalse();
        assertThat(ensureValidIP("123.122.44.4411")).isFalse();
        assertThat(ensureValidIP("-123.122.44.44")).isFalse();
    }

    @Test
    @DisplayName("Check than IP is valid not exceed IP ranges")
    void checkThanIpIsValidNotExceedIpRanges() {
        assertThat(ensureValidIP("0.0.0.0")).isTrue();
        assertThat(ensureValidIP("255.255.255.255")).isTrue();
        assertThat(ensureValidIP("256.255.255.255")).isFalse();
        assertThat(ensureValidIP("256.256.256.256")).isFalse();
        assertThat(ensureValidIP("999.999.999.999")).isFalse();
    }
}