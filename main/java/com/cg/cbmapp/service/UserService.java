package com.cg.cbmapp.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.cbmapp.payload.UpdateRequest;
import com.cg.cbmapp.entities.User;
import com.cg.cbmapp.repository.IUserRepo;



@Service
public class UserService {

	@Autowired
	private IUserRepo userRepository;

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	public User getUserDetails(String email) {

		Optional<User> user = userRepository.findByEmail(email);

		return user.get();
	}

	public boolean updateUser(int id, UpdateRequest user) {
		User u1 = userRepository.findById(id).get();

		if (u1 != null) {

			u1.setEmail(user.getEmail());
			u1.setFirstname(user.getFirstname());
			u1.setLastname(user.getLastname());

			userRepository.save(u1);
			return true;
		}

		return false;

	}

//	public List<Booking> getBookings(int userId) {
//
//		ArrayList<Booking> getList = restTemplate
//				.getForObject("http://localhost:8083/bookingservice/getBookingByUserId/" + userId, ArrayList.class);
//
//		logger.info("{}", getList);
//		return getList;
//
//	}

}
