package br.com.fiap.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.repository.ProdutoRepository;
import br.com.fiap.to.ProdutoTO;

@ManagedBean
public class ProdutoBean {

	private ProdutoTO produto;
	
	private List<ProdutoTO> lista;

	private ProdutoRepository rep;
	
	@PostConstruct
	private void init(){
		rep = new ProdutoRepository();
		produto = new ProdutoTO();
		try {
			lista = rep.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String cadastrar(){
		FacesMessage msg;
		try {
			rep.cadastrar(produto);
			msg = new FacesMessage("Sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance()
			.getExternalContext().getFlash()
			.setKeepMessages(true);
		return "produto?faces-redirect=true";
	}
	
	public ProdutoTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoTO produto) {
		this.produto = produto;
	}

	public List<ProdutoTO> getLista() {
		return lista;
	}

	public void setLista(List<ProdutoTO> lista) {
		this.lista = lista;
	}

}
