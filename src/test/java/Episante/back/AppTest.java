package Episante.back;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    @Test
    @DisplayName("Simple true assertion test")
    void testApp()
    {
        assertThat(true).isTrue();

    }

}