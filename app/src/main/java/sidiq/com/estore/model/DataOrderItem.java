package sidiq.com.estore.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DataOrderItem{

	@SerializedName("id_order")
	private String idOrder;

	@SerializedName("quantity_barang")
	private String quantityBarang;

	@SerializedName("nama_pelanggan")
	private String namaPelanggan;

	@SerializedName("ukuran_barang")
	private String ukuranBarang;

	@SerializedName("harga_satuan")
	private String hargaSatuan;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("total_harga")
	private String totalHarga;

	@SerializedName("image_barang")
	private String imageBarang;

	@SerializedName("nama_toko")
	private String namaToko;

	public void setIdOrder(String idOrder){
		this.idOrder = idOrder;
	}

	public String getIdOrder(){
		return idOrder;
	}

	public void setQuantityBarang(String quantityBarang){
		this.quantityBarang = quantityBarang;
	}

	public String getQuantityBarang(){
		return quantityBarang;
	}

	public void setNamaPelanggan(String namaPelanggan){
		this.namaPelanggan = namaPelanggan;
	}

	public String getNamaPelanggan(){
		return namaPelanggan;
	}

	public void setUkuranBarang(String ukuranBarang){
		this.ukuranBarang = ukuranBarang;
	}

	public String getUkuranBarang(){
		return ukuranBarang;
	}

	public void setHargaSatuan(String hargaSatuan){
		this.hargaSatuan = hargaSatuan;
	}

	public String getHargaSatuan(){
		return hargaSatuan;
	}

	public void setNamaBarang(String namaBarang){
		this.namaBarang = namaBarang;
	}

	public String getNamaBarang(){
		return namaBarang;
	}

	public void setTotalHarga(String totalHarga){
		this.totalHarga = totalHarga;
	}

	public String getTotalHarga(){
		return totalHarga;
	}

	public void setImageBarang(String imageBarang){
		this.imageBarang = imageBarang;
	}

	public String getImageBarang(){
		return imageBarang;
	}

	public void setNamaToko(String namaToko){
		this.namaToko = namaToko;
	}

	public String getNamaToko(){
		return namaToko;
	}

	@Override
 	public String toString(){
		return 
			"DataOrderItem{" + 
			"id_order = '" + idOrder + '\'' + 
			",quantity_barang = '" + quantityBarang + '\'' + 
			",nama_pelanggan = '" + namaPelanggan + '\'' + 
			",ukuran_barang = '" + ukuranBarang + '\'' + 
			",harga_satuan = '" + hargaSatuan + '\'' + 
			",nama_barang = '" + namaBarang + '\'' + 
			",total_harga = '" + totalHarga + '\'' + 
			",image_barang = '" + imageBarang + '\'' + 
			",nama_toko = '" + namaToko + '\'' + 
			"}";
		}
}