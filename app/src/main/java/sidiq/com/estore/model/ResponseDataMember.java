package sidiq.com.estore.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ResponseDataMember{

	@SerializedName("dataMember")
	private List<DataMemberItem> dataMember;

	@SerializedName("status")
	private int status;

	public void setDataMember(List<DataMemberItem> dataMember){
		this.dataMember = dataMember;
	}

	public List<DataMemberItem> getDataMember(){
		return dataMember;
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
			"ResponseDataMember{" + 
			"dataMember = '" + dataMember + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}