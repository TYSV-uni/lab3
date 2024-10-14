import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;

interface FileOperations
{
    static boolean is_valid_file_name(String name)
    {
        return name.contains(".") && !name.endsWith(" ") && !name.substring(0, name.lastIndexOf(".")).endsWith(" ");
    }

    static boolean is_valid_folder(Path folder)
    {
        if (!Files.exists(folder))
            System.out.println("Folder does not exist");
        else if (!Files.isDirectory(folder))
            System.out.println("Given directory isn't a folder");
        else
            return true;

        return false;
    }

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

        if (is_valid_folder(folder))
            try(DirectoryStream<Path> stream = Files.newDirectoryStream(folder))
            {
                for (Path x : stream)
                    file_paths.add(x);
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e);
            }

        return file_paths;
    }

    static void add_file(Path folder, String name)
    {
        if (!is_valid_folder(folder))
            return;
        if(!is_valid_file_name(name))
        {
            System.out.println("Invalid file name: " + name);
            return;
        }


        Path path = folder.resolve(name);

        int i = 2;
        while (Files.exists(path))
        {
            String formatted_i = " (" + i + ")";
            path = path.getParent().resolve(name.substring(0, name.lastIndexOf(".")) + formatted_i + get_ext(path));
            i++;
        }

        try
        {
            Files.createFile(path);
            System.out.println("File created successfully");
        }
        catch (Exception e)
        {
            System.out.println("Exception occurred: " + e);
        }

    }

    static void delete_file(Path folder, String file_name)
    {
        if (!is_valid_folder(folder))
            return;

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
        if (!is_valid_folder(folder))
            return;

        String[] file_names = {"test_file.pdf", "test_file.txt", "test_file.png", "test_file.jpg", "test_file.bad_ext"};

        for (String name : file_names)
           add_file(folder, name);

    }

    static void clear_folder(Path folder)
    {
        if (!is_valid_folder(folder))
            return;

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
        if(!is_valid_file_name(new_name))
        {
            System.out.println("Invalid file name: " + new_name);
            return false;
        }
        Path new_path = path.getParent().resolve(new_name);

        int i = 2;
        while (Files.exists(new_path))
        {
            String formatted_i = " (" + i + ")";
            new_path = new_path.getParent().resolve(new_name.substring(0, new_name.lastIndexOf(".")) + formatted_i + get_ext(new_path));
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
