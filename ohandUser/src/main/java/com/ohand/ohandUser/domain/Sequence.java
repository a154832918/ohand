package com.ohand.ohandUser.domain;

import java.io.Serializable;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-10-28
 */
public class Sequence implements Serializable {
	private static final long serialVersionUID = 6198505874322130198L;
	private int id;
	private String name;
	private int nextId;

	public Sequence() {
	}

	public Sequence(String name, int nextId) {
		this.name = name;
		this.nextId = nextId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNextId() {
		return nextId;
	}

	public void setNextId(int nextId) {
		this.nextId = nextId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
