package bit.project.restfull.service;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;

import bit.project.restfull.mapper.PaymentMapper;
import bit.project.restfull.vo.PaymentVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor
@Service
public class PaymentService {

	/*
	 * 인증 API 
	 * POST /users/getToken 
	 * 
	 * 결제관련 기본 API 
	 * GET /payments/{imp_uid} 
	 * GET /payments/status/{payment_status} 
	 * POST /payments/cancel 
	 * POST /subscribe/payments/onetime 
	 * POST /subscribe/payments/again 
	 * POST /subscribe/payments/schedule 
	 * POST /subscribe/payments/unschedule
	 * 
	 */
	
	public String getToken(HttpServletRequest request, HttpServletResponse response, JsonObject json, String requestURL) {
			 
		String access_Token = "";

		try {
			URL url = new URL(requestURL);
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		         
		    //request 형식 설정
		    conn.setRequestMethod("POST");
		    //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		    conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		         
		    conn.setDoInput(true);
		    conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
				
			//request
		    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		    
		    bw.write(json.toString());
		    bw.flush();
		    bw.close();
		    
		    int responseCode = conn.getResponseCode();
	        log.info("responseCode : " + responseCode);

		    if (responseCode == 200) {
			    //리턴된 결과 읽기
			    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	            
	            String inputline = null;
	            String result = "";

	            while((inputline = br.readLine()) != null){
	            	result += inputline;
	            }
	            
	            log.info("response body : " + result);
	            
	            ////////////////////////////
	            JsonParser parser = new JsonParser();
	            JsonElement element = parser.parse(result);

	            
	            access_Token = element.getAsJsonObject().get("access_token").getAsString();
	            log.info("access_token : " + access_Token);
	            br.close();
	            bw.close();
	        }
		} catch (IOException e) {
        	log.info("RestCall Fail : " + e.getMessage());
	    }
		return access_Token;
    }
	
}

