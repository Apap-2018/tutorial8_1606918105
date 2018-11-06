package com.apap.tutorial6.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PassModel implements Serializable {

	private String passlama;
	private String passbaru;
	private String passkonfirmasi;
	
	public String getPasslama() {
		return passlama;
	}
	public void setPasslama(String passlama) {
		this.passlama = passlama;
	}
	public String getPassbaru() {
		return passbaru;
	}
	public void setPassbaru(String passbaru) {
		this.passbaru = passbaru;
	}
	public String getPasskonfirmasi() {
		return passkonfirmasi;
	}
	public void setPasskonfirmasi(String passkonfirmasi) {
		this.passkonfirmasi = passkonfirmasi;
	}
	
}
