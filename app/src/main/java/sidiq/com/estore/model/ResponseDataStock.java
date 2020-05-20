package sidiq.com.estore.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ResponseDataStock{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("dataStock")
	private List<DataStockItem> dataStock;

	@SerializedName("status")
	private int status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setDataStock(List<DataStockItem> dataStock){
		this.dataStock = dataStock;
	}

	public List<DataStockItem> getDataStock(){
		return dataStock;
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
			"ResponseDataStock{" + 
			"pesan = '" + pesan + '\'' + 
			",dataStock = '" + dataStock + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}