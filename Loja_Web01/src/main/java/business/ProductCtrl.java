package business;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import persistence.ProductDAO;
import beans.Product;

@ManagedBean
@SessionScoped
public class ProductCtrl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Product product;
	
	public void Product() {
		this.product = new Product();
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getListing() {
		return ProductDAO.listing(null);
	}
	
	public String actionRecord() {
		 if (product.getId() == 0) {
			 ProductDAO.insert(product);
			 return actionInsert();
		 }else {
			 ProductDAO.alter(product);
			 return "form_product";
		 }
	}
	
	public String actionInsert() {
		product = new Product();
		return "form_product";
	}
	
	public String actionExclude(Product p) {
		ProductDAO.exclude(p);
		return "list_product";
	}
	
	public String actionAlter(Product p) {
		product = p;
		return "form_product";
	}
}