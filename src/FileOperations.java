import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;

interface FileOperations
{
    static String get_name_wo_ext(Path path)
    {
        return path.getFileName().toString().substring(0, path.getFileName().toString().lastIndexOf("."));
    }

    static String get_ext(Path path)
    {
        return path.getFileName().toString().substring(path.getFileName().toString().lastIndexOf("."));
    }

    static ArrayList<Path> get_file_paths_from_folder(Path folder)
    {
        ArrayList<Path> file_paths = new ArrayList<>();
        if (!Files.exists(folder))
            System.out.println("Folder does not exist");
        else
        {
            try(DirectoryStream<Path> stream = Files.newDirectoryStream(folder))
            {
                for (Path x : stream)
                    file_paths.add(x);
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e);
            }
        }
        return file_paths;
    }

    static void add_file(Path folder, String name)
    {
        if (!Files.exists(folder))
        {
            System.out.println("Folder does not exist");
            return;
        }

        try
        {
            Files.createFile(folder.resolve(name));
            System.out.println("File created successfully");
        }
        catch (java.nio.file.FileAlreadyExistsException e)
        {
            System.out.println("File " + name + " already exists");
        }
        catch (Exception e)
        {
            System.out.println("Exception occurred: " + e);
        }

    }

    static void delete_file(Path folder, String file_name)
    {
        if (!Files.exists(folder))
        {
            System.out.println("Folder does not exist");
            return;
        }

        try
        {
            Files.delete(folder.resolve(file_name));
            System.out.println("File deleted successfully");
        }
        catch (java.nio.file.NoSuchFileException e)
        {
            System.out.println("File not found");
        }
        catch (Exception e)
        {
            System.out.println("Exception occurred: " + e);
        }

    }


    static void create_test_files(Path folder)
    {
        String[] file_names ={"test_file.pdf", "test_file.txt", "test_file.png", "test_file.jpg", "test_file.bad_ext"};

        if (!Files.exists(folder))
        {
            System.out.println("Folder does not exist");
            return;
        }
        if (!Files.isDirectory(folder))
        {
            System.out.println("File is not a folder");
            return;
        }

        for (String name : file_names)
            try
            {
                Files.createFile(folder.resolve(name));
                System.out.println("File " + name + " created successfully");
            }
            catch (java.nio.file.FileAlreadyExistsException e)
            {
                System.out.println("File " + name + " already exists");
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e);
                return;
            }

    }

    static void clear_folder(Path folder)
    {
        if (!Files.exists(folder))
        {
            System.out.println("Folder does not exist");
            return;
        }
        if (!Files.isDirectory(folder))
        {
            System.out.println("File is not a folder");
            return;
        }

        ArrayList<Path> file_paths = get_file_paths_from_folder(folder);
        for (Path x : file_paths)
            try
            {
                Files.delete(x);
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e);
            }

        System.out.println("Folder cleared successfully");
    }



    static boolean rename_file(Path path, String new_name)
    {
        Path new_path = path.getParent().resolve(new_name + get_ext(path));

        int i = 2;
        while (Files.exists(new_path))
        {
            String formatted_i = "(" + i + ")";
            new_path = new_path.getParent().resolve(new_name + formatted_i + get_ext(new_path));
            i++;
        }

        try
        {
            Files.move(path, new_path);
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Exception occurred: " + e);
            return false;
        }
    }

}
