package sidiq.com.estore.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ResponseDataToko{

	@SerializedName("dataToko")
	private List<DataTokoItem> dataToko;

	@SerializedName("status")
	private int status;

	public void setDataToko(List<DataTokoItem> dataToko){
		this.dataToko = dataToko;
	}

	public List<DataTokoItem> getDataToko(){
		return dataToko;
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
			"ResponseDataToko{" + 
			"dataToko = '" + dataToko + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}