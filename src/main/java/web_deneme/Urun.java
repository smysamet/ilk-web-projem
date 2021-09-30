package web_deneme;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Urun {
	private String id;
	private String isim;
	private String fiyat;
	private String aciklama;
	private String adet;
	private String magaza_id;
	
	public Urun() {
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsim() {
		return isim;
	}
	public void setIsim(String isim) {
		this.isim = isim;
	}
	public String getFiyat() {
		return fiyat;
	}
	public void setFiyat(String fiyat) {
		this.fiyat = fiyat;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public String getAdet() {
		return adet;
	}
	public void setAdet(String adet) {
		this.adet = adet;
	}
	public String getMagaza_id() {
		return magaza_id;
	}
	public void setMagaza_id(String magaza_id) {
		this.magaza_id = magaza_id;
	}
	
	public void reset() {
		this.aciklama = "";
		this.adet = "";
		this.fiyat = "";
		this.isim = "";
		this.magaza_id = "";
	}
}
