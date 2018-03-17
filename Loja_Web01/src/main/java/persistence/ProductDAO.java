package persistence;

import java.io.Serializable;
import java.util.List;
import org.hibernate.*;
import beans.Product;

public class ProductDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void insert(Product product) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(product);
		t.commit();
		session.close();
	}

	public static void alter(Product product) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(product);
		t.commit();
		session.close();
	}

	public static void exclude(Product product) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(product);
		t.commit();
		session.close();
	}

	public static List<Product> listing(String filter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query consult;
		if (filter.trim().length() == 0) {
			consult = session.createQuery("from Product order by pro_name");
		} else {
			consult = session.createQuery("from Product " + "where pro_name like :parameter order by pro_name");
			consult.setString("parameter", "%" + filter + "%");
		}
		List list = consult.list();
		session.close();
		return list;
	}

}
