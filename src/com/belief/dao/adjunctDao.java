package com.belief.dao;

import java.util.ArrayList;

import com.belief.model.Adjunct;

//业务层抽象接口
public interface adjunctDao {
	void delete(Adjunct adjunct);

	Adjunct QueryById(String adjunct_id);

	ArrayList<Adjunct> QueryByMailId(String mail_id);

	void save(Adjunct adjunct);

	void update(Adjunct adjunct);
}
