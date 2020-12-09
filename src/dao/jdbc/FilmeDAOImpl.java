package dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import dao.FilmeDAO;
import entidades.Filme;

public class FilmeDAOImpl implements FilmeDAO {

	@Override
	public void insert(Connection conn, Filme filme) throws Exception {
		
		PreparedStatement myStmt = conn.prepareStatement("insert into en_filme (id_filme, data_lancamento, nome, descricao) values (?, ?, ?, ?)");
		
		Integer idFilme = this.getNextId(conn);
		
		Date data_lancamento = new Date(filme.getDataLancamento().getTime());
		
		myStmt.setInt(1, idFilme);
		myStmt.setDate(2, data_lancamento);
		myStmt.setString(3, filme.getNome());
		myStmt.setString(4, filme.getDescricao());
		
		myStmt.execute();
		conn.commit();
		
		filme.setIdFilme(idFilme);
		
	}

	@Override
	public Integer getNextId(Connection conn) throws Exception {
		
		PreparedStatement myStmt = conn.prepareStatement("select nextval('seq_en_filme')");
        ResultSet rs = myStmt.executeQuery();
        rs.next();
        return rs.getInt(1);
        
	}

	@Override
	public void edit(Connection conn, Filme filme) throws Exception {
		
		PreparedStatement myStmt = conn.prepareStatement("update en_filme set data_lancamento = (?), nome = (?), descricao = (?) where id_filme = (?)");
		
		Date data_lancamento = new Date(filme.getDataLancamento().getTime());
		
		myStmt.setDate(1, data_lancamento);
		myStmt.setString(2, filme.getNome());
		myStmt.setString(3, filme.getDescricao());
		myStmt.setInt(4, filme.getIdFilme());
		
		myStmt.execute();
		conn.commit();
		
	}

	@Override
	public void delete(Connection conn, Integer idFilme) throws Exception {
		//Seleciona o id do aluguel e caso o filme esteja alugado armazena em um array
		PreparedStatement myStmt = conn.prepareStatement("select id_aluguel from re_aluguel_filme where id_filme = ?");
		
		myStmt.setInt(1, idFilme);
		
		ResultSet rs = myStmt.executeQuery();
		
		ArrayList<Integer> arr_id_aluguel = new ArrayList<>();
		
		if(!rs.next()) {
			System.out.println("Filme não alugado!");
		}else {
			do {
				arr_id_aluguel.add(rs.getInt("id_aluguel"));
			}while(rs.next());
			
			//Verifica se o existe mais de um filme no alguel
			for(int i = 0; i < arr_id_aluguel.size(); i++) {
				myStmt = conn.prepareStatement("select id_filme from re_aluguel_filme where id_aluguel = ?");
				
				myStmt.setInt(1, (int) arr_id_aluguel.get(i));
				
				myStmt.execute();
				
				rs = myStmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getInt("id_filme") != idFilme) {
						arr_id_aluguel.remove(i);
					}
				}
			}
			
			//Deleta o filme e o aluguel da tabela de relacionamento
			myStmt = conn.prepareStatement("delete from re_aluguel_filme where id_filme = ?");
			
			myStmt.setInt(1, idFilme);
			
			myStmt.execute();
			conn.commit();
			
			//Deleta da tabela de alugueis
			for(int i = 0; i < arr_id_aluguel.size(); i++) {
				myStmt = conn.prepareStatement("delete from en_aluguel where id_aluguel = ?");
				
				myStmt.setInt(1, arr_id_aluguel.get(i));
				
				myStmt.execute();
				conn.commit();
			}
			
		}
		
		//Deleta da tabela de filmes
		myStmt = conn.prepareStatement("delete from en_filme where id_filme = ?");
		
		myStmt.setInt(1, idFilme);
		
		myStmt.execute();
		conn.commit();
		
	}

	@Override
	public Filme find(Connection conn, Integer idFilme) throws Exception {
		PreparedStatement myStmt = conn.prepareStatement("select * from en_filme where id_filme = ?");

        myStmt.setInt(1, idFilme);

        ResultSet myRs = myStmt.executeQuery();

        if (!myRs.next()) {
            return null;
        }

        String nome = myRs.getString("nome");
        String descricao = myRs.getString("descricao");
        java.sql.Date data_lancamento = myRs.getDate("data_lancamento");
        
        return new Filme(idFilme, data_lancamento, nome, descricao);
	}

	@Override
	public Collection<Filme> list(Connection conn) throws Exception {
		
		PreparedStatement myStmt = conn.prepareStatement("select * from en_filme order by nome");
        ResultSet myRs = myStmt.executeQuery();

        Collection<Filme> items = new ArrayList<>();

        while (myRs.next()) {
            Integer idFilme = myRs.getInt("id_filme");
            String nome = myRs.getString("nome");
            java.sql.Date data_lancamento = myRs.getDate("data_lancamento");
            String descricao = myRs.getString("descricao");

            items.add(new Filme(idFilme, data_lancamento, nome, descricao));
        }

        return items;
        
	}
	
}
