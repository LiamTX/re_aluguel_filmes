package dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import dao.AluguelDAO;
import dao.ClienteDAO;
import dao.FilmeDAO;
import entidades.Aluguel;
import entidades.Cliente;
import entidades.Filme;

public class AluguelDAOImpl implements AluguelDAO{

	@Override
	public void insert(Connection conn, Aluguel aluguel) throws Exception {
		
		//Insere o aluguel na sua devida tabela
		PreparedStatement myStmt = conn.prepareStatement("insert into en_aluguel (id_aluguel, id_cliente, data_aluguel, valor) values (?, ?, ?, ?)");
		
		Integer idAluguel = this.getNextId(conn);
		
		Date data_aluguel = new Date(aluguel.getDataAluguel().getTime());
		
		myStmt.setInt(1, idAluguel);
		myStmt.setInt(2, aluguel.getCliente().getIdCliente());
		myStmt.setDate(3, data_aluguel);
		myStmt.setFloat(4, aluguel.getValor());
		
		myStmt.execute();
		conn.commit();
		
		aluguel.setIdAluguel(idAluguel);
		
		//faz o relacionamento
		for(int i = 0; i < aluguel.getFilmes().size(); i++) {
			myStmt = conn.prepareStatement("insert into re_aluguel_filme (id_filme, id_aluguel) values (?, ?)");
			
			myStmt.setInt(1, aluguel.getFilmes().get(i).getIdFilme());
			myStmt.setInt(2, aluguel.getIdAluguel());
			
			myStmt.execute();
			conn.commit();
		}
		
	}

	@Override
	public Integer getNextId(Connection conn) throws Exception {
		PreparedStatement myStmt = conn.prepareStatement("select nextval('seq_en_aluguel')");
        ResultSet rs = myStmt.executeQuery();
        rs.next();
        return rs.getInt(1);
	}

	@Override
	public void edit(Connection conn, Aluguel aluguel) throws Exception {
		
		
	}

	@Override
	public void delete(Connection conn, Integer id_aluguel) throws Exception {
		
		//Deleta do relacionamento
		PreparedStatement myStmt = conn.prepareStatement("delete from re_aluguel_filme where id_aluguel = ?");
		
		myStmt.setInt(1, id_aluguel);
		
		myStmt.execute();
		conn.commit();
		
		//Deleta de sua devida tabela
		myStmt = conn.prepareStatement("delete from en_aluguel where id_aluguel = ?");
		
		myStmt.setInt(1, id_aluguel);
		
		myStmt.execute();
		conn.commit();
		
	}

	@Override
	public Aluguel find(Connection conn, Integer idAluguel) throws Exception {
		//Faz um inner join com o re_aluguel_filme
		PreparedStatement myStmt = conn.prepareStatement("select * from en_aluguel inner join re_aluguel_filme on en_aluguel.id_aluguel = re_aluguel_filme.id_aluguel where en_aluguel.id_aluguel = ?");
		
		myStmt.setInt(1, idAluguel);
		
		ResultSet myRs = myStmt.executeQuery();
		
		if(!myRs.next()) {
			return null;
		}
		
		ArrayList<Filme> filmes = new ArrayList<>();	
		java.sql.Date data_aluguel = myRs.getDate("data_aluguel");
		Float valor = myRs.getFloat("valor");
		
		//Procura o cliente a partir do id
		ClienteDAO clienteDAO = new ClienteDAOImpl();
		Cliente cliente = clienteDAO.find(conn, myRs.getInt("id_cliente"));
		
		//Procura os filmes a partir dos ids
		ArrayList<Integer> arr_id_filmes = new ArrayList<>();
		do {
			arr_id_filmes.add(myRs.getInt("id_filme"));
		}while(myRs.next());
		
		
		for(int i = 0; i < arr_id_filmes.size(); i++) {
			FilmeDAO filmeDAO = new FilmeDAOImpl();
			
			Filme filme = filmeDAO.find(conn, arr_id_filmes.get(i));
			
			filmes.add(filme);
		}
		
		return new Aluguel(idAluguel, filmes, cliente, data_aluguel, valor);
	}

	@Override
	public Collection<Aluguel> list(Connection conn) throws Exception {
		PreparedStatement myStmt = conn.prepareStatement("select * from en_aluguel inner join re_aluguel_filme on en_aluguel.id_aluguel = re_aluguel_filme.id_aluguel");
		ResultSet myRs = myStmt.executeQuery();
		
		if(!myRs.next()) {
			return null;
		}
		
		Collection<Aluguel> list = new ArrayList<>();
		
		while(myRs.next()) {
			Integer idAluguel = myRs.getInt("id_aluguel");		
			ArrayList<Filme> filmes = new ArrayList<>();
			ClienteDAO clienteDAO = new ClienteDAOImpl();
			Cliente cliente = clienteDAO.find(conn, myRs.getInt("id_cliente"));
			java.sql.Date data_aluguel = myRs.getDate("data_aluguel");
			Float valor = myRs.getFloat("valor");
			
			list.add(new Aluguel(idAluguel, filmes, cliente, data_aluguel, valor));
		}
		
		return list;
	}

}
