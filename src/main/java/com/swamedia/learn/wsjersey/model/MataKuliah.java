package com.swamedia.learn.wsjersey.model;

public class MataKuliah {
	private int mkID;
	private String kodeMK;
	private String nama;
	private String sks;

	public MataKuliah() {
		// TODO Auto-generated constructor stub
	}

	public String getKodeMK() {
		return kodeMK;
	}

	public void setKodeMK(String kodeMK) {
		this.kodeMK = kodeMK;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getSks() {
		return sks;
	}

	public void setSks(String sks) {
		this.sks = sks;
	}

	public int getMkID() {
		return mkID;
	}

	public void setMkID(int mkID) {
		this.mkID = mkID;
	}

}