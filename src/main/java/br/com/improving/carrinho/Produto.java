package br.com.improving.carrinho;

import java.util.Objects;

/**
 * Classe que representa um produto que pode ser adicionado
 * como item ao carrinho de compras.
 * <p>
 * Importante: Dois produtos são considerados iguais quando ambos possuem o
 * mesmo código.
 */
public class Produto {

    private final Long codigo;
    private final String descricao;

    /**
     * Construtor da classe Produto.
     *
	 * @param codigo     O código do produto.
	 * @param descricao  A descrição do produto.
     */
    public Produto(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
    }

    /**
     * Retorna o código da produto.
     *
	 * @return O código do produto.
     */
    public Long getCodigo() {
		return this.codigo;
    }

    /**
     * Retorna a descrição do produto.
     *
     * @return String
     */
    public String getDescricao() {
		return this.descricao;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Produto produto = (Produto) obj;
		return codigo != null && codigo.equals(produto.codigo);
	}

	@Override
	public int hashCode() {
		return codigo != null ? codigo.hashCode() : 0;
	}
}