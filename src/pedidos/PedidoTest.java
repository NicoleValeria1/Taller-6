package pedidos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.Test;

public class PedidoTest {
	public Pedido pedido;
	public ProductoMenu productoMenu;
	
	@BeforeEach
	public void set() {
		ProductoMenu hamburguesa = this.productoMenu.productoMenu("Hamburguesa", 20000, 400);
		ProductoMenu papas = this.productoMenu.productoMenu("Papas Gigante", 150000, 2000);
	}
	
	@BeforeEach
	public void setUp() {
		this.pedido = new Pedido();
		this.pedido.pedido("Nicole", "Calle 27A #3-24");
	}
	
	@BeforeEach
	public void agregarProducto() {
		this.pedido.agregarProducto(this.hamburguesa);
		assertThrows(PedidoSuperiorException.class, () -> this.pedido.agregarProducto(this.papas));
	}
	
	@Test
	public void testgetIdPedido() {
		this.pedido.setIdPedido(001, pedido);
		assertEquals(001, this.pedido.getIdPedido());
	}
	
	@Test
	public void testgetItems() {
		assertEquals(this.hamburguesa, this.pedido.getItems());
	}

	@Test
	public void testgetPrecioNetoPedido() {
		assertEquals(20000, this.pedido.getPrecioNetoPedido());
	}
	
	@Test
	public void testgetPrecioIVAPedido() {
		assertEquals(3800, this.pedido.getPrecioIVAPedido());
	}
	
	@Test
	public void testgetPrecioTotalPedido() {
		assertEquals(23800, this.pedido.getPrecioTotalPedido());
	}
	
	@Test
	public void testgetCaloriasTotalPedido() {
		assertEquals(400, this.pedido.getCaloriasTotalPedido());
	}
	
	@Test
	public void testEquals() {
		assertFalse(this.pedido.equals(pedido));
	}
	
	@Test
	public void testgenerarTextoFactura() {
		assertEquals("******************  FACTURA DE COMPRA  ********************\n\nNombre cliente : Nicole\nDirección cliente : Calle27A #3-24\nId Pedido :001\n\n\nDETALLE DE ARTICULOS ORDENADOS\n\n\n"
				+ "Artículo: Hamburguesa, Valor: $20000, Calorias: 400"
				+ "\n" + 
				"TOTAL CALORIAS       : 400\n" +
				"TOTAL NETO PEDIDO    : $20000\n" +
				"TOTAL IVA            : $3800\n" +
				"TOTAL PEDIDO CON IVA : $23800\n" +
				"***********************************************************\n\n", this.pedido.generarTextoFactura());
	}
	
	@Test
	public void testguardarFactura() {
		assertThrows(IOException.class, () -> this.pedido.guardarFactura(File));
		
	}
	
}
