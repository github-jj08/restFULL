<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>RestFuLL | 고객센터</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

	
</head>
<body>
<%@ include file="/WEB-INF/include/js-header.jsp"%>

   <div class="text-center"><h2>약관 및 정책</h2></div>
   <hr>

 <!-- Blog Section Begin -->
    <section class="blog-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-8 order-1 order-lg-1">
                    <div class="blog-sidebar">
                        <div class="blog-catagory">
                            <h4>고객센터</h4>
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/notice/<c:out value='2'/>">공지사항</a></li>
                                <li><a href="${pageContext.request.contextPath}/notice/<c:out value='5'/>">이벤트</a></li>
                                <li><a href="${pageContext.request.contextPath}/FAQ">자주하는질문</a></li>
                                <li><a href="${pageContext.request.contextPath}/personal">약관 및 정책</a></li>

                            </ul>
                        </div>
                       
                      
                    </div>
                </div>
                
                <!--개인정보처리방침 start-->
                <div class="col-lg-9 order-2 order-lg-2">
                    <div class="faq-accordin">
                        <div class="accordion" id="accordionExample">
                          <h4><b>개인정보처리방침</b></h4>
                          <br/>
                            <div class="personal">
                              	<p><b>RestFuLL</b>('http://www.RestFuLL.com'이하  'RestFuLL')은(는) 개인정보보호법에 따라 이용자의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 이용자의 고충을 원활하게 처리할 수 있도록 다음과 같은 처리방침을 두고 있습니다.</p>
                              	<b>RestFuLL</b>('RestFuLL') 은(는) 회사는 개인정보처리방침을 개정하는 경우 웹사이트 공지사항(또는 개별공지)을 통하여 공지할 것입니다.
                              	<br/><br/>○ 본 방침은 2020년 9월 23일부터 시행됩니다.
                              	 <br/><br/> <br/><br/>
                          </div>
                          
                          <!--1번-->
                           <div class="card">
                            <div class="card-heading">
                                <a class="active" data-toggle="collapse" data-target="#collapseOne">
                                    <strong>1. 개인정보의 처리 목적 </strong>
                                </a>
                            </div>
                            <div id="collapseOne" class="collapse" data-parent="#accordionExample">
                                <div class="card-body">
                                   <b>RestFuLL</b>('http://www.RestFuLL.com'이하  'RestFuLL')은(는) 개인정보를 다음의 목적을 위해 처리합니다. 처리한 개인정보는 다음의 목적이외의 용도로는 사용되지 않으며 이용 목적이 변경될 시에는 사전동의를 구할 예정입니다.
                                   <ul class="list_indent2 mgt10"><br/>
                                       가. 홈페이지 회원가입 및 관리
                                       회원 가입의사 확인, 회원제 서비스 제공에 따른 본인 식별·인증, 회원자격 유지·관리 등을 목적으로 개인정보를 처리합니다.
                                       <br/><br/>
                                       나. 마케팅 및 광고에의 활용
                                       이벤트 및 광고성 정보 제공 및 참여기회 제공  등을 목적으로 개인정보를 처리합니다.
                                       <br/>
                                   </ul>
                                </div>
                            </div>
                           </div>
                           
                           <!--2번-->
                            <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseTwo">
                                        <strong>2. 개인정보 파일 현황</strong>
                                    </a>
                                </div>
                                <div id="collapseTwo" class="collapse" data-parent="#accordionExample">
                                    <div class="card-body">
                                       <ul class='list_indent2 mgt10'><li class='tt'><b>1. 개인정보 파일명 : RestFuLL</b></li><li>개인정보 항목 : 이메일, 휴대전화번호, 비밀번호, 로그인ID, 이름</li><li>수집방법 : 홈페이지</li><li>보유근거 : 홈페이지 회원정보 수집 등에 관한 기록</li><li>보유기간 : 3년</li><li>관련법령 : 신용정보의 수집/처리 및 이용 등에 관한 기록 : 3년</li></ul>
                                    </div>
                                </div>
                            </div>
                            
                            <!--3번-->
                            <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseThree">
                                       <strong>3. 개인정보의 처리 및 보유 기간</strong>
                                    </a>
                                </div>
                                <div id="collapseThree" class="collapse" data-parent="#accordionExample">
                                    <div class="card-body">
                                        ① <em class="emphasis"><b>RestFuLL</b>('RestFuLL')</em>
                                       은(는) 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집시에 동의 받은 개인정보 보유,이용기간 내에서 개인정보를 처리,보유합니다.
                                        <br/><br/>
                                       ② 각각의 개인정보 처리 및 보유 기간은 다음과 같습니다.
                                       <ul class='list_indent2 mgt10'>
                                       <li class='tt'>1.▶홈페이지 회원가입 및 관리</li>
                                       <li class='tt'>＜홈페이지 회원가입 및 관리＞와 관련한 개인정보는 수집.이용에 관한 동의일로부터＜3년＞까지 위 이용목적을 위하여 보유.이용됩니다.</li>
                                       <li>보유근거 : 홈페이지 회원정보 수집 등에 관한 기록</li>
                                       <li>관련법령 : 신용정보의 수집/처리 및 이용 등에 관한 기록 : 3년</li>
                                       <li>예외사유 : </li>
                                       </ul>
                                    </div>
                                </div>
                            </div>
                            
                            <!--4번-->
                             <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseFour">
                                       <strong>4. 개인정보처리 위탁</strong>
                                    </a>
                                </div>
                                <div id="collapseFour" class="collapse" data-parent="#accordionExample">
                                  <div class="card-body">
                                       ① <em class="emphasis"><b>RestFuLL</b>('RestFuLL')</em>
                                       은(는) 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다.
                                        <ul class='list_indent2 mgt10'>
                                           <li class='tt'>1. ＜＞</li>
                                           <li>위탁받는 자 (수탁자) : </li>
                                           <li>위탁하는 업무의 내용 : </li>
                                           <li>위탁기간 : </li>
                                       </ul>
                                       <p class='sub_p mgt10'>②  
                                           <span class='colorLightBlue'><b>RestFuLL</b>('http://www.RestFuLL.com'이하 'RestFuLL')</span>
                                           은(는) 위탁계약 체결시 개인정보 보호법 제25조에 따라 위탁업무 수행목적 외 개인정보 처리금지, 기술적․관리적 보호조치, 재위탁 제한, 수탁자에 대한 관리․감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게 처리하는지를 감독하고 있습니다.
                                       </p>
                                       <p class='sub_p mgt10'>③ 위탁업무의 내용이나 수탁자가 변경될 경우에는 지체없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다.</p>
                                    </div>
                                </div>
                            </div>
                            
                            
                            <!--5번-->
                             <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseFive">
                                      <strong>5. 정보주체와 법정대리인의 권리·의무 및 그 행사방법 이용자는 개인정보주체로써 다음과 같은 권리를 행사할 수 있습니다.</strong>
                                    </a>
                                </div>
                                <div id="collapseFive" class="collapse" data-parent="#accordionExample">
                                    <div class="card-body">  
                                        <p class="ls2">① 정보주체는 RestFuLL에 대해 언제든지 개인정보 열람,정정,삭제,처리정지 요구 등의 권리를 행사할 수 있습니다.</p>
                                        <p class='sub_p'>② 제1항에 따른 권리 행사는RestFuLL에 대해 개인정보 보호법 시행령 제41조제1항에 따라 서면, 전자우편, 모사전송(FAX) 등을 통하여 하실 수 있으며 RestFuLL은(는) 이에 대해 지체 없이 조치하겠습니다.</p>
                                        <p class='sub_p'>③ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다. 이 경우 개인정보 보호법 시행규칙 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.</p>
                                        <p class='sub_p'>④ 개인정보 열람 및 처리정지 요구는 개인정보보호법 제35조 제5항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다.</p>
                                        <p class='sub_p'>⑤ 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.</p>
                                        <p class='sub_p'>⑥ RestFuLL은(는) 정보주체 권리에 따른 열람의 요구, 정정·삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.</p>
                                    </div>
                                </div>
                            </div>
                           
                            
                            <!--6번-->
                             <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseSix">
                                       <strong> 6. 처리하는 개인정보의 항목 작성 </strong>
                                    </a>
                                </div>
                                <div id="collapseSix" class="collapse" data-parent="#accordionExample">
                                     <div class="card-body">
                                       ① <em class="emphasis"><b>RestFuLL</b>('http://www.RestFuLL.com'이하  'RestFuLL')</em>
                                       은(는) 다음의 개인정보 항목을 처리하고 있습니다.<p/>
                                       <ul class='list_indent2 mgt10'>
                                           <li class='tt'>1＜홈페이지 회원가입 및 관리＞</li>
                                           <li>필수항목 : 이메일, 휴대전화번호, 비밀번호, 로그인ID, 이름</li>
                                           <li>- 선택항목 : </li>
                                       </ul>
                                       <ul class='list_indent2 mgt10'>
                                           <li class='tt'>2＜마케팅 및 광고에의 활용＞</li>
                                           <li>필수항목 : 이메일, 휴대전화번호, 비밀번호, 로그인ID, 이름</li>
                                           <li>- 선택항목 : </li>
                                       </ul>
                                    </div>
                                </div>
                            </div>
                            
                           
                            <!--7번-->
                             <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseSeven">
                                       <strong>7. 개인정보의 파기<em class="emphasis"><b>RestFuLL</b>('RestFuLL')</em>은(는) 원칙적으로 개인정보 처리목적이 달성된 경우에는 지체없이 해당 개인정보를 파기합니다. 파기의 절차, 기한 및 방법은 다음과 같습니다.</strong>
                                    </a>
                                </div>
                                <div id="collapseSeven" class="collapse" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <p class='ls2'>-파기절차<br/>
                                             이용자가 입력한 정보는 목적 달성 후 별도의 DB에 옮겨져(종이의 경우 별도의 서류) 내부 방침 및 기타 관련 법령에 따라 일정기간 저장된 후 혹은 즉시 파기됩니다. 이 때, DB로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 다른 목적으로 이용되지 않습니다.<br/>
                                             <br/>-파기기한
                                             <br/>
                                             이용자의 개인정보는 개인정보의 보유기간이 경과된 경우에는 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.
                                        </p>
                                        <p class='sub_p mgt10'>-파기방법</p>
                                        <p class='sub_p'>전자적 파일 형태의 정보는 기록을 재생할 수 없는 기술적 방법을 사용합니다.</p>
                                        종이에 출력된 개인정보는 분쇄기로 분쇄하거나 소각을 통하여 파기합니다
                                                                                                
                                   
                                    </div>
                                </div>
                            </div>
                            
                            
                            <!--8번-->
                             <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseEight">
                                      <strong>8. 개인정보 자동 수집 장치의 설치•운영 및 거부에 관한 사항</strong>
                                    </a>
                                </div>
                                <div id="collapseEight" class="collapse" data-parent="#accordionExample">
                                    <div class="card-body">
                                         <p class="ls2">① RestFuLL 은 개별적인 맞춤서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠기(cookie)’를 사용합니다.
                                        ② 쿠키는 웹사이트를 운영하는데 이용되는 서버(http)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자들의 PC 컴퓨터내의 하드디스크에 저장되기도 합니다.
                                        가. 쿠키의 사용 목적 : 이용자가 방문한 각 서비스와 웹 사이트들에 대한 방문 및 이용형태, 인기 검색어, 보안접속 여부, 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다.
                                        나. 쿠키의 설치•운영 및 거부 : 웹브라우저 상단의 도구>인터넷 옵션>개인정보 메뉴의 옵션 설정을 통해 쿠키 저장을 거부 할 수 있습니다.
                                             다. 쿠키 저장을 거부할 경우 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.
                                             
                                        </p>
                                    </div>
                                </div>
                            </div>
                            
                            
                            <!--9번(형식상 10번)-->
                             <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseNine">
                                      <strong>9. 개인정보 처리방침 변경 </strong>
                                    </a>
                                </div>
                                <div id="collapseNine" class="collapse" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <p class='sub_p mgt10'>①이 개인정보처리방침은 시행일로부터 적용되며, 법령 및 방침에 따른 변경내용의 추가, 삭제 및 정정이 있는 경우에는 변경사항의 시행 7일 전부터 공지사항을 통하여 고지할 것입니다.</p>
                                    </div>
                                </div>
                            </div>
                            
                            
                            <!--10번-->
                             <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseTen">
                                       <strong>10. 개인정보의 안전성 확보 조치 <em class="emphasis"><b>RestFuLL</b>('RestFuLL')</em>은(는) 개인정보보호법 제29조에 따라 다음과 같이 안전성 확보에 필요한 기술적/관리적 및 물리적 조치를 하고 있습니다.</strong>
                                    </a>
                                </div>
                                <div id="collapseTen" class="collapse" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <p class='sub_p mgt10'>
                                        1. 정기적인 자체 감사 실시
                                        <br/>
                                        개인정보 취급 관련 안정성 확보를 위해 정기적(분기 1회)으로 자체 감사를 실시하고 있습니다.
                                        <br/><br/>
                                        2. 개인정보 취급 직원의 최소화 및 교육
                                        <br/>
                                        개인정보를 취급하는 직원을 지정하고 담당자에 한정시켜 최소화 하여 개인정보를 관리하는 대책을 시행하고 있습니다.
                                        <br/><br/>
                                        3. 내부관리계획의 수립 및 시행
                                        <br/> 
                                        개인정보의 안전한 처리를 위하여 내부관리계획을 수립하고 시행하고 있습니다.
                                        <br/><br/>
                                        4. 해킹 등에 대비한 기술적 대책
                                        <br/> 
                                        <em class="emphasis">RestFuLL</em>>
                                        ('<em class="emphasis">RestFuLL</em>')은 해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신·점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적/물리적으로 감시 및 차단하고 있습니다.
                                        <br/><br/>
                                        5. 개인정보의 암호화
                                        <br/> 
                                        이용자의 개인정보는 비밀번호는 암호화 되어 저장 및 관리되고 있어, 본인만이 알 수 있으며 중요한 데이터는 파일 및 전송 데이터를 암호화 하거나 파일 잠금 기능을 사용하는 등의 별도 보안기능을 사용하고 있습니다.
                                        <br/><br/>
                                        6. 접속기록의 보관 및 위변조 방지
                                        <br/> 
                                        개인정보처리시스템에 접속한 기록을 최소 6개월 이상 보관, 관리하고 있으며, 접속 기록이 위변조 및 도난, 분실되지 않도록 보안기능 사용하고 있습니다.
                                        <br/><br/>
                                        7. 개인정보에 대한 접근 제한
                                        <br/> 
                                        개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여,변경,말소를 통하여 개인정보에 대한 접근통제를 위하여 필요한 조치를 하고 있으며 침입차단시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다.
                                        <br/><br/>
                                        8. 문서보안을 위한 잠금장치 사용
                                        <br/> 
                                        개인정보가 포함된 서류, 보조저장매체 등을 잠금장치가 있는 안전한 장소에 보관하고 있습니다.
                                        <br/><br/>
                                        9. 비인가자에 대한 출입 통제
                                        <br/> 
                                        개인정보를 보관하고 있는 물리적 보관 장소를 별도로 두고 이에 대해 출입통제 절차를 수립, 운영하고 있습니다.
                                        <br/><br/>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
   <!--개인정보처리방침 end-->



<%@ include file="/WEB-INF/include/js-footer.jsp"%>

</html>