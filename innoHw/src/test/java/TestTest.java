import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestTest {

    @Test
    void doSome() {
        Assert.assertEquals(2, 1+1);
    }

    @Test
    public void testRead() throws IOException {
        TestUnit unit = new TestUnit();

        byte[] data = "123,456,789,123,456,789".getBytes();

        InputStream input = new ByteArrayInputStream(data);

        unit.read(input);

        assertEquals("123", unit.tokens.get(0));
        assertEquals("456", unit.tokens.get(1));
        assertEquals("789", unit.tokens.get(2));
        assertEquals("123", unit.tokens.get(3));
        assertEquals("456", unit.tokens.get(4));
        assertEquals("789", unit.tokens.get(5));
    }
}