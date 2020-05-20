package sidiq.com.estore.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ResponsePermintaanMember{

	@SerializedName("dataPermintaanMember")
	private List<DataPermintaanMemberItem> dataPermintaanMember;

	@SerializedName("status")
	private int status;

	public void setDataPermintaanMember(List<DataPermintaanMemberItem> dataPermintaanMember){
		this.dataPermintaanMember = dataPermintaanMember;
	}

	public List<DataPermintaanMemberItem> getDataPermintaanMember(){
		return dataPermintaanMember;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponsePermintaanMember{" + 
			"dataPermintaanMember = '" + dataPermintaanMember + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}