
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Controllers.AluguelController;
import Controllers.ClienteController;
import Controllers.FilmeController;
import entidades.Aluguel;
import entidades.Cliente;
import entidades.Filme;


public class Main {
	
	public static java.util.Date formatData(String data) throws Exception{
    	SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date new_date = dataFormat.parse(data);
    	
    	return new_date;
    }

	public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://ec2-35-172-246-19.compute-1.amazonaws.com/dsnl78pvu9752?sslmode=require", "lbyxotgxljouwk", "93c64e2d249cf42c56b8d7e4193806cde2d3615a6ffa085426388d841faf16ce");
            conn.setAutoCommit(false);

            //Demonstrar o funcionamento aqui
            //Descomente o trecho de função que você deseja testar
            
            				//     CLIENTE
            ClienteController clienteController = new ClienteController();
            
            				// ----> INSERT <---- //
//            String nome = "Liam Cabral";//<--Insira o nome do cliente
//            Cliente cliente = new Cliente(0, nome);
//            clienteController.insert(conn, cliente);
            
            				// ----> EDIT <---- //
//            Integer idCliente = 85;//<--Insira o id do cliente que deseja alterar
//            String novo_nome = "Liam Cabral Teixeira";//<--Insira o novo nome
//            Cliente cliente = new Cliente(idCliente, novo_nome);
//            clienteController.edit(conn, cliente);
            
            				// ----> DELETE <---- //
//            Integer idCliente = 85;//<--Insira o id do cliente que deseja excluir
//            clienteController.delete(conn, idCliente);

            				// ----> LIST <----- //
//            clienteController.list(conn);
            
            				// ----> FIND <---- //
//            Integer idCliente = 85;//<--Insira o id do cliente que deseja achar
//            clienteController.find(conn, idCliente);

            // --------------------------------------------- //
            
            					//   FILME  //
            FilmeController filmeController = new FilmeController();
            
            					// ----> INSERT <---- // 
//            String data_lancamento = "2021-01-01";//<--Insira a data de lancamento do filme
//            String nome = "Filme do liam";//<--Insira o nome do filme
//            String descricao = "Melhor filme do ano";//<--Insira a descricao do filme
//            
//            Filme filme = new Filme(0, formatData(data_lancamento), nome, descricao);
//            filmeController.insert(conn, filme);
            
            					// ----> EDIT <---- //
//            Integer idFilme = 85;//<--Insira o id do filme que deseja alterar
//            String data_lancamento = "2021-01-04";//<--Insira a nova data de lancamento
//            String nome = "Filme do Liamzao";//<-- Insira o novo nome
//            String descricao = "Outra descricao";//<-- Insira a nova descricao
//            
//            Filme filme = new Filme(idFilme, formatData(data_lancamento), nome, descricao);
//            filmeController.edit(conn, filme);
            
            				// ----> DELETE <---- //
//            Integer idFilme = 85;//<--Insira o id do filme que deseja excluir
//            filmeController.delete(conn, idFilme);
            
            	// ----> LIST <---- //
//            filmeController.list(conn);
            
            			// ----> FIND <---- //
//            Integer idFilme = 40;//<--Insira o id do filme que deseja achar
//            filmeController.find(conn, idFilme);
            
            // ---------------------------------------------- //
            
            				// ALUGUEL //
            AluguelController aluguelController = new AluguelController();
       
           			// ----> INSERT <---- //           
            //Insira os filmes que serão alugados
//            List<Filme> filmes = new ArrayList<>();
//            Filme filme1 = new Filme(40, "Filme do liam");
//            filmes.add(filme1);
//            String nome_cliente = "Liam Cabral";//<---Insira o nome do cliente
//            Cliente cliente = new Cliente(51, nome_cliente);          
//            String data_aluguel = "2022-01-01";//<--Insira a data do aluguel
//            Float valor = 2F;//<--Insira o valor do aluguel
//            
//            Aluguel aluguel = new Aluguel(0, filmes, cliente, formatData(data_aluguel), valor);
//            
//            aluguelController.insert(conn, aluguel);
            
            			// ----> DELETE <---- //
//            Integer idAluguel = 90;//<--Insira o id do aluguel
//            aluguelController.delete(conn, idAluguel);

//            // ----> FIND <---- //    
//            Integer idAluguel = 81;
//            aluguelController.find(conn, idAluguel);
            
//            // ----> LIST <---- //
//            aluguelController.list(conn);
            
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fim do teste.");
    }
}