package br.com.fiap.bo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import br.com.fiap.dao.PalestranteDAO;
import br.com.fiap.dao.impl.PalestranteDAOImpl;
import br.com.fiap.entity.Palestrante;
import br.com.fiap.exception.CodigoInvalidoException;
import br.com.fiap.exception.CommitErrorException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class PalestranteBO {
	private EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();

	public void cadastrar(Palestrante palestrante) throws CommitErrorException {
		EntityManager em = factory.createEntityManager();
		PalestranteDAO dao = new PalestranteDAOImpl(em);
		try {
			dao.cadastrar(palestrante);
			dao.commit();
		} catch (Exception e) {
			throw new CommitErrorException(e);
		} finally {
			em.close();
		}
	}

	public void atualizar(Palestrante palestrante) throws CommitErrorException {
		EntityManager em = factory.createEntityManager();
		PalestranteDAO dao = new PalestranteDAOImpl(em);
		try {
			dao.atualizar(palestrante);
			dao.commit();
		} catch (Exception e) {
			throw new CommitErrorException(e);
		} finally {
			em.close();
		}
	}

	public List<Palestrante> listar() {
		EntityManager em = factory.createEntityManager();
		PalestranteDAO dao = new PalestranteDAOImpl(em);
		List<Palestrante> lista = dao.listar();
		em.close();
		return lista;
	}

	public Palestrante buscarPorCodigo(int codigo) {
		EntityManager em = factory.createEntityManager();
		PalestranteDAO dao = new PalestranteDAOImpl(em);
		Palestrante palestrante = dao.buscar(codigo);
		em.close();
		return palestrante;
	}
	
	public void remover(int codigo) throws CodigoInvalidoException,CommitErrorException{
		EntityManager em = factory.createEntityManager();
		PalestranteDAO dao = new PalestranteDAOImpl(em);
		try {
			dao.excluir(codigo);
			dao.commit();
		} catch (CommitErrorException e) {
			e.printStackTrace();
			new CommitErrorException(e);
		}catch (CodigoInvalidoException e) {
				e.printStackTrace();
				new CodigoInvalidoException(e);
		}finally {
			em.close();
		}
	}
}
