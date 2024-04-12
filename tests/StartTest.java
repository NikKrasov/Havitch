import havitch.Start;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StartTest {

    @Test
    void executeCommand() {
        var h = new Start();
        h.act("a&a|b&c");

    }
}