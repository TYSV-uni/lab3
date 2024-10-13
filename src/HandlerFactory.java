public class HandlerFactory
{
    public static FileHandler create_handler(String ext)
    {
        if (!ext.contains("."))
            ext = "." + ext;

        return switch(ext)
        {
            case ".pdf" -> new PDFHandler();
            case ".txt" -> new TXTHandler();
            case ".jpg", ".png" -> new IMGHandler();
            default -> throw new IllegalArgumentException("Invalid file type");
        };
    }
}
