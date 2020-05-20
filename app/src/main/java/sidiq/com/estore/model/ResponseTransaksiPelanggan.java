package sidiq.com.estore.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseTransaksiPelanggan{

	@SerializedName("dataTransaksiPelanggan")
	private List<DataTransaksiPelangganItem> dataTransaksiPelanggan;

	@SerializedName("status")
	private int status;

	public List<DataTransaksiPelangganItem> getDataTransaksiPelanggan(){
		return dataTransaksiPelanggan;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseTransaksiPelanggan{" + 
			"dataTransaksiPelanggan = '" + dataTransaksiPelanggan + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}