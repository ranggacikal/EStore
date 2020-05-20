package sidiq.com.estore.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CheckoutOrderItem{

	@SerializedName("quantity_barang")
	private String quantityBarang;

	@SerializedName("ukuran_barang")
	private String ukuranBarang;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("total_harga")
	private String totalHarga;

	public void setQuantityBarang(String quantityBarang){
		this.quantityBarang = quantityBarang;
	}

	public String getQuantityBarang(){
		return quantityBarang;
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

	public void setTotalHarga(String totalHarga){
		this.totalHarga = totalHarga;
	}

	public String getTotalHarga(){
		return totalHarga;
	}

	@Override
 	public String toString(){
		return 
			namaBarang+" ("+ukuranBarang+") "+" x "+quantityBarang;
		}
}