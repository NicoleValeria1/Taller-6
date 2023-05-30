package pedidos;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductoMenuTest {
	public ProductoMenu productoMenu;
	
	@BeforeEach
	public void setUp() {
		this.productoMenu = new ProductoMenu();
		this.productoMenu.productoMenu("Hamburguesa", 20000, 400);
		
	}
	
	@Test
	public void testgetCalorias() {
		assertEquals(400, this.productoMenu.getCalorias());
	}
	
	@Test
	public void testgenerarTextoFactura() {
		assertEquals("Artículo: Hamburguesa, Valor: $20000, Calorias: 400", this.productoMenu.generarTextoFactura());
	}
}
