package br.com.improving.carrinho;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Kaio Souza
 * @since 2023/11/19
 */
class CarrinhoComprasFactoryTest {
	private CarrinhoComprasFactory factory;

	@BeforeEach
	void setUp() {
		factory = new CarrinhoComprasFactory();
	}

	@Test
	void shouldCreateNewCarrinho_whenCriarWithNewCliente() {
		CarrinhoCompras carrinho = factory.criar("cliente1");
		assertNotNull(carrinho);
	}

	@Test
	void shouldReturnSameCarrinho_whenCriarWithSameCliente() {
		CarrinhoCompras primeiroCarrinho = factory.criar("cliente1");
		CarrinhoCompras segundoCarrinho = factory.criar("cliente1");
		assertSame(primeiroCarrinho, segundoCarrinho);
	}

	@Test
	void shouldReturnZero_whenGetValorTicketMedioComCarrinhosVazios() {
		factory.criar("cliente1");
		factory.criar("cliente2");
		assertEquals(BigDecimal.ZERO.setScale(2), factory.getValorTicketMedio(), "O ticket médio deve ser zero para carrinhos vazios");
	}

	@Test
	void shouldReturnValorTicketMedio_whenGetValorTicketMedioComCarrinhosComProdutos() {
		String clienteID = "Client1";
		CarrinhoCompras carrinhoCompras = factory.criar(clienteID);

		carrinhoCompras.adicionarItem(new Produto(123L, "Produto teste"), BigDecimal.valueOf(50), 2);

		assertEquals(BigDecimal.valueOf(100).setScale(2), factory.getValorTicketMedio());
	}

	@Test
	void shouldReturnTrue_whenInvalidar() {
		String clienteID = "Client1";

		assertFalse(factory.invalidar(clienteID));

		factory.criar(clienteID);
		assertTrue(factory.invalidar(clienteID));
	}
}