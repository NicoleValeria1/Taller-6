package pedidos;

public class PedidoSuperiorException {
	
	public String pedidoSuperiorException(int total) throws HamburguesaException {
		if (150000 > total){
			throw new HamburguesaException("El pedido supera el tope de valor");
		}
	}

}
