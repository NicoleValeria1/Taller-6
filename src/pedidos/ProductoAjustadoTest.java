package pedidos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProductoAjustadoTest {
	public Ingrediente ingrediente;
	public ProductoAjustado productoAjustado;
	private ProductoMenu productoMenu;
	
	@BeforeEach
	public void set() {
		this.productoMenu.productoMenu("Hamburguesa", 20000, 400);
	}
	
	@BeforeEach
	public void setUp() {
		this.productoAjustado = new ProductoAjustado();
		this.productoAjustado.productoAjustado(productoMenu);
	}
	
	@BeforeEach
	public void testagregarIngredienteExtra() {
		this.ingrediente.ingrediente("Queso", 2000, 200);
		this.productoAjustado.agregarIngredienteExtra(ingrediente);
	}
	
	@BeforeEach
	public void testquitarIngrediente() {
		this.ingrediente.ingrediente("Cebolla", 1000, 100);
		this.productoAjustado.quitarIngrediente(ingrediente);
	}
	
	@Test
	public void testgetPrecio() {
		assertEquals(21000, this.productoAjustado.getPrecio());
	}
	
	@Test
	public void testgenerarTextoFactura() {
		assertEquals("Artículo: Hamburguesa, Valor: $20000, Calorias: 400\nIngredientes Adicionados: \nQueso, $2000, Calorias:200\nIngredientes Eliminados:\nCebolla ,  Calorias: 100", this.productoAjustado.generarTextoFactura());
	}
	
	@Test
	public void testgetCalorias() {
		assertEquals(500, this.productoAjustado.getCalorias());
	}
	
}
