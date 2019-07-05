package com.myrest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myrest.model.User;

@Service
public class UserDaoService {

	static List<User> userList = new ArrayList<>();
	int userCount = 2;

	static {

		userList.add(new User(1, "Yogesh"));
		userList.add(new User(2, "Rama"));

	}

	public User getUser(int id) {
		for (User user : userList) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public List<User> getAllUser() {
		return userList;
	}

	public User save(User user) {

		user.setId(++userCount);
		userList.add(user);

		for (User users : userList) {
			if (users.getId() == userCount) {
				return users;
			}
		}
		return null;
	}

	public User deleteUser(int id) {

		Iterator<User> itr = userList.iterator();
		while (itr.hasNext()) {

			User user = itr.next();
			if (user.getId() == id) {
				itr.remove();
				return user;
			}

		}
		return null;

	}

}
