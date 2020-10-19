//package bit.project.restfull.service;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//
//import javax.inject.Inject;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//import bit.project.restfull.mapper.LoginMapper;
//import bit.project.restfull.mapper.SnsMapper;
//import bit.project.restfull.vo.SnsVO;
//import bit.project.restfull.vo.UserVO;
//import lombok.NoArgsConstructor;
//import lombok.extern.log4j.Log4j;
//
//@Log4j
//@NoArgsConstructor
//@Service
//public class SocialLoginService {
//	
//	@Autowired
//	private LoginMapper loginMapper;
//	
//	@Inject
//	private BCryptPasswordEncoder passEncoder; 
//	
//	@Autowired
//	private SnsMapper snsMapper;
//	
//	public String getAccessToken(String authorize_code) {
//		String access_Token = "";
//		String refresh_Token = "";
//		String reqURL = "https://kauth.kakao.com/oauth/token";
//
//		try {
//			URL url = new URL(reqURL);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//			// POST ��û�� ���� �⺻���� false�� setDoOutput�� true��
//			conn.setRequestMethod("POST");
//			conn.setDoOutput(true);
//
//			// POST ��û�� �ʿ�� �䱸�ϴ� �Ķ���� ��Ʈ���� ���� ����
//			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//			StringBuilder sb = new StringBuilder();
//			sb.append("grant_type=authorization_code");
//			sb.append("&client_id=8c8d010971d8629ec3188f9ed097f73b"); // ������ �߱޹��� key
//			sb.append("&redirect_uri=http://localhost:8282/restfull/kakaologin"); // ������ ������ ���� ���
//			sb.append("&code=" + authorize_code);
//			bw.write(sb.toString());
//			bw.flush();
//
//			// ��� �ڵ尡 200�̶�� ����
//			int responseCode = conn.getResponseCode();
//			log.info("responseCode : " + responseCode);
//
//			// ��û�� ���� ���� JSONŸ���� Response �޼��� �о����
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String line = "";
//			String result = "";
//
//			while ((line = br.readLine()) != null) {
//				result += line;
//			}
//			log.info("response body : " + result);
//
//			// Gson ���̺귯���� ���Ե� Ŭ������ JSON�Ľ� ��ü ����
//			JsonParser parser = new JsonParser();
//			JsonElement element = parser.parse(result);
//
//			access_Token = element.getAsJsonObject().get("access_token").getAsString();
//			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
//			log.info("access_token : " + access_Token);
//			log.info("refresh_token : " + refresh_Token);
//
//			br.close();
//			bw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return access_Token;
//	}
//
//	
//	/* īī�� ���� ������ ���� */ 
//	  public HashMap<String, String> getUserInfo (String access_Token) {
//
//          // 
//          HashMap<String, String> userInfo = new HashMap<>();
//          String reqURL = "https://kapi.kakao.com/v2/user/me";
//          try {
//              URL url = new URL(reqURL);
//              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//              conn.setRequestMethod("POST");
//
//              //    ��û�� �ʿ��� Header�� ���Ե� ����
//              conn.setRequestProperty("Authorization", "Bearer " + access_Token);
//
//              
//              int responseCode = conn.getResponseCode();
//              log.info("responseCode : " + responseCode);
//
//              BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
//
//              String line = "";
//              String result = "";
//
//              while ((line = br.readLine()) != null) {
//                  result += line;
//              }
//              
//              log.info("response body : " + result);
//              
//              String decoded=new String(result);
//              
//              log.info(decoded);
//              JsonParser parser = new JsonParser();
//              JsonElement element = parser.parse(result);
//
//              
//              
//              JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//
//              String sns_id =  element.getAsJsonObject().get("id").getAsString();
//              String sns_nickname = properties.getAsJsonObject().get("nickname").getAsString();
//             
//
//              userInfo.put("sns_nickname", sns_nickname);
//              userInfo.put("sns_id", sns_id);
//              
//              
//
//          } catch (IOException e) {
//              // TODO Auto-generated catch block
//              e.printStackTrace();
//          }
//
//          return userInfo;
//      }
//	  
//	  
//	  public void addSNS(HashMap<String, String> userInfo) {
//		  
//		  SnsVO snsVO = new SnsVO();
//		  
//		  UserVO userVO = new UserVO();
//		  
//		  log.info(userInfo.get("sns_id")+"@k");
//		  log.info(userInfo.get("sns_id"));
//		  log.info(userInfo.get("sns_nickname"));
//
//		  
//		  //insertID부분
//		  userVO.setMember_id(userInfo.get("sns_id")+"@k");
//		  
//		  String pw = "null"; 
//		  String encode = passEncoder.encode(pw);
//		  userVO.setPw(encode);
//		  
//		  userVO.setName(userInfo.get("sns_nickname"));
//		  userVO.setGrade_name("뚜벅이");
//		  userVO.setAuthority_name("ROLE_USER");
//		  
//		  
//		  log.info(userVO.getPw());
//		  log.info(userVO.getAuthority_name());
//		  log.info(userVO.getAuthorities());
//		  
//		  snsVO.setMember_id(userInfo.get("sns_id")+"@k");
//		  snsVO.setSns_id(userInfo.get("sns_id"));
//		  snsVO.setSns_type("Kakao");
//		  snsVO.setSns_nickname(userInfo.get("sns_nickname"));
//		  
//		  log.info(snsVO.getMember_id());  //식별키 + @k
//		  log.info(snsVO.getSns_id());		//식별키
//		  log.info(snsVO.getSns_type());	
//		  log.info(snsVO.getSns_nickname());	//이름
//		  
//
//		  
//		  String member_id = userVO.getMember_id();
//		   
//		  if(loginMapper.idChk(member_id)== 0) {
//		  	  snsMapper.insertID(userVO);
//			  snsMapper.insertSns(snsVO);
//			  log.info("카카오 값 넣기 성공");
//		  }else {
//			  log.info("member_id :" + member_id + "이미 있음.");
//		  }
//
//		  
//	  }
//	  
//	  public void kakaoLogout(String access_Token) {
//		    String reqURL = "https://kapi.kakao.com/v1/user/logout";
//		    try {
//		        URL url = new URL(reqURL);
//		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		        conn.setRequestMethod("POST");
//		        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
//		        
//		        int responseCode = conn.getResponseCode();
//		        log.info("responseCode : " + responseCode);
//		        
//		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		        
//		        String result = "";
//		        String line = "";
//		        
//		        while ((line = br.readLine()) != null) {
//		            result += line;
//		        }
//		        log.info(result);
//		    } catch (IOException e) {
//		        // TODO Auto-generated catch block
//		        e.printStackTrace();
//		    }
//		}
//
//
//		@Autowired
//		private SnsMapper snsMapper;
//		
//		@Inject
//		private BCryptPasswordEncoder passEncoder;
//		
//		public void addsnsN( String id, String name) {
//				UserVO uservo= new UserVO();
//				
//				log.info("id- "+id+"@n");
//				log.info("name- "+name);
//				
//				String pw = "12341234"; 
//				
//				String encode = passEncoder.encode(pw);
//				  
//				uservo.setMember_id(id+"@n");
//				uservo.setPw(encode);
//				uservo.setName(name);
//				uservo.setGrade_name("뚜벅이");
//				uservo.setAuthority_name("ROLE_USER");
//				uservo.setLogin_type("naver");
//				log.info("uservo 셋팅완료");
//				snsMapper.insertID(uservo);
//				log.info("uservo 삽입완료");
//		}
//}