package sidiq.com.estore.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DataPesananTokoItem{

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

	public void setTotalHarga(String totalHarga){
		this.totalHarga = totalHarga;
	}

	public String getTotalHarga(){
		return totalHarga;
	}

	public void setIdTransaksi(String idTransaksi){
		this.idTransaksi = idTransaksi;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}

	public void setIdPelanggan(String idPelanggan){
		this.idPelanggan = idPelanggan;
	}

	public String getIdPelanggan(){
		return idPelanggan;
	}

	public void setStatusPesanan(String statusPesanan){
		this.statusPesanan = statusPesanan;
	}

	public String getStatusPesanan(){
		return statusPesanan;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setNamaToko(String namaToko){
		this.namaToko = namaToko;
	}

	public String getNamaToko(){
		return namaToko;
	}

	public void setMetodePembayaran(String metodePembayaran){
		this.metodePembayaran = metodePembayaran;
	}

	public String getMetodePembayaran(){
		return metodePembayaran;
	}

	public void setNamaPelanggan(String namaPelanggan){
		this.namaPelanggan = namaPelanggan;
	}

	public String getNamaPelanggan(){
		return namaPelanggan;
	}

	public void setListPesanan(String listPesanan){
		this.listPesanan = listPesanan;
	}

	public String getListPesanan(){
		return listPesanan;
	}

	public void setMetodePengambilan(String metodePengambilan){
		this.metodePengambilan = metodePengambilan;
	}

	public String getMetodePengambilan(){
		return metodePengambilan;
	}

	public void setTanggalTransaksi(String tanggalTransaksi){
		this.tanggalTransaksi = tanggalTransaksi;
	}

	public String getTanggalTransaksi(){
		return tanggalTransaksi;
	}

	public void setBuktiTransfer(String buktiTransfer){
		this.buktiTransfer = buktiTransfer;
	}

	public String getBuktiTransfer(){
		return buktiTransfer;
	}

	public void setStatusTransaksi(String statusTransaksi){
		this.statusTransaksi = statusTransaksi;
	}

	public String getStatusTransaksi(){
		return statusTransaksi;
	}

	@Override
 	public String toString(){
		return 
			"DataPesananTokoItem{" + 
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