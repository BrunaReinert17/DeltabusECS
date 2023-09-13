package controle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.Endereco;
import modelo.Pedido;

public class ClienteDAO implements InterfaceCliente {

	  private Conexao con;

	    public ClienteDAO() {
	        con = Conexao.getInstacia();
	    }
	    
	    public ArrayList<Cliente> listar() {
			Conexao c = Conexao.getInstacia();
			Connection con = c.conectar();

			ArrayList<Cliente> clientes = new ArrayList<>();

			String query = "SELECT * FROM clientes";
			try {
				
				PreparedStatement ps = con.prepareStatement(query);

				
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					 String nome = rs.getString("nome");

					

					Cliente cliente = new Cliente();
					Endereco endereco = new Endereco();
					
					cliente.setNome(rs.getString("nome"));
					cliente.setNumeroTelefone(rs.getInt("numeroTelefone"));
					cliente.setEmail(rs.getString("email"));
					cliente.setCpf(rs.getDouble("cpf"));
					cliente.setCnpj(rs.getLong("cnpj"));
					endereco.setCep(rs.getInt("endereco_cep"));
					cliente.setEndereco(endereco);
					

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			c.fecharConexao();

			return clientes;
		}
	    
	
	@Override
	public boolean inserirCliente(Cliente cliente) {
		 Connection c = con.conectar();
	        int valida = 0;

	        try {
	            String query = "INSERT INTO Clientes(Nome, numeroTelefone, email, cnpj, endereco_cep) VALUES (?, ?, ?, ?)";
	            PreparedStatement stm = c.prepareStatement(query);
	            stm.setString(1, cliente.getNome());
	            stm.setInt(2, cliente.getNumeroTelefone());
	            stm.setString(3, cliente.getEmail());
	            stm.setDouble(4,cliente.getCpf());
	            stm.setLong(5, cliente.getCnpj());
	            //stm.setInt(7, cliente.getEndereco().getCep());
	        	
	            valida = stm.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            con.fecharConexao();
	        }
	        return valida != 0;
	    }
	
	

	@Override
	public boolean excluirCliente(Cliente cliente) {
		
		Conexao c = Conexao.getInstacia();
		Connection con = c.conectar();
		
String query = "DELETE FROM Cliente\r\n  WHERE Cpf = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDouble(1, cliente.getCpf());
			ps.executeUpdate();
			
			c.fecharConexao();
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}


	public Cliente atualizarCliente(Cliente cliente) {
		
		Conexao c = Conexao.getInstacia();
		Connection con = c.conectar();
		
		
		String query = "UPDATE Cliente\r\n   SET" 
				+ "nome = ?\r\n" + "numeroTelefone = ?" + "email = ?" + "cnpj = ?" + " endereco = ?,  WHERE cpf = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, cliente.getNome());
			ps.setLong(2, cliente.getNumeroTelefone());
			ps.setString(3, cliente.getEmail());
			ps.setLong(3, cliente.getCnpj());
			// ps.setendereco(4, cliente.getEndereco());
			
			
			ps.executeUpdate();
			
			c.fecharConexao();
			// return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			c.fecharConexao();
		}
		
		return null;
	}
	
	

}
