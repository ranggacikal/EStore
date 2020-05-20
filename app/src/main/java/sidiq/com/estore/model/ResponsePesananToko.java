package sidiq.com.estore.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ResponsePesananToko{

	@SerializedName("dataPesananToko")
	private List<DataPesananTokoItem> dataPesananToko;

	@SerializedName("status")
	private int status;

	public void setDataPesananToko(List<DataPesananTokoItem> dataPesananToko){
		this.dataPesananToko = dataPesananToko;
	}

	public List<DataPesananTokoItem> getDataPesananToko(){
		return dataPesananToko;
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
			"ResponsePesananToko{" + 
			"dataPesananToko = '" + dataPesananToko + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}