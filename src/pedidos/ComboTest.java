package pedidos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ComboTest {
	public Combo combo;
	public ProductoMenu productoMenu;
	
	@BeforeEach
	public void set() {
		this.productoMenu.productoMenu("Hamburguesa", 20000, 400);;
	}
	
	@BeforeEach
	public void setUp() {
		this.combo = new Combo();
		
	}
	
	@BeforeEach
	public void agregarItemaCombo() {
		this.combo.agregarIteamACombo(productoMenu);
	}
	
	@Test
	public void testgetCalorias() {
		assertEquals(400, this.combo.getCalorias());
	}
	
	@Test
	public void testgenerarTextoFactura() {
		assertEquals("Nombre Combo: Super\nItems: \nArtículo: Hamburguesa, Valor: $20000, Calorias: 400", this.combo.getCalorias());
	}

}
