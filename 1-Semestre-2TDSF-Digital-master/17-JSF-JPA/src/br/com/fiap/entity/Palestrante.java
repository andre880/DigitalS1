package br.com.fiap.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_PALESTRANTE")
@SequenceGenerator(name="seqPalestrante", sequenceName="SQ_T_PALESTRANTE", allocationSize=1)
public class Palestrante {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seqPalestrante")
	@Column(name="CD_PALESTRANTE")
	private int codigo;
	
	@Column(name="NM_PALESTRANTE", nullable=false)
	private String nome;
	
	@Column(name="VL_HORA", nullable=false)
	private double valorHora;
	
	@Temporal(TemporalType.DATE)
	@Column(name="VDT_NASCIMENTO")
	private Calendar dataNasc;

	public Calendar getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Calendar dataNasc) {
		this.dataNasc = dataNasc;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorHora() {
		return valorHora;
	}

	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}
	
}
