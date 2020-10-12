<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>LoginPage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
    <script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v8.0&appId=253789402477033&autoLogAppEvents=1" nonce="6WkVrFTM"></script>
    <script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
    <link rel="stylesheet" type = "text/css" href="${pageContext.request.contextPath}/resources/CSS/LoginPage.css">
   
    
</head>

 <div class="container">
        <!--로고사진-->
         <div class="logo">
            <a href="/"><img src="D:\sts-bundle\restFul\src\main\resources\picture\LOGOsmall.png"/></a>
        </div>


      <c:url value="/login" var="loginUrl" />
      <p>"${loginUrl}"</p>
      <form:form name="f" action="${loginUrl}" method="POST"> 
    <c:if test="${param.error != null}">
        <p>아이디와 비밀번호가 잘못되었습니다.</p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>로그아웃 하였습니다.</p>
    </c:if>  
    
    
    
                <!--id,pw 입력-->               
	<div class="form-group form-group-lg" style="padding-top:10px; padding-bottom:7px;">
    <div class="col-md-4 aaa align-self-auto" >
      <label for="id">아이디 (ID):</label>
      <input type="id" class="form-control" id="member_id" placeholder="아이디를 입력해주세요" name="member_id">
      </div>
     </div>
     
     <div class="form-group form-group-lg" style="padding-bottom:7px;">
        <div class=" col-md-4 aaa align-self-auto">
      <label for="pwd">비밀번호 (Password):</label>
      <input type="password" class="form-control" id="pw" placeholder="비밀번호를 입력해주세요" name="pw">
      
    	</div>
    </div>
    
    <div class="form-group form-check">
      <label class="form-check-label">
        <a href="<c:url value="join" />">Sign Up</a>
      </label>
    </div>

    			<div class="form-group">        
                  <div class=" col-md-4 aaa align-self-auto">
                    <button type="submit" class="btn btn-custom btn-block btn-lg">로그인</button>
                  </div>
                </div>
      </form:form>

<hr class="col-md-5" width="50%">


			<div width="50%" class="form-center" > 
                   <a href="#"><b>ID/PW찾기</b></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    |
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="join"><b>회원가입</b></a>  
                </div>
                
                </form>

    <br><br>
    <label for="email">간편 로그인 (Login with SNS):</label>
    
	<div id="naverIdLogin">
		<a href="index.html" class="btn btn-success btn-user btn-block">
			<i class="fab fa-facebook-f fa-fw"></i><strong> 네이버 로그인 </strong>
		</a>
	</div>
	<script type="text/javascript">
                    var naverLogin = new naver.LoginWithNaverId(
                        {
                            clientId: "dge7CpG1Qo2Oov20L6NV",
                            callbackUrl: "http://localhost:8282/restfull/resources/Callback.html",
                            isPopup: false, /* 팝업을 통한 연동처리 여부 */
                            loginButton: {color: "green", type: 3, height: 45 , width: 240} /* 로그인 버튼의 타입을 지정 */
                        }
                    );

                    /* 설정정보를 초기화하고 연동을 준비 */
                    naverLogin.init();
        		</script>
        		
        		<div class="facebookbtn">
                <div class="fb-login-button" data-size="large" data-button-type="login_with" data-layout="default" data-auto-logout-link="false" data-use-continue-as="false" data-width="240px" data-height="50px">
                </div>
            </div>

            <div id="status">
            </div>
            
        <!--페이스북로그인 script-->
            <script>
                function statusChangeCallback(response) {  // Called with the results from FB.getLoginStatus().
                console.log('statusChangeCallback');
                console.log(response);                   // The current login status of the person.
                if (response.status === 'connected') {   // Logged into your webpage and Facebook.
                  testAPI();  
                } else {                                 // Not logged into your webpage or we are unable to tell.
                }
              }


              function checkLoginState() {               // Called when a person is finished with the Login Button.
                FB.getLoginStatus(function(response) {   // See the onlogin handler
                  statusChangeCallback(response);
                });
              }


              window.fbAsyncInit = function() {
                FB.init({
                  appId      : '253789402477033',
                  cookie     : true,                     // Enable cookies to allow the server to access the session.
                  xfbml      : true,                     // Parse social plugins on this webpage.
                  version    : 'v8.0'           // Use this Graph API version for this call.
                });


                FB.getLoginStatus(function(response) {   // Called after the JS SDK has been initialized.
                  statusChangeCallback(response);        // Returns the login status.
                });
              };

              function testAPI() {                      // Testing Graph API after login.  See statusChangeCallback() for when this call is made.
                console.log('Welcome!  Fetching your information.... ');
                FB.api('/me', function(response) {
                  console.log('Successful login for: ' + response.name);
                  document.getElementById('status').innerHTML =
                    'Thanks for logging in, ' + response.name + '!';
                });
              } 
                
        </script>
        
        <div class="kakaobtn">
            <a id="kakao-login-btn" href="javascript:loginWithKakao()">
              <img
                src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
                width="240" height="45"/>
            </a>
        </div>
        
        <script type="text/javascript">
          function loginWithKakao() {
            Kakao.init('3791ebca9ab23cd8a51a5dbccfdb596a');
            Kakao.Auth.createLoginButton({
                container: '#kakao-login-btn',
                success: function(authObj) {
                    alert(JSON.stringify(authObj))
                },
                fail: function(err) {
                    alert(JSON.stringify(err))
              },
            })
          }
        </script>
        </div>
	
   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 

  
<footer style="font-size:12px; color:black;">
        <nav style="padding-top: 50px; margin-left:15px;">
            <a href="#">회사소개 </a>| &nbsp;
            <a href="#">이용약관 </a> |&nbsp;
            <a href="#">운영정책 </a> |&nbsp;
            <a href="#">여행지등록 </a>|&nbsp;
            <a href="#">고객센터 </a>

        </nav>
        <hr>
        <div class="row-fluid">
            <div class="span12">
                <div class="span8" style="margin-left:10px;">
                    <a href="#"><b>개인정보처리방침</b> </a>
                    |
                    <a href="#">청소년보호방침</a>
                </div>
                <div class="span4">
                    <p class="muted pull-right"> © 2020 RestFuLL. All rights reserved</p>
                </div>
            </div>
        </div>
     </footer>

</body>


</html>






















