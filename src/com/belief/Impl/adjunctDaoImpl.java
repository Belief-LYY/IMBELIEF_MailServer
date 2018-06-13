package com.belief.Impl;

import java.util.ArrayList;

import com.belief.dao.adjunctDao;
import com.belief.model.Adjunct;

//业务层(DAO层)的实现类，继承了业务层公共父类，并实现了业务层抽象接口
public class adjunctDaoImpl extends ImplFather implements adjunctDao {

	@Override
	public void save(Adjunct adjunct) {
		session.save(adjunct);
		cleanUp();
	}

	@Override
	public void delete(Adjunct adjunct) {
		session.delete(adjunct);
		cleanUp();
	}

	@Override
	public void update(Adjunct adjunct) {
		session.update(adjunct);
		cleanUp();
	}

	@Override
	public Adjunct QueryById(String adjunct_id) {
		Adjunct adjunct = (Adjunct) session.get(Adjunct.class, adjunct_id);
		cleanUp();
		return adjunct;
	}

	@Override
	public ArrayList<Adjunct> QueryByMailId(String mail_id) {
		String hql = "FROM Adjunct WHERE Adjunct.mail_id=?";
		@SuppressWarnings("unchecked")
		ArrayList<Adjunct> adjuncts = (ArrayList<Adjunct>) session.createQuery(hql).setParameter("Adjunct.mail_id", mail_id)
				.getResultList();
		cleanUp();
		return adjuncts;
	}

}
