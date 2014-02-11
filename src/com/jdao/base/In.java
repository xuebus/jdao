package com.jdao.base;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-2-2
 * @verion 1.0.1 入参
 */
public class In extends com.jdao.base.Params {
	private Object o;

	public In(Object o) {
		this.o = o;
	}

	public Object getValue() {
		return o;
	}

	@Override
	public int getTypes() {
		return 0;
	}
}
