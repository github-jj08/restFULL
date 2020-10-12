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
    <title>RestFuLL | 회원가입</title>
    
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">
 
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    
    <!-- 자동완성 cdn -->
     <link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 


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

    <script>
    
        function checkValue() {
            var form = document.userInfo;
            
            if(!form.id.value) {
                alert("아이디를 입력하세요.");
                return false;
            }
            if(form.idDuplication.value != "idCheck"){
                alert("아이디 중복체크를 해주세요.");
                return false;
            }
            if(!form.password.value) {
                alert("비밀번호를 입력하세요.");
                return false;
            }
            // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
            if(form.password.value != form.passwordcheck.value ){
                alert("비밀번호를 동일하게 입력하세요.");
                return false;
            }
            
            if(!form.name.value) {
                alert("이름을 입력하세요.");
                return false;
            }
            if(form.birthyy.value == "년") {
                alert("년도를 선택하세요.");
                return  false;
            }
            if(form.birthmm.value == "월") {
                alert("월을 선택하세요.");
                return  false;
            }
            if(form.birthdd.value == "일") {
                alert("일을 선택하세요.");
                return  false;
            }
            if(!form.mail.value) {
                alert("메일 주소를 입력하세요.");
                return false;
            }
            if(!form.phone.value){
                alert("전화번호를 입력하세요.");
                return false;
            }
            
            if(isNaN(form.phone.value)){
                alert("전화번호는 - 제외한 숫자만 입력해주세요.");
                return false;
            }
            

        }

    </script>
    
    
    

    <!-- Register Section Begin -->
    <div class="register-login-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 offset-lg-4">
                    <div class="register-form">
                        <h2>Register</h2>
                        <form action="./rs-registerFinish.jsp" name="userInfo" method="post" onsubmit="return checkValue()">
                           <!--아이디 입력창-->
                            <div class="group-input" id="userid">
                                <label for="username">아이디</label>
                                <input type="text" id="username" name="id" maxlength="50" onkeydown="inputIdChk()" >
                                <input type="button"  class="con-id" value="중복확인" onclick="openIdChk()">
                                <input type="hidden" name="idDuplication" value="idUncheck">
                            </div>
                            <!--비밀번호 입력창-->
                            <div class="group-input">
                                <label for="password">비밀번호</label>
                                <input type="password" id="pass" name="password" maxlength="50" placeholder="비밀번호 입력">
                            </div>
                            <!--비밀번호 재확인 입력창-->
                            <div class="group-input">
                                <label for="con-password">비밀번호 확인</label>
                                <input type="password" id="con-pass" name="passwordcheck" maxlength="50" placeholder="비밀번호 재입력">
                            </div>
                            <!--이름 입력창-->
                             <div class="group-input">
                                <label for="name">이름</label>
                                <input type="text" id="name" name="name">
                            </div>
                            
                             <!--생년월일 입력창-->
                            <div class="group-input">
                            <label for="birth">생년월일</label>
                                <div class="row">
                                    <div class="col">
                                        <label for="year" class="bd">년도</label>
                                        <select class="form-control" id="year" name="birthyy" >
                                            <option>년</option>
                                            <option>1980</option>
                                            <option>1981</option>
                                            <option>1982</option>
                                            <option>1983</option>
                                            <option>1984</option>
                                            <option>1985</option>
                                            <option>1986</option>
                                            <option>1987</option>
                                            <option>1988</option>
                                            <option>1989</option>
                                            <option>1990</option>
                                            <option>1991</option>
                                            <option>1992</option>
                                            <option>1993</option>
                                            <option>1994</option>
                                            <option>1995</option>
                                            <option>1996</option>
                                            <option>1997</option>
                                            <option>1998</option>
                                            <option>1999</option>
                                            <option>2000</option>
                                            <option>2001</option>
                                            <option>2002</option>
                                            <option>2003</option>
                                            <option>2004</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <label for="month" class="bd" >월</label>
                                        <select class="form-control" id="month" name="birthmm">
                                           <option>월</option>
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                            <option>6</option>
                                            <option>7</option>
                                            <option>8</option>
                                            <option>9</option>
                                            <option>10</option>
                                            <option>11</option>
                                            <option>12</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <label for="date" class="bd">일</label>
                                        <select class="form-control" id="date" name="birthdd">
                                           <option>일</option>
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                            <option>6</option>
                                            <option>7</option>
                                            <option>8</option>
                                            <option>9</option>
                                            <option>10</option>
                                            <option>11</option>
                                            <option>12</option>
                                            <option>13</option>
                                            <option>14</option>
                                            <option>15</option>
                                            <option>16</option>
                                            <option>17</option>
                                            <option>18</option>
                                            <option>19</option>
                                            <option>20</option>
                                            <option>21</option>
                                            <option>22</option>
                                            <option>23</option>
                                            <option>24</option>
                                            <option>25</option>
                                            <option>26</option>
                                            <option>27</option>
                                            <option>28</option>
                                            <option>29</option>
                                            <option>30</option>
                                            <option>31</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            
                            <!--성별 입력창-->
                                <div class="gender">
                                <label for="gender" style="font">성별</label>
                                <div class="row">
                                    <div class="form-check col">
                                        <input class="form-check-input" type="radio" name="gender" id="exampleRadios1" value="man" checked>
                                        <label class="form-check-label" for="exampleRadios1">
                                            남자
                                        </label>
                                    </div>
                                    <div class="form-check col">
                                        <input class="form-check-input" type="radio" name="gender" id="exampleRadios2" value="woman">
                                        <label class="form-check-label" for="exampleRadios2">
                                            여자
                                        </label>
                                    </div>
                                </div>
                                </div>
                            
                                
                                
                                
                            <!--이메일 입력창-->
                            <div class="group-input">
                                <label for="email">이메일</label>
                                <input type="text" id="email" name="mail" aria-describedby="emailHelp" placeholder="이메일주소 입력">
                                <small id="emailHelp" class="form-text text-muted">이메일 공유하지 마세요~</small>
                            </div>
                            
                            <!--휴대폰 번호 입력창-->
                            <div class="group-input">
                                <label for="mobilenum">핸드폰 번호</label>
                                <div class="mbnum">
                                    <input type="text" name="phone" id="mobilenum">
                                    <input type="text" name="phone" id="con-mobilenum">
                                    <button type="button" class="con-btn">인증요청</button>
                                </div>  
                            </div>
                            <input type="submit" class="site-btn register-btn" value="회원가입"/>
                        </form>
                    </div>
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