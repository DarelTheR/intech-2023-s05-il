import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import jardin.flore.Carotte;

public class CarotteTest {
	
	@Test
    public void testSeReproduire() {
		
		//Arrange
        Carotte carotte = new Carotte();
        HashMap<String, Integer> panier = new HashMap<>();
        
        //Act
        carotte.seReproduire(panier);
        
        //Assert
        assertEquals(Integer.valueOf(3), panier.get("Carotte"));
    }
}
