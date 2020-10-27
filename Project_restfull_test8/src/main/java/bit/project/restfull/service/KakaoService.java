package bit.project.restfull.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bit.project.restfull.mapper.LoginMapper;
import bit.project.restfull.mapper.SnsMapper;
import bit.project.restfull.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@NoArgsConstructor
public class KakaoService {

	public void kakaoLogout(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
		        
			int responseCode = conn.getResponseCode();
			log.info("responseCode : " + responseCode);
		        
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
			String result = "";
			String line = "";
		        
			while ((line = br.readLine()) != null) {
				result += line;
			}
			log.info(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}