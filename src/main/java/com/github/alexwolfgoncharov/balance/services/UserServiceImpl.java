package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.dao.UserDAO;
import com.github.alexwolfgoncharov.balance.dao.impl.UserDAOImpl;
import com.github.alexwolfgoncharov.balance.security.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger log = Logger.getLogger(UserServiceImpl.class
			.getName());

//	@Autowired
	private UserDAO userDAO = new UserDAOImpl();
	
	 @Transactional
    public User getUser(String login) {
		
        return userDAO.getByLogin(login);
    }

	 @Transactional
	public void addUser(User user) {

		 String pass = "";
		 try {
			 pass = encryptPassword(user.getPassword());
			 user.setPassword(pass);
			 userDAO.add(user);
		 } catch (NoSuchAlgorithmException e) {
			 log.severe(e.getMessage());
		 } catch (UnsupportedEncodingException e) {
			 log.severe(e.getMessage());
		 }
	 }
	 
	 private static String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		    MessageDigest crypt = MessageDigest.getInstance("SHA-256");
		    crypt.reset();
		    crypt.update(password.getBytes("UTF-8"));

		    return new BigInteger(1, crypt.digest()).toString(16);
		}
	 
	 
	 public List<User> getAll(){

		return userDAO.getAll();

	 }

	@Override
	public User update(User user) {
		userDAO.add(user);
			 
		return userDAO.getByID(user.getId());
	}

	@Override
	public void deleteUser(User user) {


		log.info("delete" + user.toString());
		userDAO.delete(user);
		
		
	}

	@Override
	public void block(User user) {
		userDAO.block(user);
	}
}