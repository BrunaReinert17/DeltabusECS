package controle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.Endereco;
import modelo.Pedido;
import modelo.Veiculo;

public class PedidoDAO implements InterfacePedido {

	private Conexao con;

	public PedidoDAO() {
		con = Conexao.getInstacia();
	}

	public ArrayList<Pedido> listar() {
		Conexao c = Conexao.getInstacia();
		Connection con = c.conectar();

		ArrayList<Pedido> pedidos = new ArrayList<>();

		String query = "SELECT * FROM pedido";
		try {

			PreparedStatement ps = con.prepareStatement("select * from pedidos");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int pedido = rs.getInt("p");

				Pedido p = new Pedido();

				System.out.println("e");

				p.setIdpedido(rs.getInt("idpedido"));
				p.setVeiculo(rs.getInt("veiculo"));
				p.setCliente(rs.getString("cliente"));
				p.setDataCompra(rs.getDate("datacompra").toLocalDate());
				p.setValorPago(rs.getDouble("valorpago"));
				p.setTipoPagamento(rs.getString("cnpj"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		c.fecharConexao();

		return pedidos;
	}

	@Override
	public boolean inserirPedido(Pedido pedido) {
		Conexao c = Conexao.getInstacia();

		Connection con = c.conectar();

		String query = "INSERT INTO Pedido "
				+ "(id_pedidos, dataCompra, valorPago,tipoPagamento,veiculo_idVeiculo,Cliente_cnpj) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, pedido.getIdpedido());
			ps.setDate(2, java.sql.Date.valueOf(pedido.getDataCompra()));
			ps.setDouble(3, pedido.getValorPago());
			ps.setString(4, pedido.getTipoPagamento());
			ps.setInt(5, pedido.getVeiculo());
			ps.setString(6, pedido.getCliente());

			ps.executeUpdate();

			c.fecharConexao();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean excluirPedido(Pedido pedido) {
		Conexao c = Conexao.getInstacia();
		Connection con = c.conectar();

		String query = "DELETE FROM Cliente\r\n  WHERE Cpf = ?";

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDouble(1, pedido.getIdpedido());
			ps.executeUpdate();

			c.fecharConexao();
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean alterarPedido(Pedido pedido) {
		Conexao c = Conexao.getInstacia();
		Connection con = c.conectar();

		String query = "UPDATE Pedido\r\n   SET" + "nome = ?\r\n" + "dataNascimento = ?" + "genero = ?"
				+ " numerotelefone = ?" + "email = ?" + "Usuario_idUsuario = ?" + "endereco_cep = ? ,  WHERE cpf = ?";
		try {
			PreparedStatement p = con.prepareStatement(query);

			p.setInt(1, pedido.getIdpedido());
			p.setDouble(2, pedido.getValorPago());
			p.setDate(3, java.sql.Date.valueOf(pedido.getDataCompra()));
			p.setString(4, pedido.getTipoPagamento());
			p.setInt(5, pedido.getVeiculo());
			p.setString(6, pedido.getCliente());
			p.executeUpdate();

			c.fecharConexao();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.fecharConexao();
		}

		return false;
	}

}