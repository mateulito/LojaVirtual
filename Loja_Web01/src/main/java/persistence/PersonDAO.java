package persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.*;

import beans.Person;

public class PersonDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void insert(Person person) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(person);
		t.commit();
		session.close();
	}

	public static void alter(Person person) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(person);
		t.commit();
		session.close();
	}

	public static void exclude(Person person) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(person);
		t.commit();
		session.close();
	}

	public static List<Person> listing(String filter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query consult;
		if (filter.trim().length() == 0) {
			consult = session.createQuery("from Person order by per_name");
		} else {
			consult = session.createQuery("from Person " + "where per_name :parameter order by per_name");
			consult.setString("parameter", "%" + filter + "%");
		}
		List list = consult.list();
		session.close();
		return list;
	}
}
