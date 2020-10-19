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
    <title>RestFuLL | 메인페이지</title>
    <%@ include file="/WEB-INF/include/plugins.jspf"%>
</head>

<body>
    <%@ include file="/WEB-INF/include/js-header.jsp"%>
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
                        <form action="${pageContext.request.contextPath}/register" name="userInfo" method="post" onsubmit="return checkValue()">
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
    

  <%@ include file="/WEB-INF/include/js-footer.jsp"%>


</body>

</html>