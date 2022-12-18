package pt.dxnet.authentication.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pt.dxnet.authentication.jwt.JwtGeneratorInterface;
import pt.dxnet.authentication.login.LoginService;
import pt.dxnet.authentication.upload.UploadImageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Content-type")
public class AppController {

	@Autowired
	UploadImageService uploadImageService;

	@Autowired
	LoginService loginService;

	@Autowired
	JwtGeneratorInterface jwtService;

	@PostMapping(value = "upload")
	public ResponseEntity<String> uploadImage(@RequestParam MultipartFile file) throws UnsupportedEncodingException {
		uploadImageService.uploadImage(file);
		return ResponseEntity.ok("");
	}

	@PostMapping({ "/login" })
	public ResponseEntity<?> login(@RequestParam MultipartFile file) throws UnsupportedEncodingException {
		InputStream image = loginService.login(file);
		if (image != null) {
			return new ResponseEntity<>(jwtService.tokenGenerate(""), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Fail", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/hello")
	public ResponseEntity<?> sayHello() {
		return ResponseEntity.ok("ok");
	}

}
