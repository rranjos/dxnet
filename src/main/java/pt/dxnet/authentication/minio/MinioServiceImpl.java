package pt.dxnet.authentication.minio;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.io.ByteSource;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import pt.dxnet.authentication.login.LoginServiceImp;

@Service
public class MinioServiceImpl implements MinioService {

	private static final Logger logger = LogManager.getLogger(LoginServiceImp.class);

	@Override
	public InputStream getObject(String nome, String contentType) {

		InputStream stream = null;

		MinioClient client = getConnection();
		try {
			stream = client.getObject(GetObjectArgs.builder().bucket("images").object(nome).build());

		} catch (Exception e) {
			logger.error("Image not found!");
		}
		return stream;

	}

	@Override
	public void uploadObject(byte[] object, String contentType, String name) throws Exception {
		MinioClient client = getConnection();
		InputStream inputStream = ByteSource.wrap(object).openStream();

		client.putObject(PutObjectArgs.builder().bucket("images").object(name).stream(inputStream, -1, object.length)
				.contentType(contentType).build());
	}

	private MinioClient getConnection() {
		MinioClient minioClient = MinioClient.builder().endpoint("http://minio:9000").build();
		return minioClient;
	}

}
