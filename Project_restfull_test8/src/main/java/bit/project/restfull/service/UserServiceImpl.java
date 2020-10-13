package bit.project.restfull.service;



import java.io.PrintWriter;
import java.util.List;



import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import bit.project.restfull.mapper.LoginMapper;
import bit.project.restfull.vo.PagingVO;
import bit.project.restfull.vo.UserVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	@Inject
	private BCryptPasswordEncoder passEncoder; 
	@Inject
	private LoginMapper loginMapper;

	public void addUser(UserVO userVO){ 
		
		String pw = userVO.getPw(); 
		
		String encode = passEncoder.encode(pw);
		  
		userVO.setPw(encode);
		loginMapper.insertUser(userVO); 
	} 
	
	public void modifyUser(UserVO userVO) {
		
		String pw = userVO.getPw(); 
		log.info(pw);
		String encode = passEncoder.encode(pw);
		  
		userVO.setPw(encode);
		
		loginMapper.modifyUser(userVO);; 
		
		log.info(userVO);
		
	}
	 
	
	public void userDelete(UserVO userVO) {
		
		String member_id = userVO.getMember_id();
		log.info(member_id);
		loginMapper.delMember(member_id);
		
	}
	
	
	public String getEncodePassword(String pw) {
		log.info("pw"+pw);
		return passEncoder.encode(pw);
	}
	
	public int getUser(String member_id) {
		return loginMapper.idChk(member_id);
	}

	@Override
	public void adminModifyUser(UserVO userVO) {
		String pw = userVO.getPw(); 
		log.info(pw);
		String encode = passEncoder.encode(pw);
		
		userVO.setPw(encode);
		loginMapper.adminModifyUser(userVO);; 
	}

	@Override
	public int countMember() {
		return loginMapper.countMember();
	}

	@Override
	public List<UserVO> userList(PagingVO pagingVO){
		return loginMapper.userList(pagingVO);
	}

	@Override
	public UserVO getUserVO(String member_id) {
		return loginMapper.getUserVO(member_id);
	}
	
	@Override 
	public String findID(HttpServletResponse response, String email) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = loginMapper.findID(email);
		
		if (id == null) {
			out.println("<script>");
			out.println("alert('���Ե� ���̵� �����ϴ�.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return null;
		} else {
			return id;
		}
	
	}

	@Override
	public void findPW(HttpServletResponse response, UserVO userVO) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// ���̵� ������
		
		if(loginMapper.idChk(userVO.getMember_id()) == 0) {
			out.print("���̵� �����ϴ�.");
			out.close();
		}
		// ���Կ� ����� �̸����� �ƴϸ�
		else if(!userVO.getEmail().equals(loginMapper.readUser(userVO.getMember_id()).getEmail())) {
			out.print("�߸��� �̸��� �Դϴ�.");
			out.close();
		}else {
			// �ӽ� ��й�ȣ ����
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			
			userVO.setPw(pw);
			System.out.println(pw);
			sendMail(userVO, "findPW");
			// ��й�ȣ ����
			
			String encode = passEncoder.encode(pw);
			
			userVO.setPw(encode);
			
			loginMapper.findPW(userVO);
			// ��й�ȣ ���� ���� �߼�
			
			out.print("�̸��Ϸ� �ӽ� ��й�ȣ�� �߼��Ͽ����ϴ�.");
			out.close();
		}
	}
	

	@Override
	public void sendMail(UserVO userVO, String div) {
		// Mail Server ����
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "justicia0310@gmail.com";
		String hostSMTPpwd = ""; //���⸸ ä��� ������ (��� �־��ִ°�)

		// ������ ��� EMail, ����, ����
		String fromEmail = "restFullAdmin@gmail.com";
		String fromName = "RestFull ������";
		String subject = "";
		String msg = "";
		
		if(div.equals("findPW")) {
			subject = "RestFull �α��� �ӽ� ��й�ȣ �Դϴ�.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += userVO.getMember_id() + "���� �ӽ� ��й�ȣ �Դϴ�. ��й�ȣ�� �����Ͽ� ����ϼ���.</h3>";
			msg += "<p>�ӽ� ��й�ȣ : ";
			msg += userVO.getPw() + "</p></div>";
		}
		// �޴� ��� E-Mail �ּ�
		String mail = userVO.getEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("���Ϲ߼� ���� : " + e);
		}
	}

	@Override
	public int idChk(String member_id) {
		return loginMapper.idChk(member_id);
	}
	
}
