package com.auth0.samples.bootfaces;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "productController")
@ELBeanName(value = "productController")
@Join(path = "/error", to = "/product-form.jsf")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	private Product product = new Product();

	public String save() {
		productRepository.save(product);
		product = new Product();
		return "/product-list.xhtml?faces-redirect=true";
	}

	public Product getProduct() {
		return product;
	}

}
