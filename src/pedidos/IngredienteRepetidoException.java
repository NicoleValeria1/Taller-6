package pedidos;

public class IngredienteRepetidoException {

	public String ingredienteRepetidoException(Ingrediente lista, String ingrediente) throws HamburguesaException {
		if (ingrediente == lista.getNombre()){
			throw new HamburguesaException("El ingrediente ya existe en la lista");
		}
	}
}
