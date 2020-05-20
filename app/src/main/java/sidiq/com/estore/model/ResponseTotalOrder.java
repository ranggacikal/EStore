package sidiq.com.estore.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ResponseTotalOrder{

	@SerializedName("total_semua")
	private TotalSemua totalSemua;

	@SerializedName("status")
	private int status;

	public void setTotalSemua(TotalSemua totalSemua){
		this.totalSemua = totalSemua;
	}

	public TotalSemua getTotalSemua(){
		return totalSemua;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseTotalOrder{" + 
			"total_semua = '" + totalSemua + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}