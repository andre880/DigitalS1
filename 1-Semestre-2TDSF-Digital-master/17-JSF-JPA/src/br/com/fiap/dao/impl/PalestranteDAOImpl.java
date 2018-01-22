package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.PalestranteDAO;
import br.com.fiap.entity.Palestrante;

public class PalestranteDAOImpl extends GenericDAOImpl<Palestrante, Integer> implements PalestranteDAO{

	public PalestranteDAOImpl(EntityManager em) {
		super(em);
	}

}
