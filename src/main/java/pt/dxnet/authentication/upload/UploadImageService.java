package pt.dxnet.authentication.upload;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UploadImageService {
	
	void uploadImage(MultipartFile file) throws UnsupportedEncodingException ;

}
