package sidiq.com.estore.model;

import com.google.gson.annotations.SerializedName;

public class ResponseRegisterToko{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private boolean sukses;

	@SerializedName("status")
	private int status;

	public String getPesan(){
		return pesan;
	}

	public boolean isSukses(){
		return sukses;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseRegisterToko{" + 
			"pesan = '" + pesan + '\'' + 
			",sukses = '" + sukses + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}