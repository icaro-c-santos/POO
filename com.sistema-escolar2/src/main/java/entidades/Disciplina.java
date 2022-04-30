package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String nome;
	private String descricao;
	
	public Disciplina() {
		
	}
	public Disciplina(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getCodigo() {
		return codigo;
	}
	

	@Override
	public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Disciplina outraDisciplina = (Disciplina) obj;
        if(this.getCodigo() == outraDisciplina.getCodigo()) {
        	return true;
        }
        return false;
    }
	
	@Override
	public String toString() {
		
		return "Codigo: "+getCodigo()+"\nNome: "+getNome()+"\nDescrição: "+getDescricao()+"";
	}
	
	
	
}
