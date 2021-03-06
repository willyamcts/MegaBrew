package br.org.catolica.distribuidora.service;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import br.org.catolica.distribuidora.dao.PedidoDAO;
import br.org.catolica.distribuidora.exception.semEstoqueException;
import br.org.catolica.distribuidora.exception.semItensNoPedidoException;
import br.org.catolica.distribuidora.model.Pedido;
import br.org.catolica.distribuidora.model.Produto;
import br.org.catolica.distribuidora.model.Usuario;


@WebService
public class ProdutoService {
	private static final String CAT = "cat";
	
	public List<Produto> listarProdutos(){
		return PedidoDAO.ObterProdutos();
	}
	
	public List<Pedido> listarPedidos(){
		return PedidoDAO.ObterPedidos();
	}
	
	
	/*
	 * INSER��ES
	 */
	
	public void inserirProduto(@WebParam (name="cerveja") Produto produto, 
	@WebParam (name="usuario", header=true) Usuario usuario)  throws UsuarioNaoAutenticadoException {
		
		if(CAT.equals(usuario.getLogin()) && 
				CAT.equals(usuario.getPassword())) {
			PedidoDAO.inserirProduto(produto);	
		}else {
			throw new UsuarioNaoAutenticadoException();
		}	
		
	}
	

	public void criarPedido(@WebParam (name="pedido") Pedido pedido, 
	@WebParam (name="usuario", header=true) Usuario usuario)  throws UsuarioNaoAutenticadoException, semItensNoPedidoException, semEstoqueException {
		if(CAT.equals(usuario.getLogin()) && 
				CAT.equals(usuario.getPassword())) {
			
			//if( pedido.getItem().getQtd() >  )
			PedidoDAO.CriarPedido(pedido) ;
			
			
		}else {
			throw new UsuarioNaoAutenticadoException();
		}	
	}
	
	
	
	/*
	 * PESQUISAS
	 */

	public Produto pesquisarProdutoPorCodigo(@WebParam (name="codigoDoProduto", header=true) int cod) { 
		return PedidoDAO.pesquisaProdutoPorId(cod);
	}

	public Produto pesquisaProdutoPorDesc(@WebParam (name="descricaoDoProduto", header=true) String descricao) { 
		return PedidoDAO.pesquisaProdutoPorDesc(descricao);
	}
	
	
	
	
		
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8181/produtos", new ProdutoService());
		System.out.println("Servi�o iniciadoo! \n\t http://localhost:8181/produtos?wsdl");
		PedidoDAO.ObterProdutos();
	}
	
}