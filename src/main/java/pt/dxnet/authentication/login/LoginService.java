package pt.dxnet.authentication.login;

import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface LoginService {

	InputStream login(MultipartFile file);

}
