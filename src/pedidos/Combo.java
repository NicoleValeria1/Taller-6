package pedidos;

import java.util.ArrayList;
import java.util.List;

public class Combo implements IProducto{
	
	private  List<ProductoMenu> itemsCombo;
	private double descuento; 
	private String nombreCombo;
	
	public void combo(String nombre, double descuento)
	{
		this.descuento=descuento;
		this.nombreCombo=nombre;
		this.itemsCombo= new ArrayList<>();
		
	}
	
	
	public void agregarIteamACombo(ProductoMenu itemCombo)
	{
		itemsCombo.add(itemCombo);
	}
	
	
	public int getPrecio()
	{
		
		int precioBase=0;
		int precioFinal=0;
		for (ProductoMenu itemCombo : itemsCombo)
		{
			precioBase=precioBase+itemCombo.getPrecio();
			
		}
		precioFinal=(int) (precioBase*(descuento/100));
		return precioFinal;
	}
	
	public int getCalorias()
	{
		
		int caloriasBase=0;
		;
		for (ProductoMenu itemCombo : itemsCombo)
		{
			caloriasBase=caloriasBase+itemCombo.getCalorias();
			
		}
		
		return caloriasBase;
	}
	public String generarTextoFactura()	
	{
		String cadenaImprimir;
		cadenaImprimir="   Nombre Combo: "+ nombreCombo+"\n\n";
		cadenaImprimir= cadenaImprimir+" 		Items: ";
		for (ProductoMenu itemCombo : itemsCombo)
		{
			cadenaImprimir=cadenaImprimir+"\n" + "		"+itemCombo.generarTextoFactura();
		}
		
		return cadenaImprimir;
	}
	public String getNombre()
	{
		return nombreCombo;
	}
}
