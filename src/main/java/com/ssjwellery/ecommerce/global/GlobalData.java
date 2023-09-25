package com.ssjwellery.ecommerce.global;

import java.util.ArrayList;
import java.util.List;

import com.ssjwellery.ecommerce.model.Product;

public class GlobalData {
	public static List<Product> cart;
	static {
		cart = new ArrayList<Product>();
	}
}
