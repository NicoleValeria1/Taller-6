package pedidos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class Restaurante {
	private  ArrayList<Ingrediente> ingredientes;
	private  ArrayList<ProductoMenu> menuBase;
	private  ArrayList<Combo> combos;
	private  ArrayList<Pedido> pedidos;
	private  Pedido pedidoEnCurso;
	private ProductoMenu nuevoProductoMenu;

	public void restaurante()
	{
		this.ingredientes= new ArrayList<>();
		this.menuBase= new ArrayList<>();
		this.combos= new ArrayList<>();
		this.pedidos= new ArrayList<>();
		this.pedidoEnCurso=null;
	}
	
	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		if (pedidoEnCurso == null) {
			pedidoEnCurso = new Pedido();
		}
		pedidoEnCurso.pedido(nombreCliente, direccionCliente);
		
	}
	
	
	public Integer cerrarYGuardarPedido() 
	{
		Integer idPedido = null;
		
		boolean encontro=false;
		Pedido pedidoCerrado = new Pedido();
		
		for(Pedido pedido: pedidos)
		{
			if (pedido.equals(pedidoEnCurso))
			{
				encontro=true;				
				break;
			}							
		}
		if(encontro)
		{
			System.out.println("Error: Ya existe un pedido con la misma información");
		}
		else
		{
			pedidoCerrado.setIdPedido(pedidos.size()+1,pedidoEnCurso);
			pedidos.add(pedidoCerrado);
			pedidoCerrado.guardarFactura(null);
			pedidoEnCurso = null;
			idPedido = pedidoCerrado.getIdPedido();
		}
		return idPedido;
		
		
	}
	
	
	public Pedido buscarPedido(int idPedido)
	{
		boolean wEncontro=false;
		int i=0;
		for(Pedido pedido: pedidos)
		{
			if(pedido.getIdPedido()==idPedido)
			{
				wEncontro=true;
				break;
			}
			i=i+1;
		}
		if (wEncontro)
		{
			return pedidos.get(i);
		}
		else
		{
			return null;
		}
	}
	
	
	public Pedido getPedidoEnCurso()
	{
		return pedidoEnCurso;
		
	}
	
	
	public ArrayList<ProductoMenu> getMenuBase()
	{
		return menuBase;
	}
	
	
	public ArrayList<Ingrediente> getIngredientes()
	{
		return ingredientes;
	}
	
	public ArrayList<Combo> getCombos()
	{
		return combos;
	}
	
	private void adicionarIngrediente(String lineaarchivo)
	{
		String nombreIngrediente;
		int precioIngrediente;
		int posicion;
		String datosCombo2;
		String datosCombo3;
		int caloriasIngrediente;
		
		
		if (lineaarchivo!="")
		{
			posicion=lineaarchivo.indexOf(";");
		    nombreIngrediente=lineaarchivo.substring(0, posicion);
		    datosCombo2=lineaarchivo.substring(posicion+1, lineaarchivo.length());
		    posicion=datosCombo2.indexOf(";");
		    
		    precioIngrediente=Integer.parseInt(datosCombo2.substring(0, posicion));
		    datosCombo3=datosCombo2.substring(posicion+1, datosCombo2.length());
		    caloriasIngrediente=Integer.parseInt(datosCombo3.substring(0, datosCombo3.length()));
		    
		    Ingrediente nuevoIngrediente = new Ingrediente();
			nuevoIngrediente.ingrediente(nombreIngrediente, precioIngrediente,caloriasIngrediente);
		    ingredientes.add(nuevoIngrediente);
	
		}
	}
	
	private void cargarIngredientes(File archivoIngredientes)
	{
		
		String nombreFichero = archivoIngredientes.getPath();
		// Declarar una variable BufferedReader
		BufferedReader br = null;
		
		try 
		{
		    // Crear un objeto BufferedReader al que se le pasa 
		    //   un objeto FileReader con el nombre del fichero
		    br = new BufferedReader(new FileReader(nombreFichero));
		    
		    // Leer la primera línea, guardando en un String
		    String texto = br.readLine();
		    
		    
		    // Repetir mientras no se llegue al final del fichero
		    while(texto != null) 
		    {
		        // Hacer lo que sea con la línea leída
		        // En este ejemplo sólo se muestra por consola
		        adicionarIngrediente(texto);
		        // Leer la siguiente línea
		        texto = br.readLine();
		        
		    }
		}
		// Captura de excepción por fichero no encontrado
		catch (FileNotFoundException ex) {
		    System.out.println("Error: Fichero no encontrado");
		    ex.printStackTrace();
		}
		// Captura de cualquier otra excepción
		catch(Exception ex) {
		    System.out.println("Error de lectura del fichero");
		    ex.printStackTrace();
		}
		// Asegurar el cierre del fichero en cualquier caso
		finally {
		    try {
		        // Cerrar el fichero si se ha podido abrir
		        if(br != null) {
		            br.close();
		        }
		    }
		    catch (Exception ex) {
		        System.out.println("Error al cerrar el fichero");
		        ex.printStackTrace();
		    }
		}
	}
	
	private void adicionarProducto(String lineaarchivo)
	{
		String nombreProducto;
		int precioProducto;
		int caloriasProducto;
		int posicion;
		String datosCombo2;
		String datosCombo3;
		
		if (lineaarchivo!= "")
		{
			posicion=lineaarchivo.indexOf(";");
		    nombreProducto=lineaarchivo.substring(0, posicion);
		    datosCombo2=lineaarchivo.substring(posicion+1, lineaarchivo.length());
		    posicion=datosCombo2.indexOf(";");
		    
		    precioProducto=Integer.parseInt(datosCombo2.substring(0, posicion));
		    datosCombo3=datosCombo2.substring(posicion+1, datosCombo2.length());
		    
		    caloriasProducto= Integer.parseInt(datosCombo3.substring(0, datosCombo3.length()));
		    
		    
		    ProductoMenu nuevoProducto = new ProductoMenu();
			nuevoProducto.productoMenu(nombreProducto, precioProducto,caloriasProducto);
		    menuBase.add(nuevoProducto);
		 
		}
	}
	
	
	private void cargarMenu(File archivoMenu, File archivoBebidas)
	{
		
		String nombreFichero = archivoMenu.getPath();
		// Declarar una variable BufferedReader
		BufferedReader br = null;
		
		try 
		{
		    // Crear un objeto BufferedReader al que se le pasa 
		    //   un objeto FileReader con el nombre del fichero
		    br = new BufferedReader(new FileReader(nombreFichero));
		    
		    // Leer la primera línea, guardando en un String
		    String texto = br.readLine();
		    
		    
		    // Repetir mientras no se llegue al final del fichero
		    while(texto != null) 
		    {
		        // Hacer lo que sea con la línea leída
		        // En este ejemplo sólo se muestra por consola
		        adicionarProducto(texto);
		        // Leer la siguiente línea
		        texto = br.readLine();
		        
		    }
		}
		// Captura de excepción por fichero no encontrado
		catch (FileNotFoundException ex) {
		    System.out.println("Error: Fichero no encontrado");
		    ex.printStackTrace();
		}
		// Captura de cualquier otra excepción
		catch(Exception ex) {
		    System.out.println("Error de lectura del fichero");
		    ex.printStackTrace();
		}
		// Asegurar el cierre del fichero en cualquier caso
		finally {
		    try {
		        // Cerrar el fichero si se ha podido abrir
		        if(br != null) {
		            br.close();
		        }
		    }
		    catch (Exception ex) {
		        System.out.println("Error al cerrar el fichero");
		        ex.printStackTrace();
		    }
		}
		
		//Cargue de bebidas//
		String nombreFichero01 = archivoBebidas.getPath();
		// Declarar una variable BufferedReader
		BufferedReader br01 = null;
		
		try 
		{
		    // Crear un objeto BufferedReader al que se le pasa 
		    //   un objeto FileReader con el nombre del fichero
		    br01 = new BufferedReader(new FileReader(nombreFichero01));
		    
		    // Leer la primera línea, guardando en un String
		    String texto = br01.readLine();
		    
		    
		    // Repetir mientras no se llegue al final del fichero
		    while(texto != null) 
		    {
		        // Hacer lo que sea con la línea leída
		        // En este ejemplo sólo se muestra por consola
		        adicionarProducto(texto);
		        // Leer la siguiente línea
		        texto = br01.readLine();
		        
		    }
		}
		// Captura de excepción por fichero no encontrado
		catch (FileNotFoundException ex) {
		    System.out.println("Error: Fichero no encontrado");
		    ex.printStackTrace();
		}
		// Captura de cualquier otra excepción
		catch(Exception ex) {
		    System.out.println("Error de lectura del fichero");
		    ex.printStackTrace();
		}
		// Asegurar el cierre del fichero en cualquier caso
		finally {
		    try {
		        // Cerrar el fichero si se ha podido abrir
		        if(br01 != null) {
		            br01.close();
		        }
		    }
		    catch (Exception ex) {
		        System.out.println("Error al cerrar el fichero");
		        ex.printStackTrace();
		    }
		}
	}
	
	private ProductoMenu getProducto(String nombreProducto)
	{
		int i=0;
		nuevoProductoMenu = new ProductoMenu();
		
		for (ProductoMenu producto : menuBase)
		{
			if (producto.getNombre().compareTo(nombreProducto)== 0)
				
			{
				break;
			}
			i =i+1;
		}
		
		if (menuBase.get(i) != null)
		{
			nuevoProductoMenu.productoMenu(menuBase.get(i).getNombre(),
                    menuBase.get(i).getPrecio(),
                    menuBase.get(i).getCalorias());
			
			
		}
		return nuevoProductoMenu;	
	}
	
	private void adicionarCombo(String lineaarchivo)
	{
		String nombreCombo;
		
		int posicion;
		String datosCombo2;
		String datosCombo3;
		double valorDescuento;
		String nombreProducto;
		Combo nuevoCombo=new Combo();
		
		ArrayList<String> listaProductos = new ArrayList<String>();
		
		if (lineaarchivo !="")
		{
			
		
			posicion=lineaarchivo.indexOf(";");
		    nombreCombo=lineaarchivo.substring(0, posicion);
		    
		    datosCombo2=lineaarchivo.substring(posicion+1, lineaarchivo.length());
		    posicion=datosCombo2.indexOf(";");
		    valorDescuento=Double.parseDouble(datosCombo2.substring(0, posicion-1));
		    
		    datosCombo3=datosCombo2.substring(posicion+1, datosCombo2.length());
		    
		    while (datosCombo3.length() > 0)
		    {
		    	posicion=datosCombo3.indexOf(";");
		    	if (posicion!= -1)
		    	{
		    		nombreProducto=datosCombo3.substring(0, posicion);
		    		
		    		datosCombo3=datosCombo3.substring(posicion+1, datosCombo3.length());
		    		
		    	}
		    	else
		    	{
		    		nombreProducto=datosCombo3.substring(0, datosCombo3.length());
		    		datosCombo3="";
		    	}
		    	if (nombreProducto!="")
		    	{
		    		listaProductos.add(nombreProducto);
		    	}
		    }
		    
		    
		    
		    nuevoCombo.combo(nombreCombo, valorDescuento);
		    for (String itemLista : listaProductos)
			{
		    	nuevoCombo.agregarIteamACombo(getProducto(itemLista));
			}
		    
		    
		    combos.add(nuevoCombo);
		}
	    
	}
	
	private void cargarCombos(File archivoCombos)
	{
		
		String nombreFichero = archivoCombos.getPath();
		// Declarar una variable BufferedReader
		BufferedReader br = null;
		
		try 
		{
		    // Crear un objeto BufferedReader al que se le pasa 
		    //   un objeto FileReader con el nombre del fichero
		    br = new BufferedReader(new FileReader(nombreFichero));
		    
		    // Leer la primera línea, guardando en un String
		    String texto = br.readLine();
		    
		    
		    // Repetir mientras no se llegue al final del fichero
		    while(texto != null) 
		    {
		        // Hacer lo que sea con la línea leída
		        // En este ejemplo sólo se muestra por consola
		        adicionarCombo(texto);
		        // Leer la siguiente línea
		        texto = br.readLine();
		        
		    }
		}
		// Captura de excepción por fichero no encontrado
		catch (FileNotFoundException ex) {
		    System.out.println("Error: Fichero no encontrado");
		    ex.printStackTrace();
		}
		// Captura de cualquier otra excepción
		catch(Exception ex) {
		    System.out.println("Error de lectura del fichero");
		    ex.printStackTrace();
		}
		// Asegurar el cierre del fichero en cualquier caso
		finally {
		    try {
		        // Cerrar el fichero si se ha podido abrir
		        if(br != null) {
		            br.close();
		        }
		    }
		    catch (Exception ex) {
		        System.out.println("Error al cerrar el fichero");
		        ex.printStackTrace();
		    }
		}
	}
	
	public void cargarInformacionRestaurante (File archivoIngredientes, File archivoMenu,File archivoCombos,File archivoBebidas)
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu,archivoBebidas);
		cargarCombos(archivoCombos);
	}
}
