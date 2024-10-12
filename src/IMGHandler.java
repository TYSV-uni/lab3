import java.nio.file.Path;

public class IMGHandler implements FileHandler
{
    @Override
    public void process(Path path)
    {
        if (!FileOperations.get_ext(path).equals(".png") && !FileOperations.get_ext(path).equals(".jpg"))
            throw new IllegalArgumentException("Handler and file types don't match");

        System.out.println("Processing img: " + path.getFileName());
        if(FileOperations.rename_file(path, FileOperations.get_name_wo_ext(path) + "_handled"))
            System.out.println("File renamed successfully");
    }
}
