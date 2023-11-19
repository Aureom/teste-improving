package br.com.improving.carrinho;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Kaio Souza
 * @since 2023/11/19
 */
class CarrinhoComprasTest {

	private CarrinhoCompras carrinhoCompras;
	private Produto produto;

	@BeforeEach
	public void setUp() {
		carrinhoCompras = new CarrinhoCompras();
		produto = new Produto(123L, "Teste Produto");
	}

	@Test
	public void should_addItem_whenAdicionarItem() {
		carrinhoCompras.adicionarItem(produto, BigDecimal.valueOf(10), 5);

		assertEquals(1, carrinhoCompras.getItens().size());

		Item item = carrinhoCompras.getItens().iterator().next();
		assertEquals(produto, item.getProduto());
		assertEquals(5, item.getQuantidade());
		assertEquals(BigDecimal.valueOf(10), item.getValorUnitario());	}

	@Test
	public void should_updateItem_whenAdicionarItemWithExistingProduto() {
		carrinhoCompras.adicionarItem(produto, BigDecimal.valueOf(10), 5);
		carrinhoCompras.adicionarItem(produto, BigDecimal.valueOf(15), 5);

		assertEquals(1, carrinhoCompras.getItens().size());

		Item item = carrinhoCompras.getItens().iterator().next();
		assertEquals(produto, item.getProduto());
		assertEquals(10, item.getQuantidade());
		assertEquals(BigDecimal.valueOf(15), item.getValorUnitario());	}

	@Test
	public void shouldThrowException_whenAdicionarItemWithNullProduto() {
		assertThrows(IllegalArgumentException.class,
				() -> carrinhoCompras.adicionarItem(null, BigDecimal.valueOf(10), 5));
	}

	@Test
	public void shouldThrowException_whenAdicionarItemWithNullValorUnitario() {
		assertThrows(IllegalArgumentException.class,
				() -> carrinhoCompras.adicionarItem(produto, null, 5));
	}

	@Test
	public void shouldThrowException_whenAdicionarItemWithQuantidadeZero() {
		assertThrows(IllegalArgumentException.class,
				() -> carrinhoCompras.adicionarItem(produto, BigDecimal.valueOf(10), 0));
	}

	@Test
	public void should_removeItem_whenRemoverItemByProduto() {
		carrinhoCompras.adicionarItem(produto, BigDecimal.valueOf(10), 5);
		assertTrue(carrinhoCompras.removerItem(produto));
		assertEquals(0, carrinhoCompras.getItens().size());
	}

	@Test
	public void should_notRemoveItem_whenRemoverItemByProdutoInexistente() {
		assertFalse(carrinhoCompras.removerItem(produto));
	}
}