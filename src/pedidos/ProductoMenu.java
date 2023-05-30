package pedidos;

public class ProductoMenu implements IProducto
{
	private String nombre;
	private int precioBase;
	private int calorias;
	
	
	/* Constructor*/
	public void productoMenu(String nombre, int precioBase,int calorias)
	{
		this.nombre=nombre;
		this.precioBase=precioBase;
		this.calorias=calorias;
		
	}
	public String getNombre()
	{
		return nombre;
	}
	public int getPrecio()
	{
		return precioBase;
	}
	public int getCalorias()
	{
		return calorias;
	}
	
	public String generarTextoFactura()
	{
		String cadena;
		String valorPrecio;
		String stCalorias;
		int numCalorias;
		int numPrecio;
		numPrecio=precioBase;
		numCalorias=calorias;
		valorPrecio=Integer.toString(numPrecio);
		stCalorias=Integer.toString(numCalorias);
		
		cadena= "Artículo: " + nombre + ", Valor: $"+ valorPrecio+", Calorias: "+ stCalorias;
		return cadena;
	}

}
