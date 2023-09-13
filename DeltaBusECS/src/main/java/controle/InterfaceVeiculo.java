package controle;

import modelo.Pedido;
import modelo.Veiculo;

public interface InterfaceVeiculo {
     
	public boolean inserirVeiculo(Veiculo veiculo);

	public boolean excluirVeiculo(Veiculo veiculo);

	public boolean atualizarVeiculo(Veiculo veiculo);
	
	
}
