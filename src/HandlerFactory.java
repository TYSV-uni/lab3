public class HandlerFactory
{
    public static FileHandler create_handler(String type)
    {
        return switch(type)
        {
            case ".pdf" -> new PDFHandler();
            case ".txt" -> new TXTHandler();
            case ".jpg", ".png" -> new IMGHandler();
            default -> throw new IllegalArgumentException("Invalid file type");
        };
    }
}
