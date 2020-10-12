<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>RestFuLL | 로그인</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">
 
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    
    <!-- 자동완성 cdn -->
     <link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
	<!-- 소셜로그인 --> 
    <script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
    <script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v8.0&appId=253789402477033&autoLogAppEvents=1" nonce="6WkVrFTM"></script>
    <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>


    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/themify-icons.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    
    <!-- 로고 넣었는데 안변하네 몰랑~ -->
	<link rel="shortcut icon" href="img/favicon.ico"/>
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header Section Begin -->
    <header class="header-section">
        <div class="header-top">
            <div class="container">
                <div class="ht-right">
                <button type="button" class="login-panel">
                 <a href="./rs-loginpage.jsp">로그인</a>  
                 </button>
                </div>
            </div>
         </div>
         
 		<!-- 자동완성 자바스크립트 -->
        <script>
        $(function() {
        	
	        var searchSource = [
				"부산","부천","부경",
				"서울","서산","광주","전주","여수","여주","대전",
				"제주도","거제도","인천","양양","강릉"
	        ];
	        
	        $("#searchbox").autocomplete({
	        	source: searchSource,
	        	select : function(event, ui) {
	        		console.log(ui.item);
	        	},
	        	focus :function(event, ui) {
	        		return false;
	        	},
	        	minLength:1,
	        	autoFocus : true,
	        	classes : {
	        		"ui-autocomplete" : "highlight"
	        	},
	        	delay : 500,
	        	close : function(event) {
	        		console.log(event);
	        	}
	        	
	        });
  
   		 });
        </script>
         
        <div class="container">
                <div class="inner-header">
                    <div class="row">
                        <div class="col-lg-2 col-md-2">
                            <div class="logo">
                                <a href="./rs-mainpage.jsp">
                                    <img src="img/LOGOsmall.png" alt="">
                                </a>
                            </div>
                        </div>
                         <div class="col-lg-6 col-md-6 offset-lg-1 search-top">
                           <form name="searchform" method="post" action="#">
                                <div class="advanced-search">
                                    <div class="input-group">
                                        <input type="text" id="searchbox" placeholder="어느 지역으로 여행을 가시나요?">
                                        <button type="submit" id="submit" value="검색"><i class="ti-search"></i></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
        </div>
  
        <div class="nav-item">
            <div class="container">
                <nav class="nav-menu mobile-menu">
                    <ul>
                        <li class="active"><a href="./rs-mainpage.jsp">Home</a></li>
                        <li><a href="${pageContext.request.contextPath}/write_view">글작성</a></li>
                        <li><a href="#">여행코스작성</a></li>
                        <li><a href="./rs-Servicecenter.jsp">공지사항</a></li>
                        <li><a href="#">이벤트</a></li>
                        <li><a href="./rs-Servicecenter_faq.jsp">자주하는질문</a></li>
                        <!--<li><a href="#">로그인</a></li>-->
                    </ul>
                </nav>
                <div id="mobile-menu-wrap"></div>
            </div>
        </div>
    </header>
    <!-- Header End -->

    <!-- Login Section Begin -->
    <div class="register-login-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 offset-lg-4">
                    <div class="login-form">
                        <h2>Login</h2>
                        <form action="#">
                            <div class="group-input">
                                <label for="username">아이디</label>
                                <input type="text" id="username">
                            </div>
                            <div class="group-input">
                                <label for="pass">비밀번호</label>
                                <input type="text" id="pass">
                            </div>
                            <div class="group-input gi-check">
                                <div class="gi-more">
                                    <a href="#" class="forget-pass">ID/ PW 찾기</a>
                                </div>
                            </div>
                            <button type="submit" class="site-btn login-btn">로그인</button>
                        </form>
                        <div class="switch-login">
                            <a href="./rs-registerConfirm.jsp" class="or-login">회원가입</a>
                        </div>
                        
                        </div>
                        <!-- 네이버아이디로로그인 버튼 노출 영역 -->
                        <div id="naverIdLogin"></div>
                        <!-- //네이버아이디로로그인 버튼 노출 영역 -->

                        <!-- 네이버아디디로로그인 초기화 Script -->
                        <script type="text/javascript">
                            var naverLogin = new naver.LoginWithNaverId(
                                {
                                    clientId: "dge7CpG1Qo2Oov20L6NV",
                                    callbackUrl: "http://localhost:8282/restfull/resources/Content/rs-navercallback.jsp",
                                    isPopup: false, /* 팝업을 통한 연동처리 여부 */
                                    loginButton: {color: "green", type: 3, height: 45 , width: 240} /* 로그인 버튼의 타입을 지정 */
                                }
                            );

                            /* 설정정보를 초기화하고 연동을 준비 */
                            naverLogin.init();
                        </script>
                   
                        <!--페이스북로그인 버튼 노출영억-->
                       <div class="facebookbtn">
                            <div class="fb-login-button" data-size="large" data-button-type="login_with" data-layout="default" data-auto-logout-link="false" data-use-continue-as="false" data-width="240px" data-height="50px">
                            </div>
                        </div>
                        <div id="status"></div>
                        <!--페이스북로그인 script-end-->
                        
                        <!--페이스북로그인 script-start-->
                        <script>
                        function fbLogin() {
                            // 로그인 여부 체크
                            FB.getLoginStatus(function(response) {

                                if (response.status === 'connected') {
                                    FB.api('/me', function(res) {
                                        // 제일 마지막에 실행
                                        console.log("Success Login : " + response.name);
                                        // alert("Success Login : " + response.name);
                                    });
                                } else if (response.status === 'not_authorized') {
                                    // 사람은 Facebook에 로그인했지만 앱에는 로그인하지 않았습니다.
                                    alert('앱에 로그인해야 이용가능한 기능입니다.');
                                } else {
                                    // 그 사람은 Facebook에 로그인하지 않았으므로이 앱에 로그인했는지 여부는 확실하지 않습니다.
                                    alert('페이스북에 로그인해야 이용가능한 기능입니다.');
                                }
                            }, true); // 중복실행방지
                        }

                        window.fbAsyncInit = function() {
                            FB.init({
                                appId   : '253789402477033',
                                cookie  : true,
                                xfbml   : true,
                                version : 'v2.8'
                            });
                        };

                        (function(d, s, id) {
                            var js, fjs = d.getElementsByTagName(s)[0];
                            if (d.getElementById(id)) return;
                            js = d.createElement(s); js.id = id;
                            // ko_KR 을 en_US 로 바꾸면 영문으로 로그인버튼을 사용할 수 있어요.
                            js.src = "//connect.facebook.net/ko_KR/sdk.js";
                            fjs.parentNode.insertBefore(js, fjs);
                        }(document, 'script', 'facebook-jssdk'));

                        </script> 
                    
                        <!--카카오톡 로그인 버튼 노출영역 start-->
                        <div class="kakaobtn">
                            <a id="kakao-login-btn" href="javascript:loginWithKakao()">
                              <img
                                src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
                                width="240" height="45"/>
                            </a>
                        </div>
                         <!--카카오톡 로그인 버튼 노출영역 end-->

                         <!--카카오톡 로그인 script start-->
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
                        <!--카카오톡 로그인 script end-->
                   
                    
                </div>
            </div>
        </div>
    </div>
    <!-- Register Form Section End -->


    <!-- Footer Section Begin -->
    <footer class="footer-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="footer-left">
                        <div class="footer-logo">
                            <a href="#"><img src="img/LOGOsmall.png" alt=""></a>
                        </div>
                        <ul>
                            <li>Address: 60-49 Road 11378 New York</li>
                            <li>Phone: +65 11.188.888</li>
                            <li>Email: hello.colorlib@gmail.com</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-3 offset-lg-1">
                    <div class="footer-widget">
                        <h5>Information</h5>
                        <ul>
                          	<li><a href="./rs-comapnyinfo.jsp">회사소개</a></li>
                            <li><a href="#">이용약관</a></li>
                            <li><a href="#">여행지등록</a></li>
                            <li><a href="./rs-Servicecenter.jsp">고객센터</a></li>
                            <li><a href="#">사이트맵</a></li>
                            <li><a href="./rs-Servicecenter_personal.jsp"><b>개인정보처리방침</b></a></li>
                            <li><a href="#">위치기반서비스이용약관</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-lg-4">
                    <div class="newslatter-item">
                        <h5>새로운 소식 받기</h5>
                        <p>Get E-mail updates about our latest shop and special offers.</p>
                        <form action="#" class="subscribe-form">
                            <input type="text" placeholder="Enter Your Mail">
                            <button type="button">Subscribe</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="copyright-reserved">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="copyright-text">
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> 2020 RestFuLL. All rights reserved.
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        </div>
                        <div class="payment-pic">
                            <img src="img/payment-method.png" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- Footer Section End -->


    <!-- Js Plugins -->
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.countdown.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery.zoom.min.js"></script>
    <script src="js/jquery.dd.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>

</body>

</html>