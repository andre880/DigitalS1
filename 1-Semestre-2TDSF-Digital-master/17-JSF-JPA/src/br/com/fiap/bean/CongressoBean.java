package br.com.fiap.bean;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.bo.CongressoBO;
import br.com.fiap.entity.Congresso;

@ManagedBean
public class CongressoBean {

	private Congresso congresso;
	private CongressoBO bo;
	private int codigo;
	
	@PostConstruct
	public void init(){
		congresso = new Congresso();
		congresso.setData(Calendar.getInstance()); // Inicializa a data , necessário !
		bo = new CongressoBO();
	}
	
	public String cadastrar(){
		FacesMessage msg = null;
		try {
			if(congresso.getCodigo() == 0){
			bo.cadastrar(congresso);
			msg = new FacesMessage("Cadastrado com sucesso !");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			}else{
				bo.atualizar(congresso);
				msg = new FacesMessage("Congresso " + congresso.getTema() + " atualizado");
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			return "cadastro-congresso";
		}	
		FacesContext.getCurrentInstance().addMessage(null, msg);
		//Manter a mensagem após um redirect
		FacesContext.getCurrentInstance().getExternalContext()
		.getFlash().setKeepMessages(true);
		//?faces-redirect=true para fazer uma nova requisição e não cadastrar novamente
		//mas isso impede que a mensagem seja mostrada, pois a mensagem está na requisição anterior
		return "lista-congresso?faces-redirect=true";
	}
	
	public void buscar(){
		try {
			congresso.setCodigo(codigo);
			bo.buscarPorCodigo(codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(){
		//bo.buscarPorCodigo(codigo);
		
	}
	
	public void excluir(){
		FacesMessage msg;
		try {
			bo.remover(codigo);
			msg = new FacesMessage("Removido !");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro ao remover!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		
	}
	
	public void listar(){
		bo.listar();
	}
	

	public Congresso getCongresso() {
		return congresso;
	}

	public void setCongresso(Congresso congresso) {
		this.congresso = congresso;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}
