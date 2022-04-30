package entidades;

import java.util.Date;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Pessoa {
	
	private String nome;
	private Date nascimento;
	private String cpf;
	private String email;
	
	public Pessoa(){
	}
	
	public Pessoa(String nome, Date nascimento, String cpf, String email) {
		super();
		this.nome = nome;
		this.nascimento = nascimento;
		this.cpf = cpf;
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nome;
	}
	
}
