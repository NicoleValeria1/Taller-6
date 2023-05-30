package pedidos;

public class ProductoRepetidoException {
	
	public String productoRepetidoException(ProductoMenu lista, String producto) throws HamburguesaException {
		if (producto == lista.getNombre()){
			throw new HamburguesaException("El producto ya existe en la lista");
		}
	}

}
