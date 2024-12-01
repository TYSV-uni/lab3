import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;


public class GetInputTest
{

    @Test
    void get_string()
    {
        ByteArrayInputStream in = new ByteArrayInputStream("Hello\n".getBytes());
        System.setIn(in);
        assertEquals("Hello", GetInput.get_string(""));

        in = new ByteArrayInputStream("1234\n".getBytes());
        System.setIn(in);
        assertEquals("1234", GetInput.get_string(""));

        System.setIn(System.in);
    }

    @Test
    void get_int_in_range()
    {
        ByteArrayInputStream good_input = new ByteArrayInputStream("5\n".getBytes());
        System.setIn(good_input);
        assertEquals(5, GetInput.get_int_in_range("", 1, 10));

        ByteArrayInputStream inputs = new ByteArrayInputStream(
                ("""
                0
                11
                invalid
                2
                """).getBytes());

        System.setIn(inputs);

        assertEquals(2, GetInput.get_int_in_range("", 1, 10));

    }

    @Test
    void get_double_in_range()
    {
        ByteArrayInputStream good_input = new ByteArrayInputStream("5.0\n".getBytes());
        System.setIn(good_input);
        assertEquals(5.0, GetInput.get_double_in_range("", 1.0, 10.0));

        ByteArrayInputStream inputs = new ByteArrayInputStream(
                ("""
                0
                11
                invalid
                2
                """).getBytes());

        System.setIn(inputs);

        assertEquals(2.0, GetInput.get_double_in_range("", 1.0, 10.0));

        System.setIn(System.in);
    }

}