package sidiq.com.estore.api;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sidiq.com.estore.model.ResponseBalasanPengaduan;
import sidiq.com.estore.model.ResponseCheckoutOrder;
import sidiq.com.estore.model.ResponseConfirmReqMember;
import sidiq.com.estore.model.ResponseDaftarMember;
import sidiq.com.estore.model.ResponseDataMember;
import sidiq.com.estore.model.ResponseDataOrder;
import sidiq.com.estore.model.ResponseDataStock;
import sidiq.com.estore.model.ResponseDataToko;
import sidiq.com.estore.model.ResponseDataUser;
import sidiq.com.estore.model.ResponseDeleteOrder;
import sidiq.com.estore.model.ResponseDeletePesanan;
import sidiq.com.estore.model.ResponseEditPesanan;
import sidiq.com.estore.model.ResponseEditStatusPesanan;
import sidiq.com.estore.model.ResponseEditStatusTransaksi;
import sidiq.com.estore.model.ResponseHapusStock;
import sidiq.com.estore.model.ResponseInsertCheckout;
import sidiq.com.estore.model.ResponseInsertPengaduan;
import sidiq.com.estore.model.ResponseInsertPesanan;
import sidiq.com.estore.model.ResponseInsertStock;
import sidiq.com.estore.model.ResponseKonfirmasiBuktiTransfer;
import sidiq.com.estore.model.ResponseLogin;
import sidiq.com.estore.model.ResponsePermintaanMember;
import sidiq.com.estore.model.ResponsePesananToko;
import sidiq.com.estore.model.ResponseRegister;
import sidiq.com.estore.model.ResponseRegisterToko;
import sidiq.com.estore.model.ResponseStatusPesanan;
import sidiq.com.estore.model.ResponseTotalOrder;
import sidiq.com.estore.model.ResponseTransaksiPelanggan;
import sidiq.com.estore.model.ResponseTransaksiToko;
import sidiq.com.estore.model.ResponseUpdateBuktiTransfer;
import sidiq.com.estore.model.ResponseUpdatePesananToko;
import sidiq.com.estore.model.ResponseUpdateStock;

public interface ApiService {

    @FormUrlEncoded
    @POST("Register_user")
    Call<ResponseRegister> Register(@Field("nama_lengkap") String nama_lengkap,
                                    @Field("username") String username,
                                    @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("level") String level,
                                    @Field("image_user") String image,
                                    @Field("status_member") String status_member,
                                    @Field("no_ktp") String no_ktp,
                                    @Field("no_telpon") String no_telpon,
                                    @Field("jenis_kelamin") String jenis_kelamin,
                                    @Field("tempat_lahir") String tempat_lahir,
                                    @Field("tanggal_lahir") String tanggal_lahir);

    @FormUrlEncoded
    @POST("Register_toko")
    Call<ResponseRegisterToko> RegisterToko(@Field("nama_toko") String nama_toko,
                                            @Field("username_toko") String username_toko,
                                            @Field("email_toko") String email_toko,
                                            @Field("password_toko") String password_toko,
                                            @Field("level_toko") String level_toko,
                                            @Field("image_user") String image_user,
                                            @Field("alamat_toko") String alamat_toko);

    @FormUrlEncoded
    @POST("Login_user")
    Call<ResponseLogin> Login(@Field("username") String username,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("DaftarMember")
    Call<ResponseDaftarMember> DaftarMember(@Field("id_user") String id_user,
                                            @Field("no_telpon") String no_telpon,
                                            @Field("status_member") String status_member);

    @GET("getDataPermintaanMember")
    Call<ResponsePermintaanMember> RequestMember();

    @FormUrlEncoded
    @POST("KonfirmasiMember")
    Call<ResponseConfirmReqMember> KonfirmasiMember(@Field("id_user") String id_user,
                                                    @Field("status_member") String status_member);

    @GET("getDataMember")
    Call<ResponseDataMember> DataMember();

    @GET("getDataUser")
    Call<ResponseDataUser> DataUser();

    @GET("getDataStock/{id_toko}")
    Call<ResponseDataStock> DataStock(@Path("id_toko") String  id_toko);

    @FormUrlEncoded
    @POST("insertDataOrder")
    Call<ResponseInsertPesanan> InsertPesanan(@Field("image_barang") String image_barang,
                                              @Field("nama_barang") String nama_barang,
                                              @Field("ukuran_barang") String ukuran_barang,
                                              @Field("quantity_barang") int quantity_barang,
                                              @Field("harga_satuan") int harga_satuan,
                                              @Field("total_harga") int total_harga,
                                              @Field("nama_pelanggan") String nama_pelanggan,
                                              @Field("nama_toko") String nama_toko);

    @GET("GetDataOrder/{nama_pelanggan}")
    Call<ResponseDataOrder> DataOrder(@Path("nama_pelanggan") String nama_pelanggan);

    @GET("TotalHargaOrder/{nama_pelanggan}")
    Call<ResponseTotalOrder> TotalOrder(@Path("nama_pelanggan") String nama_pelanggan);

    @GET("CheckoutOrder/{nama_pelanggan}")
    Call<ResponseCheckoutOrder> CheckoutOrder(@Path("nama_pelanggan") String nama_pelanggan);

    @FormUrlEncoded
    @POST("insertDataCheckout")
    Call<ResponseInsertCheckout> InsertCheckout(@Field("nama_pelanggan") String nama_pelanggan,
                                                @Field("list_pesanan") String list_pesanan,
                                                @Field("total_harga") int total_harga,
                                                @Field("metode_pembayaran") String metode_pembayaran,
                                                @Field("metode_pengambilan") String metode_pengambilan,
                                                @Field("alamat") String alamat,
                                                @Field("status_pesanan") String status_pesanan,
                                                @Field("status_transaksi") String status_transaksi,
                                                @Field("tanggal_transaksi") String tanggal_transaksi,
                                                @Field("id_pelanggan") String id_pelanggan,
                                                @Field("nama_toko") String nama_toko,
                                                @Field("nama_lengkap_toko") String nama_lengkap_toko);

    @GET("DeleteTableOrder/{nama_pelanggan}")
    Call<ResponseDeleteOrder> DeleteOrder(@Path("nama_pelanggan") String nama_pelanggan);

    @GET("getDataStatusPesanan/{id_pelanggan}")
    Call<ResponseStatusPesanan> getStatusPesanan(@Path("id_pelanggan") String id_pelanggan);

    @GET("getDataToko")
    Call<ResponseDataToko> getDataToko();

    @GET("DataPesananToko/{nama_pelanggan}")
    Call<ResponsePesananToko> PesananToko(@Path("nama_pelanggan") String nama_pelanggan);

    @FormUrlEncoded
    @POST("UpdateDataPesananToko")
    Call<ResponseUpdatePesananToko> UpdatePesananToko(@Field("id_transaksi") int id_transaksi,
                                                      @Field("status_pesanan") String status_pesanan,
                                                      @Field("status_transaksi") String status_transaksi);

    @FormUrlEncoded
    @POST("insertDataStock")
    Call<ResponseInsertStock> InsertStock(@Field("nama_barang") String nama_barang,
                                          @Field("harga_barang") String harga_barang,
                                          @Field("ukuran_barang") String ukuran_barang,
                                          @Field("image_barang") String image_barang,
                                          @Field("nama_toko") String nama_toko);

    @FormUrlEncoded
    @POST("UpdateDataStock")
    Call<ResponseUpdateStock> UpdateStock(@Field("id_stock") String id_stock,
                                          @Field("nama_barang") String nama_barang,
                                          @Field("harga_barang") String harga_barang,
                                          @Field("ukuran_barang") String ukuran_barang,
                                          @Field("image_barang") String image_barang,
                                          @Field("nama_toko") String nama_toko);

    @FormUrlEncoded
    @POST("DeleteDataStock")
    Call<ResponseHapusStock> HapusStock(@Field("id_stock") String id_stock);

    @FormUrlEncoded
    @POST("UpdateBuktiTransfer")
    Call<ResponseUpdateBuktiTransfer> UpdateBuktiTransfer(@Field("id_transaksi") String id_transaksi,
                                                          @Field("bukti_transfer") String bukti_transfer);

    @FormUrlEncoded
    @POST("KonfirmasiBuktiTransfer")
    Call<ResponseKonfirmasiBuktiTransfer> KonfirmasiBukti(@Field("id_transaksi") String id_transaksi,
                                                          @Field("status_pesanan") String status_pesanan);

    @FormUrlEncoded
    @POST("EditStatusPesanan")
    Call<ResponseEditStatusPesanan> EditStatusPesanan(@Field("id_transaksi") String id_transaksi,
                                                      @Field("status_pesanan") String status_pesanan);

    @GET("DataTransaksiToko/{nama_toko}")
    Call<ResponseTransaksiToko> TransaksiToko(@Path("nama_toko") String nama_toko);

    @FormUrlEncoded
    @POST("EditPesanan")
    Call<ResponseEditPesanan> EditPesanan(@Field("id_order") String id_order,
                                          @Field("quantity_barang") String quantity_barang,
                                          @Field("total_harga") int total_harga);

    @FormUrlEncoded
    @POST("DeletePesanan")
    Call<ResponseDeletePesanan> DeletePesanan(@Field("id_order") String id_order);

    @GET("DataTransaksiPelanggan/{id_pelanggan}")
    Call<ResponseTransaksiPelanggan> TransaksiPelanggan(@Path("id_pelanggan") String id_pelanggan);

    @FormUrlEncoded
    @POST("EditStatusTransaksi")
    Call<ResponseEditStatusTransaksi> EditStatusTransaksi(@Field("id_transaksi") String id_transaksi,
                                                          @Field("status_transaksi") String status_transaksi);

    @FormUrlEncoded
    @POST("insertPengaduan")
    Call<ResponseInsertPengaduan> InsertPengaduan(@Field("nama_pengadu") String nama_pengadu,
                                                  @Field("pengaduan") String pengaduan,
                                                  @Field("id_pelanggan") String id_pelanggan);

    @GET("ResponsePengaduan/{id_pelanggan}")
    Call<ResponseBalasanPengaduan> BalasanPengaduan(@Path("id_pelanggan") String id_pelanggan);
}
