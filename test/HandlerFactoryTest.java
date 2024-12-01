import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class HandlerFactoryTest
{
    @Test
    public void create_handler()
    {
        assertInstanceOf(PDFHandler.class, HandlerFactory.create_handler(".pdf"), "PDF Handler test failed");
        assertInstanceOf(TXTHandler.class, HandlerFactory.create_handler("txt"), "TXT Handler test failed");
        assertInstanceOf(IMGHandler.class, HandlerFactory.create_handler(".jpg"), "JPG Handler test failed");
        assertInstanceOf(IMGHandler.class, HandlerFactory.create_handler(".png"), "PNG Handler test failed");
        assertThrows(IllegalArgumentException.class, () -> HandlerFactory.create_handler(".exe"), "Invalid extension test failed");
    }
}