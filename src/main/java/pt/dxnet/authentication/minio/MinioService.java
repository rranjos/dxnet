package pt.dxnet.authentication.minio;

import java.io.InputStream;

import org.springframework.stereotype.Service;

@Service
public interface MinioService {
	
	public void uploadObject(byte[] object, String contentType, String name) throws Exception;
	public InputStream getObject(String checksum, String contentType);

}
