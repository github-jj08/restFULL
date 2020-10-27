# Project_restFULL

## 1. 프로젝트 소개
  
레스트풀(restFULL)은 간단한 여행후기를 공유하고, 여행코스와 여행지 상품을 좀더 직관적이고 빠르게 조회할 수 있도록 하는 **여행 테마**의 웹 서비스입니다. 




![image](https://user-images.githubusercontent.com/65878549/97250534-864ffb80-1849-11eb-9b95-589d304cfddf.png)




### 프로젝트 목표

+ 여행 후기를 공유할 수 있다.
+ 여행 코스를 직접 생성하고 관련된 상품을 한눈에 확인 및 결제할 수 있다.


**사용자**는 특정 여행지에 대한 여행 후기와 위치를 공유할 수 있으며, 좋아요를 받아 인기 게시글로 더 자주 노출될 수 있습니다. 또한 여행코스 작성 메뉴를 통해 특정 지역구와 관련된 여행지 목록에서 여러 여행지를 선택하여 여행코스를 구성할 수 있습니다. 여행코스가 등록되면 작성한 여행코스 내 여행지와 관련된 상품 목록이 보여지고 그 중 사용하려는 특정 상품만 골라 결제할 수 있습니다. 단, 비회원은 여행 후기조회만 가능합니다.


**관리자**는 여행지 목록을 구성할 수 있으며, 특정 인기 게시글의 위치를 여행코스에 등록될 수 있도록 여행지 목록에 등록할 수 있습니다. 여행지 관련 상품(입장권 등)들을 직접 매수하여 저렴하게 제공한다는 컨셉이므로, 관리자가 특정 여행지에 대한 상품을 직접 등록하고 수정할 수 있습니다. 
      
>  "_**당신의 여행코스 작성을 도와드립니다.**_"    
   
      
---------------------------------

## Table of Contents
* [How to use]
  * [설치 방법](#설치-방법)
  * [개발 환경](#개발-환경)
  * [사용 방법](#사용-방법)
  
* [가능정리]
  * [유저 모드](#유저-모드)
  * [관리자 모드](#관리자-모드)
  
---------------------------------

## 설치 방법 (Installation)
  + 필요한 프로그램
    - [Tomcat V9.0](http://tomcat.apache.org/)   
    - [Spring Framework](https://spring.io/projects/spring-framework)   
    - [JAVAjre & JDK Compiler 1.8](https://www.oracle.com/java/technologies/javase-downloads.html)   

## 개발 환경 (Develop Environment)
  + 프로젝트 구현 핵심
    - JSP & Servlet MVC 모델   
    - Mybatis, Hibernate를 적용한 Spring 전자정부 프레임 워크     
    - OPEN API를 활용한 기능 구현 
    
    
  + 활용 Tool 
    - FRONT: HTML5, CSS, JavaScript, Bootstrap4, jQuery, jQuery-ui, Ajax, nodeJS, font-awesome, Visual Studio Code, Brackets
    - BACK: Java, Spring Framework, 5.1.1 RELEASE, Spring Security, mybatis, NaverMapAPI, REST API, Oauth2.0, LoginAPI(Kakao,Naver), Apachicommons-email, Log4j
    - Server: Apache Tomcat 9.0
    - DB: Oracle 
    - 형상관리: GitHub 
    - Tool: exERD, Sourcetree, STS, sqldeveloper, kakaoOven

  + [JQuery](https://jquery.com/)   
  + [oracle SQL](https://www.oracle.com/database/technologies/appdev/sqldeveloper-landing.html)   
  + [Spring Security](https://spring.io/projects/spring-security)   
    
---------------------------------

## 유저 모드
+ 유저 모드 기능 설명

  1. 일반 회원가입
   > 입력 항목별 유효성 검사   
   > 약관 미체크시 회원가입 불가   
   > 핸드폰 번호 인증번호 기능   
  
  2. 로그인
    - 아이디 비밀번호 찾기
   > 가입시 입력했던 이메일 대조비교후 아이디 출력, 이메일로 임시 비밀번호 전송   
   > 페이지 자체 정보 로그인 및 카카오, 네이버를 통한 소셜로그인 구현   
  
  3. 메인 페이지
    - 게시글 검색 기능
   > 목적지 이름을 통한 해당 게시글 리스트 출력   
   > 여행 정보 및 리뷰 게시글 리스트 출력   
  
  4. 마이 페이지
    - 간략한 회원 정보 수정 가능
   > 연락처, 이메일, 비밀번호 수정 가능   
  
    - 내 게시글 리스트
   > 본인이 작성한 게시글을 리스트로 출력, 삭제 및 수정 가능   
  
    - 좋아요 한 글 목록
   > 사용자가 타인의 게시글에 좋아요 등록한 게시글 리스트 출력   
   > 좋아요 게시글 확인및 해제 가능   
  
    - 내 여행코스 보기
   > 사용자가 등록했던 여행코스 리스트 출력   
   > 수정 및 삭제 가능   
  
    - 내 결제내역 보기
   > 사용자가 결제한 여행지 관련 상품 결제내역 확인 가능   
  
    - 내 신고내역 보기
   > 사용자가 신고한 특정 게시글 및 댓글의 리스트 및 상태 확인 가능   
   > 답변 처리여부 확인 가능   
    
    - 내 문의 내역 보기
   > 사용자가 문의한 글 리스트 출력 및 답변 확인 가능   
  


## 관리자 모드
+ 관리자 모드 기능 설명
  
  1.유저 관리
   > 관리자 권한으로 유저 리스트에서 해당 유저를 선택하고 유저가 수정하지 못하는 개인정보 변경   
   > 특정 유저의 권한 및 회원 탈퇴 가능   
   > 특정 유저가 작성한 글 확인 가능   
  
  2.공지사항 및 이벤트 관리
   > 관리자 권한 공지사항 작성 내역 확인 및 작성 가능   
   > 같은 페이지 내에서 공지사항 과 이벤트로 구분가능   
   > 관리자만 수정 삭제 가능  
  
  3.문의 및 신고 답변
   > 유저가 결재한 상품에 대한 정보 리스트 출력   
  
  4.여행지 및 상품 관리
   > 전체 여행지 정보 리스트 출력 및 정보 추가 가능   
  
  5.상품 주문내역 관리
   > 여행지관련 상품 리스트 출력 및 상품 정보 추가 가능   
