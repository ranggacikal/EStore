package sidiq.com.estore.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DataLogin{

	@SerializedName("password")
	private String password;

	@SerializedName("level")
	private String level;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("status_member")
	private String statusMember;

	@SerializedName("email")
	private String email;

	@SerializedName("image_user")
	private String imageUser;

	@SerializedName("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setStatusMember(String statusMember){
		this.statusMember = statusMember;
	}

	public String getStatusMember(){
		return statusMember;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setImageUser(String imageUser){
		this.imageUser = imageUser;
	}

	public String getImageUser(){
		return imageUser;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"DataLogin{" + 
			"password = '" + password + '\'' + 
			",level = '" + level + '\'' + 
			",nama_lengkap = '" + namaLengkap + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",status_member = '" + statusMember + '\'' + 
			",email = '" + email + '\'' + 
			",image_user = '" + imageUser + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}