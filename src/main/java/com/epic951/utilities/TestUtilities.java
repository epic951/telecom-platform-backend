package com.epic951.utilities;

import com.epic951.data.entities.Operator;
import com.epic951.data.entities.Product;
import com.epic951.data.entities.TelecomService;

public class TestUtilities {

	public static Product createTestProduct(String productName, int productId, String productDescription) {
		Product testProduct = new Product();
		testProduct.setProductName(productName);
		testProduct.setProductId(productId);
		testProduct.setProductDescription(productDescription);
		return testProduct;
	}

	public static Operator createTestOperator(int operatorId, String operatorCountry, String operatorName) {
		Operator testOperator = new Operator();
		testOperator.setOperatorId(operatorId);
		testOperator.setOperatorCountry(operatorCountry);
		testOperator.setOperatorName(operatorName);
		return testOperator;
	}

	public static TelecomService createTestTelecomService(int telecomServiceId, String operatorName,
			String telecomServiceName, boolean telecomServiceType, int operatorId, int operatorServiceId,
			int operatorPackageId) {
		TelecomService testTelecomService = new TelecomService();
		testTelecomService.setTelecomServiceId(telecomServiceId);
		testTelecomService.setOperatorName(operatorName);
		testTelecomService.setTelecomServiceName(telecomServiceName);
		testTelecomService.setTelecomServiceType(telecomServiceType);
		testTelecomService.setOperatorPackageId(operatorPackageId);
		testTelecomService.setOperatorServiceId(operatorServiceId);
		testTelecomService.setOperatorId(operatorId);
		return testTelecomService;
	}
}
