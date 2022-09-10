package com.example.Gestion_Recrutement.rest.controller;

import com.example.Gestion_Recrutement.ExceptionAndFilters.DisabledUserException;

import com.example.Gestion_Recrutement.ExceptionAndFilters.InvalidUserCredentialsException;
import com.example.Gestion_Recrutement.ExceptionAndFilters.util.JwtUtil;
import com.example.Gestion_Recrutement.entity.*;
import com.example.Gestion_Recrutement.service.UtilisateurService;
import org.springframework.security.core.userdetails.User;
import com.example.Gestion_Recrutement.vo.Request;
import com.example.Gestion_Recrutement.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class JwtRestApi {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/image/")
	public ResponseEntity<UploadResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
		utilisateurService.save(file,id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}

	@PostMapping("/signup")
	public ResponseEntity<Candidat> signup(@RequestBody Candidat candidat) throws ParseException {
		Candidat candidat1 = utilisateurService.addCondidat(candidat);
		return new ResponseEntity(candidat1, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<Response> generateJwtToken(@RequestBody Request request) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getUserPwd()));
		} catch (DisabledException e) {
			throw new DisabledUserException("User Inactive");
		} catch (BadCredentialsException e) {
			throw new InvalidUserCredentialsException("Invalid Credentials");
		}

		User user = (User) authentication.getPrincipal();
		System.out.println(user);
		Set<String> roles = user.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
		String token = jwtUtil.generateToken(authentication);
		Utilisateur utilisateur=utilisateurService.getUser(request.getUserEmail());
		if(utilisateur.getEnabled()==false){
			return null;
		}
		Response response = new Response();
		response.setToken(token);
		response.setRoles(roles.stream().findFirst());
		response.setUser_id(utilisateur.getId());

		if(utilisateur instanceof ResponsableRH){
			response.setCompany_id(((ResponsableRH) utilisateur).getCompany().getId());
		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}



	@GetMapping("/utilisateur/{id}")
	public ResponseEntity<Utilisateur> findUserById (@PathVariable("id") Long id) {
		Optional<Utilisateur> utilisateur = utilisateurService.findUserById(id);
		return new ResponseEntity(utilisateur, HttpStatus.OK);
	}


	@GetMapping("/verify/{code}")
	public ResponseEntity<Boolean> verifyCompte (@PathVariable("code") String code) {
		Boolean bool = utilisateurService.verifyCompte(code);
		return new ResponseEntity(bool, HttpStatus.OK);
	}

}
