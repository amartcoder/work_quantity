package com.qfnu.bean;

public class TQuantity {
	public String name;
	public String idNumber;
	public double quantity;
	public String dept;
	public String term;
	public String note;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "TQuantity [name=" + name + ", idNumber=" + idNumber + ", quantity=" + quantity + ", dept=" + dept
				+ ", term=" + term + ", note=" + note + "]";
	}
}
