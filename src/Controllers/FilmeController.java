package Controllers;

import java.sql.Connection;
import java.util.Collection;

import dao.FilmeDAO;
import dao.jdbc.FilmeDAOImpl;
import entidades.Filme;

public class FilmeController {

	private FilmeDAO filmeDAO = new FilmeDAOImpl();
	
	public void insert(Connection conn, Filme filme) throws Exception {
		filmeDAO.insert(conn, filme);
		
		System.out.println("Filme cadastrado!");
	}
	
	public void edit(Connection conn, Filme filme) throws Exception {
		filmeDAO.insert(conn, filme);
		
		System.out.println("Filme editato!");
	}
	
	public void delete(Connection conn, Integer idFilme) throws Exception {
		filmeDAO.delete(conn, idFilme);
		
		System.out.println("Filme deletado!");
	}
	
	public void list(Connection conn) throws Exception {
		Collection<Filme> filmes = filmeDAO.list(conn);
		
		System.out.println(filmes);
	}
	
	public void find(Connection conn, Integer idFilme) throws Exception {
		Filme filme = filmeDAO.find(conn, idFilme);
		
		System.out.println(filme);
	}
}
