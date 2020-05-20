package sidiq.com.estore.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ResponseDaftarMember{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private int sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setSukses(int sukses){
		this.sukses = sukses;
	}

	public int getSukses(){
		return sukses;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDaftarMember{" + 
			"pesan = '" + pesan + '\'' + 
			",sukses = '" + sukses + '\'' + 
			"}";
		}
}