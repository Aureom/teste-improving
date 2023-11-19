package br.com.improving.carrinho;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {

	private final Map<Produto, Item> itens;

	public CarrinhoCompras() {
		this.itens = new LinkedHashMap<>();
	}

	/**
	 * Permite a adição de um novo item no carrinho de compras.
	 * <p>
	 * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
	 * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
	 * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
	 * o passado como parâmetro.
	 * <p>
	 * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
	 *
	 * @param produto       O produto a ser adicionado como item.
	 * @param valorUnitario O valor unitário do produto.
	 * @param quantidade    A quantidade do produto.
	 */
	public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
		validarItem(produto, valorUnitario, quantidade);

		Item item = itens.get(produto);
		if (item != null) {
			item.setQuantidade(item.getQuantidade() + quantidade);
			item.setValorUnitario(valorUnitario);
		} else {
			itens.put(produto, new Item(produto, valorUnitario, quantidade));
		}
	}

	/**
	 * Verifica se um produto, sua quantidade e seu valor unitário são válidos.
	 *
	 * @param produto       O produto a ser verificado. Não pode ser null.
	 * @param valorUnitario O valor unitário do produto. Não pode ser null.
	 * @param quantidade    A quantidade do produto. Deve ser maior que zero.
	 * @throws IllegalArgumentException se produto for null, valorUnitario for null ou quantidade for zero ou negativo.
	 */
	private void validarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
		if (produto == null) {
			throw new IllegalArgumentException("O produto não pode ser nulo");
		} else if (valorUnitario == null) {
			throw new IllegalArgumentException("O valor unitário não pode ser nulo");
		} else if (quantidade <= 0) {
			throw new IllegalArgumentException("A quantidade deve ser maior que zero");
		}
	}

	/**
	 * Permite a remoção do item que representa este produto do carrinho de compras.
	 *
	 * @param produto
	 * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
	 * caso o produto não exista no carrinho.
	 */
	public boolean removerItem(Produto produto) {
		if (itens.containsKey(produto)) {
			itens.remove(produto);
			return true;
		}
		return false;
	}

	/**
	 * Permite a remoção do item de acordo com a posição.
	 * Essa posição deve ser determinada pela ordem de inclusão do produto na
	 * coleção, em que zero representa o primeiro item.
	 *
	 * @param posicaoItem
	 * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
	 * caso o produto não exista no carrinho.
	 */
	public boolean removerItem(int posicaoItem) {
		if (posicaoItem < 0 || posicaoItem >= itens.size()) {
			return false;
		}

		itens.keySet().stream()
				.skip(posicaoItem)
				.findFirst()
				.ifPresent(itens::remove);

		return true;
	}

	/**
	 * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
	 * de todos os itens que compõem o carrinho.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorTotal() {
		return itens.values().stream()
				.map(Item::getValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	/**
	 * Retorna a lista de itens do carrinho de compras.
	 *
	 * @return itens
	 */
	public Collection<Item> getItens() {
		return itens.values();
	}
}