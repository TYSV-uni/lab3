import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

public class PDFHandler implements FileHandler
{
    @Override
    public void process(Path path)
    {
        if (!FileOperations.get_ext(path).equals(".pdf"))
            throw new IllegalArgumentException("Handler and file types don't match");

        System.out.println("Processing pdf: " + path.getFileName());
        if(FileOperations.rename_file(path, FileOperations.get_name_wo_ext(path) + "_handled"))
            System.out.println("File renamed successfully");
    }
}

