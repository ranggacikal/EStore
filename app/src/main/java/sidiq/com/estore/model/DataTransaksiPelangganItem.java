package sidiq.com.estore.model;

import com.google.gson.annotations.SerializedName;

public class DataTransaksiPelangganItem{

	@SerializedName("total_harga")
	private String totalHarga;

	@SerializedName("id_transaksi")
	private String idTransaksi;

	@SerializedName("id_pelanggan")
	private String idPelanggan;

	@SerializedName("status_pesanan")
	private String statusPesanan;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("nama_toko")
	private String namaToko;

	@SerializedName("metode_pembayaran")
	private String metodePembayaran;

	@SerializedName("nama_pelanggan")
	private String namaPelanggan;

	@SerializedName("list_pesanan")
	private String listPesanan;

	@SerializedName("metode_pengambilan")
	private String metodePengambilan;

	@SerializedName("tanggal_transaksi")
	private String tanggalTransaksi;

	@SerializedName("bukti_transfer")
	private String buktiTransfer;

	@SerializedName("status_transaksi")
	private String statusTransaksi;

	public String getTotalHarga(){
		return totalHarga;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}

	public String getIdPelanggan(){
		return idPelanggan;
	}

	public String getStatusPesanan(){
		return statusPesanan;
	}

	public String getAlamat(){
		return alamat;
	}

	public String getNamaToko(){
		return namaToko;
	}

	public String getMetodePembayaran(){
		return metodePembayaran;
	}

	public String getNamaPelanggan(){
		return namaPelanggan;
	}

	public String getListPesanan(){
		return listPesanan;
	}

	public String getMetodePengambilan(){
		return metodePengambilan;
	}

	public String getTanggalTransaksi(){
		return tanggalTransaksi;
	}

	public String getBuktiTransfer(){
		return buktiTransfer;
	}

	public String getStatusTransaksi(){
		return statusTransaksi;
	}

	@Override
 	public String toString(){
		return 
			"DataTransaksiPelangganItem{" + 
			"total_harga = '" + totalHarga + '\'' + 
			",id_transaksi = '" + idTransaksi + '\'' + 
			",id_pelanggan = '" + idPelanggan + '\'' + 
			",status_pesanan = '" + statusPesanan + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",nama_toko = '" + namaToko + '\'' + 
			",metode_pembayaran = '" + metodePembayaran + '\'' + 
			",nama_pelanggan = '" + namaPelanggan + '\'' + 
			",list_pesanan = '" + listPesanan + '\'' + 
			",metode_pengambilan = '" + metodePengambilan + '\'' + 
			",tanggal_transaksi = '" + tanggalTransaksi + '\'' + 
			",bukti_transfer = '" + buktiTransfer + '\'' + 
			",status_transaksi = '" + statusTransaksi + '\'' + 
			"}";
		}
}