import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        Path path = Path.of("files").toAbsolutePath();
        if (!Files.exists(path))
            try
            {
                Files.createDirectory(path);
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e);
            }

        System.out.println("\n\n\t\tCurrent folder path for files: " + path);

        while(true)
        {
            int num = GetInput.get_int_in_range("""
                     
                     Main menu
                     1.Display available files
                     2.Add a new file
                     3.Delete a file
                     4.Create test files
                     5.Clear folder
                     6.Handle files
                     7.Exit
                     Enter:\s""", 1, 7);

            if (!Files.exists(path))
                try
                {
                    Files.createDirectory(path);
                } catch (Exception e)
                {
                    System.out.println("Exception occurred: " + e);
                }

            ArrayList<Path> files = FileOperations.get_file_paths_from_folder(path);

            switch (num)
            {
                case 1:

                    System.out.println("\nAvailable files: ");
                    if (files.isEmpty())
                        System.out.println("\tNo files found");
                    for (Path x : files)
                        System.out.println("\t" + x.getFileName());

                    break;

                case 2:
                    while (true)
                    {
                        String name = GetInput.get_string("\n(0 - exit) Input your file name (Ex: file_name.txt): ");
                        if (name.equals("0"))
                            break;

                        if(!name.contains(".") || name.endsWith(" ") || name.substring(0, name.lastIndexOf(".")).endsWith(" "))
                            System.out.println("Invalid file name");
                        else
                            FileOperations.add_file(path, name);
                    }

                    break;

                case 3:
                    if (files.isEmpty())
                        System.out.println("\nThe folder is empty, nothing to delete");
                    else
                        while (true)
                        {
                            String name = GetInput.get_string("\n(0 - exit) Input your file name (Ex: file_name.txt): ");
                            if (name.equals("0"))
                                break;

                            if(!name.contains(".") || name.endsWith(" ") || name.substring(0, name.lastIndexOf(".")).endsWith(" "))
                                System.out.println("Invalid file name");
                            else
                                FileOperations.delete_file(path, name);
                        }

                    break;

                case 4:
                    System.out.println();
                    FileOperations.create_test_files(path);
                    break;

                case 5:
                    System.out.println();

                    if (files.isEmpty())
                        System.out.println("The folder is empty, nothing to delete");
                    else
                        FileOperations.clear_folder(path);

                    break;

                case 6:
                    if (files.isEmpty())
                        System.out.println("\nThe folder is empty");
                    else
                        for (Path x : files)
                        {
                            System.out.println();
                            try
                            {
                                HandlerFactory.create_handler(FileOperations.get_ext(x)).process(x);
                            }
                            catch (Exception e)
                            {
                                System.out.println("Exception occurred for file " + x.getFileName() + ": " + e.getMessage());
                            }
                        }

                    break;

                case 7:
                    System.out.println("You have exited the program.");
                    return;
            }
        }



    }
}
