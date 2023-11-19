package br.com.improving.carrinho;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Kaio Souza
 * @since 2023/11/19
 */
class ItemTest {

	private Item item;

	@BeforeEach
	public void setUp() {
		Produto produto = new Produto(123L, "Teste Produto");
		item = new Item(produto, BigDecimal.valueOf(10.0), 2);
	}

	@Test
	public void assert_shouldReturnCorrectTotalValue_whenCalculatingTotalValue() {
		assertEquals(BigDecimal.valueOf(20.0), item.getValorTotal());
	}
}