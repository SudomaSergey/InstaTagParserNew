package com.kademika.domain;

import java.io.Serializable;

public class Tag implements Serializable, Comparable<Tag> {

	private String name;

	private int count;

	public Tag() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void updateCount() {
		count++;
	}

	@Override
	public int compareTo(Tag o) {
		return new Integer(count).compareTo(o.getCount());
	}

	@Override
	public boolean equals(Object obj) {
		Tag tag = (Tag) obj;
		if (this.getName().equals(tag.getName())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "#" + this.name + " - " + this.count + "\n";
	}
}
