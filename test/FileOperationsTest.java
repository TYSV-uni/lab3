import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;

public class FileOperationsTest
{
    @Test
    void is_valid_file_name() {
        assertTrue(FileOperations.is_valid_file_name("file.txt"));
        assertTrue(FileOperations.is_valid_file_name("document.pdf"));
        assertTrue(FileOperations.is_valid_file_name(".ext"));
        assertTrue(FileOperations.is_valid_file_name("file..txt"));

        assertFalse(FileOperations.is_valid_file_name("file "));
        assertFalse(FileOperations.is_valid_file_name("file"));
    }

    @Test
    void  is_valid_folder() {
        Path path = Path.of("");
        assertTrue(FileOperations.is_valid_folder(path));

        path = Path.of("nonexistent/folder");
        assertFalse(FileOperations.is_valid_folder(path));

        path = Path.of(".gitignore");
        assertFalse(FileOperations.is_valid_folder(path));
    }

    @Test
    void get_name_wo_ext() {
        Path path = Path.of("test_file.txt");
        assertEquals("test_file", FileOperations.get_name_wo_ext(path));

        path = Path.of("another.test.file.pdf");
        assertEquals("another.test.file", FileOperations.get_name_wo_ext(path));
    }

    @Test
    void get_ext() {
        Path path = Path.of("test_file.txt");
        assertEquals(".txt", FileOperations.get_ext(path));

        path = Path.of("document.pdf");
        assertEquals(".pdf", FileOperations.get_ext(path));

        path = Path.of(".bad_ext");
        assertEquals(".bad_ext", FileOperations.get_ext(path));

        path = Path.of("many_dots....ext");
        assertEquals(".ext", FileOperations.get_ext(path));
    }

}
