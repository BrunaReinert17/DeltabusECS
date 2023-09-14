package modelo;

import java.time.LocalDate;

public class Pedido {

	private int veiculo;
	private String cliente;
	private LocalDate dataCompra;
	private Double valorPago;
	private String tipoPagamento;
	private int idpedido;

	public Pedido(int veiculo, String cliente, LocalDate dataCompra, Double valorPago, String tipoPagamento, int idpedido) {

		super();
		this.idpedido = idpedido;
		this.veiculo = veiculo;
		this.cliente = cliente;
		this.dataCompra = dataCompra;
		this.valorPago = valorPago;
		this.tipoPagamento = tipoPagamento;
	}
	
	public Pedido() {
		super();
	}

	public int getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public int getVeiculo() {

		return veiculo;

	}

	public void setVeiculo(int veiculo) {

		this.veiculo = veiculo;

	}

	public String getCliente() {

		return cliente;

	}

	public void setCliente(String cliente) {

		this.cliente = cliente;

	}

	public LocalDate getDataCompra() {

		return dataCompra;

	}

	public void setDataCompra(LocalDate dataCompra) {

		this.dataCompra = dataCompra;

	}
	public Double getValorPago() {

		return valorPago;

	}
	public void setValorPago(Double valorPago) {

		this.valorPago = valorPago;

	}
	public String getTipoPagamento() {

		return tipoPagamento;

	}
	public void setTipoPagamento(String tipoPagamento) {

		this.tipoPagamento = tipoPagamento;
	}

	
}