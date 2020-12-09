package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Liam
 *
 */
public class Filme {

    private Integer idFilme;
    private Date dataLancamento;
    private String nome;
    private String descricao;

    public Filme() {
    }
    
    public Filme(Integer idFilme, String nome) {
    	this.idFilme = idFilme;
    	this.nome = nome;
    }

    public Filme(Integer idFilme, Date dataLancamento, String nome, String descricao) {
        this.idFilme = idFilme;
        this.dataLancamento = dataLancamento;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getIdFilme() {
        return idFilme;
    }

    public Filme setIdFilme(Integer idFilme) {
        this.idFilme = idFilme;
        return this;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public Filme setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Filme setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Filme setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

	@Override
	public String toString() {
		return "Filme [idFilme=" + idFilme + ", dataLancamento=" + dataLancamento + ", nome=" + nome + ", descricao="
				+ descricao + "] \n";
	}
    
	public java.util.Date formatDate(String data) throws Exception{
    	SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date new_date = dataFormat.parse(data);
    	
    	return new_date;
    }
    
}
