package com.belief.Impl;

import java.util.ArrayList;

import com.belief.dao.addressDao;
import com.belief.model.Address;

//业务层(DAO层)的实现类，继承了业务层公共父类，并实现了业务层抽象接口
public class addressDaoImpl extends ImplFather implements addressDao {

	@Override
	public void save(Address address) {
		session.save(address);
		cleanUp();
	}

	@Override
	public void delete(Address address) {
		session.delete(address);
		cleanUp();
	}

	@Override
	public void update(Address address) {
		session.update(address);
		cleanUp();
	}

	@Override
	public Address QueryByOne(String mail_address, String friend_address) {
		String hql = "FROM Address WHERE Address.mail_address=? AND Address.friend_address=?";
		Address address = (Address) session.createQuery(hql).setParameter(0, mail_address)
				.setParameter(1, friend_address).uniqueResult();
		cleanUp();
		return address;
	}

	@Override
	public ArrayList<Address> QueryByMail(String mail_address) {
		String hql = "FROM Address WHERE Address.mail_address=?";
		@SuppressWarnings("unchecked")
		ArrayList<Address> addresss = (ArrayList<Address>) session.createQuery(hql).setParameter(0, mail_address)
				.getResultList();
		cleanUp();
		return addresss;
	}

}
