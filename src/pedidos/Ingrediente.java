package pedidos;

public class Ingrediente {
	private String nombre;
	private int costoAdicional;
	private int calorias;


	// ************************************************************************
		// Constructores
		// ************************************************************************

		/**
		 * Construye un nuevo ingrediente e inicializa sus atributos con la información de
		 * los parámetros. 
		 * 
		 * @param nombre El nombre del ingrediente.
		 * @param costoAdicional El costo de cada ingrediente que se suma al pedido.
		
		 */
	public void ingrediente(String nombre, int costoAdicional,int calorias)
	{
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
		this.calorias=calorias;
	}
	public String getNombre()
	{
		return nombre;
	}
	public int getCostoAdiconal()
	{
		return costoAdicional;
	}
	
	public int getCalorias()
	{
		return calorias;
	}
}
