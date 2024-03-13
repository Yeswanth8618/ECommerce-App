package or.jsp.SpringBoot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import or.jsp.SpringBoot.model.User;
import or.jsp.SpringBoot.respository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userrepository;
	
	public User saveuser(User user) {
		return userrepository.save(user);
	}
	
	public User updateuser(User user) {
		return userrepository.save(user);
	}
	
	public Optional<User> findbyid(int id) {
		return userrepository.findById(id);
	}
	public boolean deletebyid(int id) {
		Optional<User> dbuser = findbyid(id);
		if (dbuser.isPresent()) {
			userrepository.delete(dbuser.get());
			return true;
		}
		return false;

	}

	public List<User> findall() {
		return userrepository.findAll();
	}
	public Optional<User> verifyuser(long phone, String password) {
		return userrepository.verify(phone, password);
	}

	public Optional<User> verifyuserbyemail(String email, String password) {
		return userrepository.verify(email, password);
	}
	public List<User> findbyname(String name) {
		return userrepository.findbyname(name);
	}
	public Optional<User> findByToken(String token) {
		return userrepository.findByToken(token);
	}
}

