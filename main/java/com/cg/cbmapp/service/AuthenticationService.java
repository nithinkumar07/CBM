package com.cg.cbmapp.service;

import com.cg.cbmapp.payload.AuthenticationRequest;
import com.cg.cbmapp.payload.AuthenticationResponse;
import com.cg.cbmapp.payload.RegisterRequest;
import com.cg.cbmapp.entities.User;

public interface AuthenticationService {

	AuthenticationResponse register(RegisterRequest request);

	AuthenticationResponse authenticate(AuthenticationRequest request);

	User changePassword(AuthenticationRequest request);

	User getUserById(int id);

	boolean deletebyid(Integer id);

	boolean signout(int id);

	User getUserDetails(String email);
}
