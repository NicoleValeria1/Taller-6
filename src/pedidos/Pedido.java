package pedidos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Pedido {
	private int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private  List<IProducto> itemsPedido;
	
	
	public void pedido(String nombreCliente, String direccionCliente)
	{
		this.nombreCliente=nombreCliente;
		this.direccionCliente=direccionCliente;
		this.itemsPedido=new ArrayList<>();
		
		
	}
	
	public Object clone()  {
		Object obj = null;
		
		obj = clone();
		
		return obj;
	}
	
	
	public void setIdPedido(int numeroPedido, Pedido pedidoEnCurso)
	{
		numeroPedidos=numeroPedido; 
		idPedido= numeroPedido;
		this.nombreCliente=pedidoEnCurso.nombreCliente;
		this.direccionCliente=pedidoEnCurso.direccionCliente;
		this.itemsPedido=pedidoEnCurso.itemsPedido;
		
		
	}
	
	public int getIdPedido()
	{
		
		return idPedido;
		
	}
	
	public List<IProducto> getItems()
	{
		
		return this.itemsPedido;
		
	}
	
	public void agregarProducto(IProducto nuevoItem)
	{
		int total = getPrecioTotalPedido();
		try {
		itemsPedido.add(nuevoItem);}
		catch(PedidoSuperiorException ex) {
		    ex.pedidoSuperiorException(total);
		}
			
		}
		
	}
	public int getPrecioNetoPedido()
	{
		
		int precioBase=0;
		
		for (IProducto itemPedido : itemsPedido)
		{
			precioBase=precioBase+itemPedido.getPrecio();
			
		}
		
		return precioBase;
	}
	
	public int getPrecioIVAPedido()
	{
		int precioBase=getPrecioNetoPedido();
		int valorIVA=(int)(precioBase*0.19);
		return valorIVA;
				
	}
	
	public int getPrecioTotalPedido()
	{
		int precioBase=getPrecioNetoPedido();
		int valorIVA=getPrecioIVAPedido();
		int valorTotal=precioBase+valorIVA;
		return valorTotal;
		
	}
	
	public int getCaloriasTotalPedido()
	{
		int caloriasBase=0;
			
		for (IProducto itemPedido : itemsPedido)
		{
			caloriasBase=caloriasBase+itemPedido.getCalorias();
			
		}
		
		return caloriasBase;
		
		
	}
	
	@Override
	public boolean equals(Object obj) {
		int i=0;
		boolean igual=true;
		
		Pedido pedido1 = (Pedido)obj;
		
		if (!this.nombreCliente.equals(pedido1.nombreCliente))
		{
			igual=false;
			return igual;
		}
		if (!this.direccionCliente.equals(pedido1.direccionCliente))
		{
			igual=false;
			return igual;
		}
		if (this.itemsPedido.size()!=(pedido1.itemsPedido.size()))
		{
			igual=false;
			return igual;
		}
		if (this.getPrecioTotalPedido()!=(pedido1.getPrecioTotalPedido()))
		{
			igual=false;
			return igual;
		}
		for (IProducto items: itemsPedido)
		{
			if (!this.itemsPedido.get(i).getNombre().equalsIgnoreCase(pedido1.itemsPedido.get(i).getNombre()))
			{
				igual=false;
				return igual;
			}
			if (!this.itemsPedido.get(i).getClass().equals(pedido1.itemsPedido.get(i).getClass()))
			{
				igual=false;
				return igual;
			}
		}
		
		return igual;
    }
	
	public String generarTextoFactura()	
	{
		String cadenaImprimir;
		int idPedido=getIdPedido();
		cadenaImprimir=
				"******************  FACTURA DE COMPRA  ********************" + "\n\n" +
		        "Nombre cliente : " + nombreCliente + "\n" +
				"Dirección cliente : " + direccionCliente 	+ "\n" +
		        "Id Pedido :" + idPedido + "\n\n\n" +
		        "DETALLE DE ARTICULOS ORDENADOS" + "\n\n\n";
		int precioBase=getPrecioNetoPedido();
		int valorIVA=getPrecioIVAPedido();
		int valorTotal= getPrecioTotalPedido();
		int totalCalorias=getCaloriasTotalPedido();
		
		for (IProducto itemPedido : itemsPedido)
		{
			cadenaImprimir=cadenaImprimir+"\n" + itemPedido.generarTextoFactura()
			+"\n";
		}
		cadenaImprimir=cadenaImprimir+"\n" + 
		"TOTAL CALORIAS       : " + totalCalorias + "\n" +
		"TOTAL NETO PEDIDO    : $" + precioBase +"\n" +
		"TOTAL IVA            : $" + valorIVA +"\n" +
		"TOTAL PEDIDO CON IVA : $" + valorTotal +"\n" +
		"***********************************************************" + "\n\n";
		
		
		return cadenaImprimir;
	}
	
	
	public void guardarFactura(File archivo)
	{
		try {
			String directorio = System.getProperty("user.dir");
			String archivoNombre=Integer.toString(idPedido)+".txt";
			String path = directorio+"\\data\\"+archivoNombre;
			File file = new File(path);
			if(!file.exists())
	            file.createNewFile();
			else
			{
				file.delete();
				file.createNewFile();
			}
			
			try
			 (FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); 
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				    {
				        out.println(generarTextoFactura());
				        
				} catch (IOException e) {
				    //exception handling left as an exercise for the reader
				}
		}
		catch(Exception e) {
            e.printStackTrace();
        }
        
       
	}
}
