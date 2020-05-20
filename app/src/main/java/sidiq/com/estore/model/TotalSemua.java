package sidiq.com.estore.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class TotalSemua{

	@SerializedName("total_harga")
	private String totalHarga;

	public void setTotalHarga(String totalHarga){
		this.totalHarga = totalHarga;
	}

	public String getTotalHarga(){
		return totalHarga;
	}

	@Override
 	public String toString(){
		return 
			"TotalSemua{" + 
			"total_harga = '" + totalHarga + '\'' + 
			"}";
		}
}