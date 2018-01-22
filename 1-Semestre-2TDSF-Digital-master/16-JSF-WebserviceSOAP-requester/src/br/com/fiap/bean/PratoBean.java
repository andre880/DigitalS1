package br.com.fiap.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.axis2.AxisFault;

import br.com.fiap.bo.PratoBOStub;
import br.com.fiap.bo.PratoBOStub.Buscar;
import br.com.fiap.bo.PratoBOStub.BuscarResponse;
import br.com.fiap.bo.PratoBOStub.Cadastrar;
import br.com.fiap.bo.PratoBOStub.Prato;

@ManagedBean
public class PratoBean {

	private Prato prato;
	private int codigo;
	
	@PostConstruct
	private void init(){
		prato = new Prato();
	}
	
	public void buscar(){
		try {
			PratoBOStub stub = new PratoBOStub();
			Buscar params = new Buscar();
			params.setId(codigo);
			BuscarResponse response = stub.buscar(params);
			prato = response.get_return();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar(){
		FacesMessage msg;
		try {
			PratoBOStub stub = new PratoBOStub();
			Cadastrar params = new Cadastrar();
			params.setPrato(prato);
			stub.cadastrar(params);
			msg = new FacesMessage("Cadastrado!");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Prato getPrato() {
		return prato;
	}

	public void setPrato(Prato prato) {
		this.prato = prato;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
