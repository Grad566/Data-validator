package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    public void mainTest() {
        var expected = 1;
        var actual = App.main();
        assertEquals(expected, actual);
    }
}
