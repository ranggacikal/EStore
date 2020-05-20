package sidiq.com.estore.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ResponseTransaksiToko{

	@SerializedName("dataTransaksiToko")
	private List<DataTransaksiTokoItem> dataTransaksiToko;

	@SerializedName("status")
	private int status;

	public void setDataTransaksiToko(List<DataTransaksiTokoItem> dataTransaksiToko){
		this.dataTransaksiToko = dataTransaksiToko;
	}

	public List<DataTransaksiTokoItem> getDataTransaksiToko(){
		return dataTransaksiToko;
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
			"ResponseTransaksiToko{" + 
			"dataTransaksiToko = '" + dataTransaksiToko + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}