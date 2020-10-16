package bit.project.restfull.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bit.project.restfull.controller.NaverLoginBO;
import bit.project.restfull.mapper.LoginMapper;
import bit.project.restfull.mapper.SnsMapper;
import bit.project.restfull.vo.SnsVO;
import bit.project.restfull.vo.UserVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor
@Service
public class NaverService {
	
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private SnsMapper snsMapper;
	@Autowired
	private LoginMapper loginMapper;
	
	@Inject
	private BCryptPasswordEncoder passEncoder; 
	

	public HashMap<String, String> getcallback (String code, String state, HttpSession session) throws  IOException, ParseException  {
		
		 HashMap<String, String> callback = new HashMap<>();
		
		try {
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
		//1. 로그인 사용자 정보를 읽어온다.
		log.info("전apiResult : "+ apiResult);
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		log.info("apiResult : "+ apiResult);
		
		//2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
		//3. 데이터 파싱
		//Top레벨 단계 _response 파싱
		JSONObject properties = (JSONObject) jsonObj.get("response");
		//response의 nickname값 파싱
		String sns_id = (String) properties.get("id");
		log.info("id"+sns_id);
		
		String sns_nickname = (String) properties.get("name");
		log.info("name"+sns_nickname);
		
		callback.put("sns_id", sns_id);
		callback.put("sns_nickname", sns_nickname);
		 
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return callback;
		
	}

	public void addsnsN(HashMap<String, String> callback) {
		
			SnsVO snsVO = new SnsVO();
			
			UserVO userVO = new UserVO();
			

			log.info(callback.get("sns_id")+"@k");
			log.info(callback.get("sns_id"));
			log.info(callback.get("sns_nickname"));
			
			
			//insertID부분
			userVO.setMember_id(callback.get("sns_id")+"@n");
			
			String pw = "null";
			String encode = passEncoder.encode(pw);
			userVO.setPw(encode);
			
			userVO.setName(callback.get("sns_nickname"));
			userVO.setGrade_name("뚜벅이");
			userVO.setAuthority_name("ROLE_USER");
			
			log.info(userVO.getMember_id());
			log.info(userVO.getPw());
			log.info(userVO.getName());
			log.info(userVO.getAuthority_name());
			
			//insertSNS부분 
			snsVO.setMember_id(callback.get("sns_id")+"@n");
			snsVO.setMember_id(callback.get("sns_id"));
			snsVO.setSns_type("Naver");
			snsVO.setMember_id(callback.get("sns_nickname"));
			
			log.info(snsVO.getMember_id());  //식별키 + @k
			log.info(snsVO.getSns_id());		//식별키
			log.info(snsVO.getSns_type());	
			log.info(snsVO.getSns_nickname());	//이름
			
			
			
			String member_id = userVO.getMember_id();
			
			if(loginMapper.idChk(member_id)==0) {
				snsMapper.insertSns(snsVO);
				snsMapper.insertID(userVO);
				log.info("네이버 값 넣기 성공");
			}else  {
				  log.info("member_id :" + member_id + "네이버 아디 이미 있음.");
			}

	}
	
	
	
}
 