package pt.dxnet.authentication.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtGeneratorInterfaceImpl implements JwtGeneratorInterface {

	@Value("${jwt.secret}")
	private String secret;
	@Value("${app.jwttoken.message}")
	private String message;

	@Override
	public Map<String, String> tokenGenerate(String image) throws UnsupportedEncodingException {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject("me").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secret").compact();
		Map<String, String> jwtTokenGen = new HashMap<String, String>();
		jwtTokenGen.put("token", jwtToken);
		jwtTokenGen.put("message", message);
		return jwtTokenGen;
	}

}
