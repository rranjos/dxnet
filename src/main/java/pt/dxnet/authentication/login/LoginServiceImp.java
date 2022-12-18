package pt.dxnet.authentication.login;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pt.dxnet.authentication.minio.MinioService;

@Service
public class LoginServiceImp implements LoginService {

	@Autowired
	MinioService minIoService;
	
	private static final Logger logger = LogManager.getLogger(LoginServiceImp.class);

	@Override
	public InputStream login(MultipartFile file) {
		InputStream image = null;
		try {
			byte[] hash = MessageDigest.getInstance("MD5").digest(file.getBytes());
			String checksum = new BigInteger(1, hash).toString(16);
			image = minIoService.getObject(checksum, file.getContentType());
		} catch (Exception e) {
			logger.error("Image not found!");
		}
		return image;

	}

}
