package sidiq.com.estore.model;

import com.google.gson.annotations.SerializedName;

public class DataResponsePengaduanItem{

	@SerializedName("balasan_pengaduan")
	private String balasanPengaduan;

	@SerializedName("id_pengaduan")
	private String idPengaduan;

	@SerializedName("nama_pengadu")
	private String namaPengadu;

	@SerializedName("id_pelanggan")
	private String idPelanggan;

	@SerializedName("pengaduan")
	private String pengaduan;

	public String getBalasanPengaduan(){
		return balasanPengaduan;
	}

	public String getIdPengaduan(){
		return idPengaduan;
	}

	public String getNamaPengadu(){
		return namaPengadu;
	}

	public String getIdPelanggan(){
		return idPelanggan;
	}

	public String getPengaduan(){
		return pengaduan;
	}

	@Override
 	public String toString(){
		return 
			"DataResponsePengaduanItem{" + 
			"balasan_pengaduan = '" + balasanPengaduan + '\'' + 
			",id_pengaduan = '" + idPengaduan + '\'' + 
			",nama_pengadu = '" + namaPengadu + '\'' + 
			",id_pelanggan = '" + idPelanggan + '\'' + 
			",pengaduan = '" + pengaduan + '\'' + 
			"}";
		}
}