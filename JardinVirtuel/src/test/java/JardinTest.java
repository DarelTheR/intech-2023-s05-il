import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.AbstractMap.SimpleEntry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jardin.Emplacement;
import jardin.InputReader;
import jardin.Jardin;
import jardin.flore.Ail;
import jardin.flore.Betterave;
import jardin.flore.Etat;



@ExtendWith(MockitoExtension.class)
public class JardinTest {
	@Mock
	private InputReader inputReaderMock;
	
	@Mock
	private Betterave betteraveMock;

	private Jardin j;
	
	@BeforeEach
	public void init() {
	j = new Jardin(4, 4);
	}
	
	@Test
	public void testAjouterPanierVide() {
	// arrange
	String nom = "Fleur";
	Integer quantite = 5;

	// act
	j.ajouterPanier(nom, quantite);

	// assert
	Assertions.assertEquals(quantite, j.getPanier().get(nom));
	}
	
	@Test
	public void testAjouterPanierNonVide() {
	// arrange
	int expectedQuantite = 5;
	String vegetalName = "Ail";


	// act
	j.ajouterPanier(vegetalName, 2);
	j.ajouterPanier(vegetalName, 3);

	// assert
	Assertions.assertEquals(expectedQuantite, j.getPanier().get(vegetalName));
	}

	@Test
	public void testPasserSaisonSuivante() {
	// arrange
	j.getEmplacement()[0][0] = new Emplacement(new Ail());
	Etat expected = Etat.GERME;

	// act
	j.passerSaisonSuivante();

	// assert
	Assertions.assertEquals(expected, j.getEmplacement()[0][0].getVeg().getEtat());
	}
	
	
	@Test
	public void testPasserSaisonSuivantePlanteMorte() {
	// arrange
	Ail a = new Ail();
	a.grandir(5);
	j.getEmplacement()[0][0] = new Emplacement(a);

	// act
	j.passerSaisonSuivante();

	// assert
	Assertions.assertNull(j.getEmplacement()[0][0]);
	}
	
	
	@Test
	public void testSemerAil() {
	// arrange
	//        String input = "0 0 1";
	//        System.setIn(new ByteArrayInputStream(input.getBytes()));

	j.setInputReader(inputReaderMock);

	when(inputReaderMock.readIntValue()).thenReturn(0,0,1);

	j.ajouterPanier("Ail", 3);

	// act
	j.semer();

	// assert
	verify(inputReaderMock, times(3)).readIntValue();


	Assertions.assertEquals(2, j.getPanier().get("Ail"));
	Assertions.assertTrue(j.getEmplacement()[0][0].getVeg() instanceof Ail);
	}
	
	@Test
	public void testRecolterEnFleurEtOgm() {
		//Arrange
		
		when(betteraveMock.getEtat()).thenReturn(Etat.FLEUR, Etat.GRAINE);
		SimpleEntry<Integer, Integer> coords = new SimpleEntry<>(1,1);
		when(betteraveMock.seDupliquer(4, 4)).thenReturn(coords);
		
		j.getEmplacement()[0][0] = new Emplacement(betteraveMock);
		
		//Act
		j.recolter();
		
		//Assert
		verify(betteraveMock, times(2)).getEtat();
		verify(betteraveMock).seDupliquer(4, 4);
		
		
		Assertions.assertNull(j.getEmplacement()[0][0]);
		Assertions.assertNotNull(j.getEmplacement()[1][1]);
		
	}
}
