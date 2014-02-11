package com.jdao.action;

import org.junit.Test;
import com.jdao.base.DaoFactory;
import com.jdao.base.FieldFilter;
import com.jdao.base.Fields;
import com.jdao.dao.Hstest;
import com.jdao.dbHandler.JdaoHandler;
import com.jdao.dbHandlerImpl.JdaoHandlerFactory;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-4-26
 * @verion 1.0.5 1_0_5 版本的新功能测试
 */
public class ActionTest1_0_5 {
	static JdaoHandler jdao = JdaoHandlerFactory.getDBHandler4c3p0SingleTon();
	static {
		// 给每个dao(包括QueryDao查询类)注册不同的过滤器操作对象。
		DaoFactory.dataSourceRegister(Hstest.class, jdao);
		DaoFactory.setFieldFilter(new FieldFilter() {
			@Override
			public Object process(Fields field, String name, Object value) {
				if (field.equals(Hstest.VALUE)) {
					return null;
				} else {
					return value;
				}
			}
		});
	}

	@Test
	static void fieldFilterTest() throws Exception {
		Hstest h = new Hstest();
		h.setFieldFilter(new FieldFilter() {
			@Override
			public Object process(Fields field, String name, Object value) {
				if (field.equals(Hstest.ROWNAME)) {
					return null;
				} else {
					return value;
				}
			}
		});
		h.setRowname("wuxiaodong100");
		h.setValue("wuxiaodong100");
		int id = h.saveAndGetLastInsertId4MYSQL();//获取插入ID
		h.clear(); // 清除缓存，作用相当于重新new对象。
		h.where(Hstest.ID.EQ(id));
		h = h.queryById();
		System.out.println(h.getId() + " " + h.getRowname() + " " + h.getValue());
	}

	public static void main(String[] args) throws Exception {
		ActionTest1_0_5.fieldFilterTest();
	}

}
