import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import jardin.flore.Ail;

public class AilTest {
	
	@Test
	 	void testSeReproduire() {
		//Arrange
        Ail ail = new Ail();
        HashMap<String, Integer> panier = new HashMap<String, Integer>();
        
        //Act
        panier.put("Ail", 1);
        ail.seReproduire(panier);
        
        //Assert
        assertEquals(4, (int) panier.get("Ail"));
    }
	
}
