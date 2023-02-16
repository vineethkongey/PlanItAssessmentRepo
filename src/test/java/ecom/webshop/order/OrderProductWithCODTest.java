package ecom.webshop.order;

import java.util.Map;

import org.testng.annotations.Test;

import com.crm.autodesk.genericUtility.BaseClass;

public class OrderProductWithCODTest extends BaseClass {

	@Test
	public void orderTest() {
		
		Map<String, String> map = eLib.getData("webshop", getClass().getSimpleName());
		int quantity=Integer.parseInt(map.get("quantity"));
		
		hp.clickLoginLink()
		.loginAction(email, password)
		.verifyAccountId(email)
		.clickCartLink().clearCart()
		.hoverOnComputer()
		.selectDesktop()
		.selectComputer(map.get("computerName"))
		.getPrice().addQuantity(quantity)
		.clickAddToCart()
		.verifySucessMsg(map.get("verifySuccessMessage"))
		.getHomePage().clickCartLink()
		.validateSubTotal(quantity)
		.clickCheckout()
		.billingAddress(eLib)
		.shippingAddress()
		.shippingMethod(map.get("shippingMethod"))
		.paymentMethod()
		.verifyPaymentInformation( map.get("verifyPaymentInfoMessage"))
		.clickConfirm()
		.validateOrderMessage(map.get("verifyOrderMessage"))
		.getOrderNumber()
		.clickContinue()
		.clickLogout();

	}
}
