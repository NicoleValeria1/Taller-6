package pedidos;

import java.util.ArrayList;
import java.util.List;





public class ProductoAjustado implements IProducto{
	
	private ProductoMenu base;
	/**
	 * Una lista con los ingredientes agregados.
	 */
	private List<Ingrediente> agregados;
	/**
	 * Una lista con los ingredientes eliminados.
	 */
	private List<Ingrediente> eliminados;
	
	
	
	
	/*Constructor*/
	public void productoAjustado(ProductoMenu base)
	{
		this.base=base;
		this.agregados= new ArrayList<>();
		this.eliminados=new ArrayList<>();
		
				
	}
	public String getNombre()
	{
		return base.getNombre();
	}
	public int getPrecio()
	{
		int precioBase=base.getPrecio();
		for (Ingrediente ingredienteExtra : agregados)
		{
			precioBase=precioBase+ingredienteExtra.getCostoAdiconal();
		}
		return precioBase;
	}
	public String generarTextoFactura()	
	{
		String cadenaImprimir = "";
		cadenaImprimir=base.generarTextoFactura();
		for (Ingrediente ingredienteExtra : agregados)
		{
			cadenaImprimir=cadenaImprimir+"\n"+ "  Ingredientes Adicionados: "+"\n"+ ingredienteExtra.getNombre()+
							", $" + Integer.toString(ingredienteExtra.getCostoAdiconal()) +
							", Calorias:" + Integer.toString(ingredienteExtra.getCalorias());
		}
		for (Ingrediente ingredienteQuitar : eliminados)
		{
			cadenaImprimir=cadenaImprimir+"\n"+ "Ingredientes Eliminados:"+"\n"+
					ingredienteQuitar.getNombre()+
					", Calorias: " + Integer.toString(ingredienteQuitar.getCalorias());
	
		}
		cadenaImprimir=cadenaImprimir+"\n";
		return cadenaImprimir;
	}
	
	public int getCalorias()
	{
		int caloriasBase=base.getCalorias();
		for (Ingrediente ingredienteExtra : agregados)
		{
			caloriasBase=caloriasBase+ingredienteExtra.getCalorias();
		}
		for (Ingrediente ingredienteEliminar : eliminados)
		{
			caloriasBase=caloriasBase-ingredienteEliminar.getCalorias();
		}
		return caloriasBase;
	}
	
	/*Método*/
	
	public void agregarIngredienteExtra(Ingrediente nuevoIngrediente)
	{
		agregados.add(nuevoIngrediente);
	}
	public void quitarIngrediente(Ingrediente quitarIngrediente)
	{
		eliminados.add(quitarIngrediente);
	}
}

