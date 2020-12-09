package Controllers;

import java.sql.Connection;
import java.util.Collection;

import dao.AluguelDAO;
import dao.jdbc.AluguelDAOImpl;
import entidades.Aluguel;

public class AluguelController {
	
	private AluguelDAO aluguelDAO = new AluguelDAOImpl();
	
	public void insert(Connection conn, Aluguel aluguel) throws Exception {
		aluguelDAO.insert(conn, aluguel);
		
		System.out.println("Aluguel cadastrado!");
	}
	
	public void delete(Connection conn, Integer id_aluguel) throws Exception {
		aluguelDAO.delete(conn, id_aluguel);
		
		System.out.println("Aluguel deletado!");
	}
	
	public void find(Connection conn, Integer id_aluguel) throws Exception {
		Aluguel aluguel = aluguelDAO.find(conn, id_aluguel);
		
		System.out.println(aluguel);
	}
	
	public void list(Connection conn) throws Exception {
		Collection<Aluguel> alugueis = aluguelDAO.list(conn);
		
		System.out.println(alugueis);
	}
}
