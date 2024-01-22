package com.example.otogaleri.models;

public class AdDetailModel {
	private String ortalamayakit;
	private String km;
	private String surat;
	private String mahalle;
	private String ucret;
	private String motorhacmi;
	private String kimden;
	private String aciklama;
	private String ilce;
	private String uyeid;
	private String sehiradi;
	private String yakittipi;
	private String ilantipi;
	private String marka;
	private String model;
	private String depohacmi;
	private String seri;
	private String motortipi;
	private String yil;
	private String baslik;

	public void setOrtalamayakit(String ortalamayakit){
		this.ortalamayakit = ortalamayakit;
	}

	public String getOrtalamayakit(){
		return ortalamayakit;
	}

	public void setKm(String km){
		this.km = km;
	}

	public String getKm(){
		return km;
	}

	public void setSurat(String surat){
		this.surat = surat;
	}

	public String getSurat(){
		return surat;
	}

	public void setMahalle(String mahalle){
		this.mahalle = mahalle;
	}

	public String getMahalle(){
		return mahalle;
	}

	public void setUcret(String ucret){
		this.ucret = ucret;
	}

	public String getUcret(){
		return ucret;
	}

	public void setMotorhacmi(String motorhacmi){
		this.motorhacmi = motorhacmi;
	}

	public String getMotorhacmi(){
		return motorhacmi;
	}

	public void setKimden(String kimden){
		this.kimden = kimden;
	}

	public String getKimden(){
		return kimden;
	}

	public void setAciklama(String aciklama){
		this.aciklama = aciklama;
	}

	public String getAciklama(){
		return aciklama;
	}

	public void setIlce(String ilce){
		this.ilce = ilce;
	}

	public String getIlce(){
		return ilce;
	}

	public void setUyeid(String uyeid){
		this.uyeid = uyeid;
	}

	public String getUyeid(){
		return uyeid;
	}

	public void setSehiradi(String sehiradi){
		this.sehiradi = sehiradi;
	}

	public String getSehiradi(){
		return sehiradi;
	}

	public void setYakittipi(String yakittipi){
		this.yakittipi = yakittipi;
	}

	public String getYakittipi(){
		return yakittipi;
	}

	public void setIlantipi(String ilantipi){
		this.ilantipi = ilantipi;
	}

	public String getIlantipi(){
		return ilantipi;
	}

	public void setMarka(String marka){
		this.marka = marka;
	}

	public String getMarka(){
		return marka;
	}

	public void setModel(String model){
		this.model = model;
	}

	public String getModel(){
		return model;
	}

	public void setDepohacmi(String depohacmi){
		this.depohacmi = depohacmi;
	}

	public String getDepohacmi(){
		return depohacmi;
	}

	public void setSeri(String seri){
		this.seri = seri;
	}

	public String getSeri(){
		return seri;
	}

	public void setMotortipi(String motortipi){
		this.motortipi = motortipi;
	}

	public String getMotortipi(){
		return motortipi;
	}

	public void setYil(String yil){
		this.yil = yil;
	}

	public String getYil(){
		return yil;
	}

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	@Override
 	public String toString(){
		return 
			"AdsetailModel{" +
			"ortalamayakit = '" + ortalamayakit + '\'' + 
			",km = '" + km + '\'' + 
			",surat = '" + surat + '\'' + 
			",mahalle = '" + mahalle + '\'' + 
			",ucret = '" + ucret + '\'' + 
			",motorhacmi = '" + motorhacmi + '\'' + 
			",kimden = '" + kimden + '\'' + 
			",aciklama = '" + aciklama + '\'' + 
			",ilce = '" + ilce + '\'' + 
			",uyeid = '" + uyeid + '\'' + 
			",sehiradi = '" + sehiradi + '\'' + 
			",yakittipi = '" + yakittipi + '\'' + 
			",ilantipi = '" + ilantipi + '\'' + 
			",marka = '" + marka + '\'' + 
			",model = '" + model + '\'' + 
			",depohacmi = '" + depohacmi + '\'' + 
			",seri = '" + seri + '\'' + 
			",motortipi = '" + motortipi + '\'' + 
			",yil = '" + yil + '\'' + 
			",baslik = '" + baslik + '\'' + 
			"}";
		}
}
