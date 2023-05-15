import static org.junit.jupiter.api.Assertions.*;
import java.util.AbstractMap.SimpleEntry;
import org.junit.jupiter.api.Test;
import jardin.flore.Betterave;
import jardin.flore.Etat;


public class BetteraveTest {

	@Test
	public void testSeDupliquer() {
		Betterave betterave = new Betterave();
		SimpleEntry<Integer, Integer> result = betterave.seDupliquer(10, 10);
		int newX = result.getKey();
		int newY = result.getValue();
		assertEquals(true, newX >= 0 && newX < 10);
		assertEquals(true, newY >= 0 && newY < 10);
		assertEquals(Etat.GRAINE, betterave.getEtat());
	}
}
