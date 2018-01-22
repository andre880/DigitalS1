package br.com.fiap.bean;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.bo.PalestranteBO;
import br.com.fiap.entity.Palestrante;
import br.com.fiap.exception.CommitErrorException;

@ManagedBean
public class PalestranteBean {

	private int codigo;
	private Palestrante p;
	private PalestranteBO bo;
	
	@PostConstruct
	public void init(){
		p = new Palestrante();
		bo = new PalestranteBO();
		p.setDataNasc(Calendar.getInstance());
	}
	public String cadastrar(){
		FacesMessage msg = null;
		try {
			bo.cadastrar(p);
			msg = new FacesMessage("Cadastrado com sucesso");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (CommitErrorException e) {
			msg = new FacesMessage("Erro");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext()
		.getFlash().setKeepMessages(true);
		return "lista-palestrante?faces-redirect=true";
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Palestrante getP() {
		return p;
	}
	public void setP(Palestrante p) {
		this.p = p;
	}
	
}
