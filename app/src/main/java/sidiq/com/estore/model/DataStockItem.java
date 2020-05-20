package sidiq.com.estore.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DataStockItem{

	@SerializedName("id_stock")
	private String idStock;

	@SerializedName("ukuran_barang")
	private String ukuranBarang;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("harga_barang")
	private String hargaBarang;

	@SerializedName("image_barang")
	private String imageBarang;

	@SerializedName("nama_toko")
	private String namaToko;

	public void setIdStock(String idStock){
		this.idStock = idStock;
	}

	public String getIdStock(){
		return idStock;
	}

	public void setUkuranBarang(String ukuranBarang){
		this.ukuranBarang = ukuranBarang;
	}

	public String getUkuranBarang(){
		return ukuranBarang;
	}

	public void setNamaBarang(String namaBarang){
		this.namaBarang = namaBarang;
	}

	public String getNamaBarang(){
		return namaBarang;
	}

	public void setHargaBarang(String hargaBarang){
		this.hargaBarang = hargaBarang;
	}

	public String getHargaBarang(){
		return hargaBarang;
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
			"DataStockItem{" + 
			"id_stock = '" + idStock + '\'' + 
			",ukuran_barang = '" + ukuranBarang + '\'' + 
			",nama_barang = '" + namaBarang + '\'' + 
			",harga_barang = '" + hargaBarang + '\'' + 
			",image_barang = '" + imageBarang + '\'' + 
			",nama_toko = '" + namaToko + '\'' + 
			"}";
		}
}