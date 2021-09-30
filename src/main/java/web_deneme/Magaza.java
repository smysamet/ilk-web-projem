package web_deneme;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Magaza {
	private String id;
	private String name;
	
	public Magaza() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void reset() {
		this.id = "";
		this.name = "";
	}
	
}
