package pt.dxnet.authentication.upload;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pt.dxnet.authentication.login.LoginServiceImp;
import pt.dxnet.authentication.minio.MinioService;

@Service
public class UploadImageServiceImpl implements UploadImageService {

	@Autowired
	MinioService minIoService;

	private static final Logger logger = LogManager.getLogger(LoginServiceImp.class);

	@Override
	public void uploadImage(MultipartFile file) throws UnsupportedEncodingException {

		try {
			byte[] hash = MessageDigest.getInstance("MD5").digest(file.getBytes());
			String checksum = new BigInteger(1, hash).toString(16);

			minIoService.uploadObject(file.getBytes(), file.getContentType(), checksum);

		} catch (Exception e) {
			logger.error("Minio error!");

		}

	}

}
