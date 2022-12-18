package pt.dxnet.authentication.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface JwtGeneratorInterface {
	
	Map<String, String> tokenGenerate(String image) throws UnsupportedEncodingException;

}
