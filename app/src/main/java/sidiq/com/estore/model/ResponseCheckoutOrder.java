package sidiq.com.estore.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ResponseCheckoutOrder{

	@SerializedName("checkoutOrder")
	private List<CheckoutOrderItem> checkoutOrder;

	@SerializedName("status")
	private int status;

	public void setCheckoutOrder(List<CheckoutOrderItem> checkoutOrder){
		this.checkoutOrder = checkoutOrder;
	}

	public List<CheckoutOrderItem> getCheckoutOrder(){
		return checkoutOrder;
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
			"ResponseCheckoutOrder{" + 
			"checkoutOrder = '" + checkoutOrder + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}