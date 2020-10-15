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
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bit.project.restfull.mapper.LoginMapper;
import bit.project.restfull.mapper.SnsMapper;
import bit.project.restfull.vo.SnsVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor
@Service
public class KakaoService {
	
	@Autowired
	private LoginMapper loginMapper;
	
	
	@Autowired
	private SnsMapper snsMapper;
	
	/* 토큰 값 얻기 */
	public String getAccessToken(String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=8c8d010971d8629ec3188f9ed097f73b"); // 본인이 발급받은 key
			sb.append("&redirect_uri=http://localhost:8282/restfull/kakaologin"); // 본인이 설정해 놓은 경로
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			log.info("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			log.info("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			log.info("access_token : " + access_Token);
			log.info("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return access_Token;
	}

	
	/* 카카오 유저 정보값 추출 */ 
	  public HashMap<String, String> getUserInfo (String access_Token) {

          //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
          HashMap<String, String> userInfo = new HashMap<>();
          String reqURL = "https://kapi.kakao.com/v2/user/me";
          try {
              URL url = new URL(reqURL);
              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
              conn.setRequestMethod("POST");

              //    요청에 필요한 Header에 포함될 내용
              conn.setRequestProperty("Authorization", "Bearer " + access_Token);

              
              int responseCode = conn.getResponseCode();
              log.info("responseCode : " + responseCode);

              BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

              String line = "";
              String result = "";

              while ((line = br.readLine()) != null) {
                  result += line;
              }
              
              log.info("response body : " + result);
              
              String decoded=new String(result);
              
              log.info(decoded);
              JsonParser parser = new JsonParser();
              JsonElement element = parser.parse(result);

              
              
              JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();

              String sns_id =  element.getAsJsonObject().get("id").getAsString();
              String sns_nickname = properties.getAsJsonObject().get("nickname").getAsString();
             

              userInfo.put("sns_nickname", sns_nickname);
              userInfo.put("sns_id", sns_id);
              
              

          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }

          return userInfo;
      }
	  
	  
	  public void addSNS(HashMap<String, String> userInfo) {
		  
		  SnsVO snsVO = new SnsVO();
		  
		  log.info(userInfo.get("sns_id")+"@k");
		  log.info(userInfo.get("sns_id"));
		  log.info(userInfo.get("sns_nickname"));
		  
		  snsVO.setMember_id(userInfo.get("sns_id")+"@k");
		  snsVO.setSns_id(userInfo.get("sns_id"));
		  snsVO.setSns_type("Kakao");
		  snsVO.setSns_nickname(userInfo.get("sns_nickname"));
		  
		  log.info(snsVO.getMember_id());  //식별자 키 + @k
		  log.info(snsVO.getSns_id());		//식별자 키
		  log.info(snsVO.getSns_type());	
		  log.info(snsVO.getSns_nickname());	//이름
		  
		  String member_id = snsVO.getMember_id();
		  
		  
		  
		  if(loginMapper.idChk(member_id)== 0) {
			  
		  	  snsMapper.insertID(snsVO.getMember_id());
			  snsMapper.insertSns(snsVO);
		  }else {
			  log.info("member_id :" + member_id + "는 이미 가입되어 있습니다.");
		  }

		  
	  }
	  



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
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		}





}
