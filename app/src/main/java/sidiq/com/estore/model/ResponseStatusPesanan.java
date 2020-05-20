package sidiq.com.estore.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseStatusPesanan{

	@SerializedName("dataStatusPesanan")
	private List<DataStatusPesananItem> dataStatusPesanan;

	@SerializedName("status")
	private int status;

	public List<DataStatusPesananItem> getDataStatusPesanan(){
		return dataStatusPesanan;
	}

	public int getStatus(){
		return status;
	}
}