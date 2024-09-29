
import com.example.url.shortner.microservices.validationservice.service.PrefixCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class PrefixCheckTest {

    private PrefixCheck prefixCheck;

    @BeforeEach
    void setUp() {
        // Initialize the PrefixCheck instance before each test
        prefixCheck = new PrefixCheck();
    }

    @Test
    void testPrefixStringInspector_withValidHttpPrefix() throws Exception {
        // Test case for a valid "http://" prefix
        String input = "http://example.com";
        String expected = "http://";
        String result = prefixCheck.prefixStringInspector(input);

        assertEquals(expected, result, "The prefix should be 'http://'");
    }

    @Test
    void testPrefixStringInspector_withValidHttpsPrefix() throws Exception {
        // Test case for a valid "https://" prefix
        String input = "https://example.com";
        String expected = "https://";
        String result = prefixCheck.prefixStringInspector(input);

        assertEquals(expected, result, "The prefix should be 'https://'");
    }

    @Test
    void testPrefixStringInspector_withInvalidPrefix() throws Exception {
        // Test case for an invalid prefix (e.g., "ftp://")
        String input = "ftp://example.com";
        String expected = "false";
        String result = prefixCheck.prefixStringInspector(input);

        assertEquals(expected, result, "The result should be 'false' for an invalid prefix");
    }

    @Test
    void testPrefixStringInspector_withEmptyString() throws Exception {
        // Test case for an empty string input
        String input = "";
        String expected = "false";
        String result = prefixCheck.prefixStringInspector(input);

        assertEquals(expected, result, "The result should be 'false' for an empty input");
    }

    @Test
    void testPrefixStringInspector_withNullInput() {
        // Test case for a null input, expecting an exception to be thrown
        assertThrows(Exception.class, () -> {
            prefixCheck.prefixStringInspector(null);
        }, "An exception should be thrown for null input");
    }
}
