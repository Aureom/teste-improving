package br.com.improving.carrinho;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Kaio Souza
 * @since 2023/11/19
 */

class ProdutoTest {

	@Test
	void equals_shouldReturnFalse_whenComparingWithDifferentClass() {
		Produto produto = new Produto(123L, "Teste Produto");

		assertNotEquals(new Object(), produto);
	}

	@Test
	void equals_shouldReturnTrue_whenComparingProdcutsWithSameCodigo() {
		Produto produto1 = new Produto(123L, "Teste Produto 1");
		Produto produto2 = new Produto(123L, "Teste Produto 2");

		assertEquals(produto1, produto2);
	}

	@Test
	void equals_shouldReturnFalse_whenComparingProdcutsWithDifferentCodigo() {
		Produto produto1 = new Produto(123L, "Teste Produto");
		Produto produto2 = new Produto(456L, "Teste Produto");

		assertNotEquals(produto1, produto2);
	}
}
