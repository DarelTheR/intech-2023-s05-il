import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import jardin.flore.Ail;
import jardin.flore.Etat;
import jardin.flore.Vegetal;

public class VegetalTest {

	@Test
    public void testGrandir() {
        Vegetal vegetal = new Ail();
        assertEquals(Etat.GRAINE, vegetal.getEtat());
        vegetal.grandir();
        assertEquals(Etat.TIGE, vegetal.getEtat());
        vegetal.grandir(2);
        assertEquals(Etat.FLEUR, vegetal.getEtat());
    }
}
