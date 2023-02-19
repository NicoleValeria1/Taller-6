package Aplicacion;


import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import pedidos.Combo;
import pedidos.Ingrediente;
import pedidos.Pedido;
import pedidos.ProductoAjustado;
import pedidos.ProductoMenu;
import pedidos.Restaurante;


	
	
	public class Aplicacion 
	{
		
		private Restaurante nuevoRestaurante;

		
		public void ejecutarAplicacion() 
		{
			
					
			System.out.println("Restaurantes\n");
			

			boolean continuar = true;
			while (continuar)
			{
				try
				{
					mostrarMenu();
					int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
					if (opcion_seleccionada == 1)
						ejecutarDespliegueMenuTotal();
					else if (opcion_seleccionada == 2)
					{
						ejecutarIniciarPedido();						
					}
					else if (opcion_seleccionada == 3)
					{
						ejecutarAgregarProductoAPedido();
						
					}
					else if (opcion_seleccionada == 4)
					{
						ejecutarCerrarPedido();						
					}
					else if (opcion_seleccionada == 5)
					{
						ejecutarBuscarPedido();						
					}
					else if (opcion_seleccionada == 6)
					{
						System.out.println("Saliendo de la aplicación ...");
						continuar = false;
					}
					
					else
					{
						System.out.println("Por favor seleccione una opción válida.");
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Debe seleccionar uno de los números de las opciones.");
				}
			}
		}

		
		public void mostrarMenu()
		{
			System.out.println("\nOpciones de la aplicación\n");
			System.out.println("1. Mostrar menu");
			System.out.println("2. Iniciar Pedido");
			System.out.println("3. Agregar un elemento a un pedido");
			System.out.println("4. Cerrar Pedido y guardar factura");
			System.out.println("5. Buscar Pedido por su id");
			System.out.println("6. Salir de la aplicación\n");
			
		}

		
		
		private void cargarNuevoRestaurante()
		{
			File menu = null;
		    FileReader fr = null;
		    
		    File ingredientes = null;
		    
		    File combos = null;
		    File bebidas=null;
		    
		    String directorio = System.getProperty("user.dir");
		    
			if (nuevoRestaurante == null)
			{
				
			    
			    
			    try {
			         
			         menu = new File (directorio + "\\data\\menu.txt");
			         fr = new FileReader (menu);
			      }
			      catch(Exception e){
			         e.printStackTrace();
			      }finally{
			         // En el finally cerramos el fichero, para asegurarnos
			         // que se cierra tanto si todo va bien como si salta 
			         // una excepcion.
			         try{                    
			            if( null != fr ){   
			               fr.close();     
			            }                  
			         }catch (Exception e2){ 
			            e2.printStackTrace();
			         }
			      }
			    
			    
			    try {
			         
			         ingredientes = new File (directorio + "\\data\\ingredientes.txt");
			         fr = new FileReader (ingredientes);
			      }
			      catch(Exception e){
			         e.printStackTrace();
			      }finally{
			         
			         try{                    
			            if( null != fr ){   
			               fr.close();     
			            }                  
			         }catch (Exception e2){ 
			            e2.printStackTrace();
			         }
			      }
			    
			    
			    try {
			         
			         combos = new File (directorio + "\\data\\combos.txt");
			         fr = new FileReader (combos);
			      }
			      catch(Exception e){
			         e.printStackTrace();
			      }finally{
			         
			         try{                    
			            if( null != fr ){   
			               fr.close();     
			            }                  
			         }catch (Exception e2){ 
			            e2.printStackTrace();
			         }
			      }
			    try {
			         
			         bebidas = new File (directorio + "\\data\\bebidas.txt");
			         fr = new FileReader (bebidas);
			      }
			      catch(Exception e){
			         e.printStackTrace();
			      }finally{
			         
			         try{                    
			            if( null != fr ){   
			               fr.close();     
			            }                  
			         }catch (Exception e2){ 
			            e2.printStackTrace();
			         }
			      }
			   this.nuevoRestaurante=new Restaurante();
			   this.nuevoRestaurante.restaurante();
			   this.nuevoRestaurante.cargarInformacionRestaurante(ingredientes, menu, combos,bebidas);
			}
		}
		
		private void imprimirMenu(Restaurante restaurante)
		{
			int i=1;
			String  cadenaOpcionMenu = "\n\n****************************************************" + "\n";
			System.out.println(cadenaOpcionMenu);
			cadenaOpcionMenu = "                    MENU BASE                        " + "\n";
			System.out.println(cadenaOpcionMenu);
			cadenaOpcionMenu = "****************************************************" + "\n";
			System.out.println(cadenaOpcionMenu);
			for (ProductoMenu productoMenu: restaurante.getMenuBase())
			{
				cadenaOpcionMenu = "  " + i+ "--"+ productoMenu.getNombre()+"-  $"+
						productoMenu.getPrecio();
				System.out.println(cadenaOpcionMenu);
				i=i+1;
			}
			cadenaOpcionMenu = " ";
			System.out.println(cadenaOpcionMenu);
		}
		
		private void imprimirMenuCombos(Restaurante restaurante)
		{
			int i=1;
			String  cadenaOpcionMenu = "\n\n****************************************************" + "\n";
			System.out.println(cadenaOpcionMenu);
			cadenaOpcionMenu = "                    MENU COMBOS                     " + "\n";
			System.out.println(cadenaOpcionMenu);
			cadenaOpcionMenu = "****************************************************" + "\n";
			System.out.println(cadenaOpcionMenu);
			for (Combo combo: restaurante.getCombos())
			{
				cadenaOpcionMenu = i+ "--"+ combo.getNombre()+ "-  $"+
						combo.getPrecio();
				System.out.println(cadenaOpcionMenu);
				i=i+1;
			}
			cadenaOpcionMenu = " ";
			System.out.println(cadenaOpcionMenu);
		}
		
		public void imprimirIngredientes(Restaurante restaurante)
		{
			int i=1;
			String  cadenaOpcionMenu = "\n\n****************************************************" + "\n";
			System.out.println(cadenaOpcionMenu);
			cadenaOpcionMenu = "               INGREDIENTES ADICIONALES             " + "\n";
			System.out.println(cadenaOpcionMenu);
			cadenaOpcionMenu = "****************************************************" + "\n";
			System.out.println(cadenaOpcionMenu);
			for (Ingrediente ingrediente: restaurante.getIngredientes())
			{
				cadenaOpcionMenu = "  " + i+ "--"+ ingrediente.getNombre()+ "-  $"+
						ingrediente.getCostoAdiconal();
				System.out.println(cadenaOpcionMenu);
				i=i+1;
			}
			cadenaOpcionMenu = " ";
			System.out.println(cadenaOpcionMenu);
		}
		
		public static void limpiarPantalla(){
			System.out.print("\n\n\n\n\n\n\n");
			System.out.flush();
	    }
		
		
		public void ejecutarDespliegueMenuRestaurante()
		{
		   cargarNuevoRestaurante();			
		   imprimirMenu(nuevoRestaurante);	 
		}
		
		public void ejecutarDespliegueMenuTotal()
		{
		   String cadenaOpcionMenu = "";
		   cargarNuevoRestaurante();			
		   limpiarPantalla();
		   imprimirMenu(nuevoRestaurante);
		   imprimirMenuCombos(nuevoRestaurante);
		   imprimirIngredientes(nuevoRestaurante);
		   cadenaOpcionMenu = "\n\n****************************************************" + "\n";
		   System.out.println(cadenaOpcionMenu);
		   System.out.println("Presione enter para continuar...");
           new java.util.Scanner(System.in).nextLine();
          limpiarPantalla();
	 
		}
		
		public void ejecutarDespliegueMenuCombo()
		{
		   cargarNuevoRestaurante();
		   imprimirMenuCombos(nuevoRestaurante);
		    
		}
		
		public void ejecutarDespliegueIngredientes()
		{
			cargarNuevoRestaurante();
			imprimirIngredientes(nuevoRestaurante);
		}
		
	public void ejecutarIniciarPedido()
	{

		cargarNuevoRestaurante();
		
		if (nuevoRestaurante.getPedidoEnCurso() != null) 
		{
			System.out.print("\nERROR ... Ya existe un pedido en curso, debe finalizarlo para iniciar uno nuevo\n\n");
			System.out.println("Presione enter para continuar...");
            new java.util.Scanner(System.in).nextLine();
           limpiarPantalla();
		}
		else
		{
			System.out.print("\nPor favor ingrese los siguientes datos del pedido...\n\n");
			
			String wNombreCliente=input("Nombre Cliente");
			String wDireccionCliente=input("Dirección Cliente");
			
			nuevoRestaurante.iniciarPedido(wNombreCliente, wDireccionCliente);
			System.out.print("Se ha iniciado el pedido para el cliente:"+" "+wNombreCliente+ " En la dirección:"+" "+wDireccionCliente+"\n\n");
		}
	}
	
	
	public void ejecutarAgregarProductoAPedido()
	{
		if (nuevoRestaurante == null)
		{
			System.out.print("No existe un pedido en curso, no es posible agregar un producto.\n\n");
			System.out.println("Presione enter para continuar...");
            new java.util.Scanner(System.in).nextLine();
           limpiarPantalla();
		}
		else
		{	
			if (nuevoRestaurante.getPedidoEnCurso()== null)
			{
				System.out.print("No existe un pedido en curso, no es posible agregar un producto.\\n\\n");
				System.out.println("Presione enter para continuar...");
	            new java.util.Scanner(System.in).nextLine();
	           limpiarPantalla();
			}
			else
			{
				
				ProductoAjustado productoAjustado=new ProductoAjustado();
				System.out.print("\nPor favor indique si desea un producto del menu base o un combo,digite 1 para menu Base y 2 para combo..\n\n");
				String respuestaTipoMenu=input("Tipo Menu");
				int numeroRespuestaTipoMenu=Integer.valueOf(respuestaTipoMenu);
				if ((numeroRespuestaTipoMenu!=1)&&(numeroRespuestaTipoMenu!=2))
				{
					System.out.print("No se ha elegido una opción válida..\n\n");
					
				}
				else 
				{
					if (nuevoRestaurante.getPedidoEnCurso()!= null)
					{
						if (numeroRespuestaTipoMenu==1)
						{
							System.out.print("\nElija de la siguiente lista el numero del producto que desea adicionar.\n\n");
							imprimirMenu(nuevoRestaurante);
							String respuestaNumeroProducto=input("Numero Producto");
							int numeroRespuesta=Integer.valueOf(respuestaNumeroProducto);
							ProductoMenu productoMenu=nuevoRestaurante.getMenuBase().get(numeroRespuesta-1);
							productoAjustado.productoAjustado(productoMenu);
							
							
							String respuestaAdicionarIngrediente=input("\nDesea adicionar algún ingrediente?, marque 1 para si y 2 para no.");
							int numeroAdicionar= Integer.valueOf(respuestaAdicionarIngrediente);
							if (numeroAdicionar==1)
							{
								
								productoAjustado.productoAjustado(productoMenu);
								System.out.print("\nElija de la siguiente lista el numero del producto que desea adicionar.\n\n");
								ejecutarDespliegueIngredientes();
								
								
								boolean continuar=true;
								
								while (continuar)
								{
									String add=input("Numero Ingrediente");
									int addRespuesta=Integer.valueOf(add);
									
									Ingrediente ingrediente=nuevoRestaurante.getIngredientes().get(addRespuesta-1);
									productoAjustado.agregarIngredienteExtra(ingrediente);
									String preg=input("\nDesea agregar otro ingrediente a su pedido? Marque 1 para si y 2 para no");
									int numPreg=Integer.valueOf(preg);
									if (numPreg==1)
									{
										continuar=true;
									}
									else
									{
										continuar=false;
									}
								}
							}
							
							
							String respuestaQuitarIngrediente=input("\nDesea quitar algún ingrediente?, marque 1 para si y 2 para no.");
							int numeroQuitar= Integer.valueOf(respuestaQuitarIngrediente);
							if (numeroQuitar==1)
							{
								if (productoAjustado.getNombre()=="")
								{
									productoAjustado.productoAjustado(productoMenu);
								}
								
								System.out.print("\nElija de la siguiente lista el numero del ingrediente que desea eliminar.\n\n");
								ejecutarDespliegueIngredientes();
								
								
								boolean continuar=true;
								
								while (continuar)
								{
									String quit=input("Numero Ingrediente");
									int quitRespuesta=Integer.valueOf(quit);
									
									Ingrediente ingrediente=nuevoRestaurante.getIngredientes().get(quitRespuesta-1);
									productoAjustado.quitarIngrediente(ingrediente);
									String preg=input("\nDesea agregar otro ingrediente a su pedido? Marque 1 para si y 2 para no");
									int numPreg=Integer.valueOf(preg);
									if (numPreg==1)
									{
										continuar=true;
									}
									else
									{
										continuar=false;
									}
								}
							}
							
							
							if (productoAjustado.getNombre()!="")
							{
								nuevoRestaurante.getPedidoEnCurso().agregarProducto(productoAjustado);
								System.out.print("\nSe ha agregado exitosamente el producto\n\n"+ productoAjustado.generarTextoFactura() + "\n\n");
								System.out.println("Presione enter para continuar...");
					            new java.util.Scanner(System.in).nextLine();
					           limpiarPantalla();
							}
							else 
							{
								nuevoRestaurante.getPedidoEnCurso().agregarProducto(productoMenu);
								System.out.print("\nSe ha agregado exitosamente el producto\n\n"+ productoMenu.generarTextoFactura());
								System.out.println("Presione enter para continuar...");
					            new java.util.Scanner(System.in).nextLine();
					           limpiarPantalla();
							}
							
							
								
							
						}
						else if (numeroRespuestaTipoMenu==2)
						{
							System.out.print("\nElija de la siguiente lista el numero del combo que desea adicionar.\n\n");
							ejecutarDespliegueMenuCombo();
							String respuestaNumeroProducto=input("Numero combo");
							int numeroRespuesta=Integer.valueOf(respuestaNumeroProducto);
							Combo productoMenu=nuevoRestaurante.getCombos().get(numeroRespuesta-1);
							nuevoRestaurante.getPedidoEnCurso().agregarProducto(productoMenu);
							System.out.print("\nSe ha agregado exitosamente el producto\n  "+ productoMenu.generarTextoFactura());
						}
					}
					
				}
			}
		}
	}
	
	
	public void ejecutarCerrarPedido() 
	{
		Integer idPedido;
		
		if (nuevoRestaurante == null )
		{
			System.out.print("\nERROR... No existe un pedido para cerrar.\n\n");
			System.out.println("Presione enter para continuar...");
            new java.util.Scanner(System.in).nextLine();
           limpiarPantalla();
		}
		else
		{
			if (nuevoRestaurante.getPedidoEnCurso()== null)
			{
				System.out.print("\nERROR... No existe un pedido para cerrar.\n\n");
				System.out.println("Presione enter para continuar...");
	            new java.util.Scanner(System.in).nextLine();
	           limpiarPantalla();
			}
			else
			{
				if (nuevoRestaurante.getPedidoEnCurso().getItems().size() == 0)
				{
					System.out.print("\nERROR...No existe items para el pedido en curso, no es posible cerrarlo.\n\n");
					System.out.println("Presione enter para continuar...");
		            new java.util.Scanner(System.in).nextLine();
		           limpiarPantalla();
				}
				else
				{
					idPedido = nuevoRestaurante.cerrarYGuardarPedido();
					
					System.out.print("\nSe ha generado su pedido:  " + idPedido + " , verifique el archivo de factura generado.\n\n");	
					System.out.println("Presione enter para continuar...");
		            new java.util.Scanner(System.in).nextLine();
		           limpiarPantalla();
				}
			}
		}
	}
		
		public String input(String mensaje)
		{
			try
			{
				System.out.print(mensaje + ": ");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				return reader.readLine();
			}
			catch (IOException e)
			{
				System.out.println("Error leyendo de la consola");
				e.printStackTrace();
			}
			return null;
		}
		
		public void ejecutarBuscarPedido()
		{
			String respuestaNumeroPedido=input("Digite el número de pedido");
			int numeroRespuesta=Integer.valueOf(respuestaNumeroPedido);
			Pedido pedido=nuevoRestaurante.buscarPedido(numeroRespuesta);
			if (pedido==null)
			{
				System.out.println("No existe un pedido con el id:  "+ respuestaNumeroPedido);
			
			}
			else
			{
				System.out.println("Se ha encontrado un pedido con el id:  "+ respuestaNumeroPedido+"\n\n");
				
				
			}
		}
		

		public static void main(String[] args) 
		{
			Aplicacion consola = new Aplicacion();
			consola.ejecutarAplicacion();
		}
	}
	


