package sidiq.com.estore.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseBalasanPengaduan{

	@SerializedName("dataResponsePengaduan")
	private List<DataResponsePengaduanItem> dataResponsePengaduan;

	@SerializedName("status")
	private int status;

	public List<DataResponsePengaduanItem> getDataResponsePengaduan(){
		return dataResponsePengaduan;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseBalasanPengaduan{" + 
			"dataResponsePengaduan = '" + dataResponsePengaduan + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}