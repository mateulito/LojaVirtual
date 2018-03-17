package business;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.Person;
import persistence.PersonDAO;

@ManagedBean
@SessionScoped
public class PersonCtrl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Person person;
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public List<Person> getListing(){
		return PersonDAO.listing(null);
	}
	
	public String actionRecord() {
		if(person.getId() == 0) {
			PersonDAO.insert(person);
			return actionInsert();
		}else {
			PersonDAO.alter(person);
			return "form_person";
		}
	}
	
	public String actionInsert() {
		person = new Person();
		return "form_person";
	}
	
	public String actionExclude(Person p) {
		PersonDAO.exclude(p);
		return "list_person";
	}
	
	public String actionAlter(Person p) {
		person = p;
		return "form_person";
	}
	
}
