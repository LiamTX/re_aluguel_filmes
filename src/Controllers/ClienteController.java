package Controllers;

import java.sql.Connection;
import java.util.Collection;

import dao.ClienteDAO;
import dao.jdbc.ClienteDAOImpl;
import entidades.Cliente;

public class ClienteController {
	
	private ClienteDAO clienteDAO = new ClienteDAOImpl();
	
	public void insert(Connection conn, Cliente cliente) throws Exception {
	    clienteDAO.insert(conn, cliente);
	    
	    System.out.println("Cliente cadastrado!");
	}
	
	public void edit(Connection conn, Cliente cliente) throws Exception{
		clienteDAO.edit(conn, cliente);
		
		System.out.println("Clinte atualizado!");
	}
	
	public void delete(Connection conn, Integer idCliente) throws Exception {
		clienteDAO.delete(conn, idCliente);
		
		System.out.println("Cliente deletado!");
	}
	
	public void list(Connection conn) throws Exception {
		Collection<Cliente> clientes = clienteDAO.list(conn);
		
		System.out.println(clientes);
	}
	
	public void find(Connection conn, Integer idCliente) throws Exception {
		Cliente cliente  = clienteDAO.find(conn, idCliente);
		
		System.out.println(cliente);
	}
}
