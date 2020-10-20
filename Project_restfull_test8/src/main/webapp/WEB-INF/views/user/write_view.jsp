<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>RestFuLL | 글작성 </title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		

</head>
<body>
<%@ include file="/WEB-INF/include/js-header.jsp"%>



    <script>
        //임의의 file object영역
        var files = {};
        var previewIndex = 0;
 
        // image preview 기능 구현
        // input = file object[]
        function addPreview(input) {
            if (input[0].files) {
                //파일 선택이 여러개였을 시의 대응
                for (var fileIndex = 0; fileIndex < input[0].files.length; fileIndex++) {
                    var file = input[0].files[fileIndex];
 
                    if (validation(file.name))
                        continue;
 
                    var reader = new FileReader();
                    reader.onload = function(img) {
                        //div id="preview" 내에 동적코드추가.
                        //이 부분을 수정해서 이미지 링크 외 파일명, 사이즈 등의 부가설명을 할 수 있을 것이다.
                        var imgNum = previewIndex++;
                        $("#preview")
                                .append(
                                        "<div class=\"preview-box\" value=\"" + imgNum +"\">"
                                                + "<img class=\"thumbnail\" src=\"" + img.target.result + "\"\/>"
/*                                                + "<p>"
                                                + file.name
                                                + "</p>"*/
                                                + "<a href=\"#\" value=\""
                                                + imgNum
                                                + "\" onclick=\"deletePreview(this)\">"
                                                + "X" + "</a>" + "</div>");
                        files[imgNum] = file;
                    };
                    reader.readAsDataURL(file);
                }
            } else
                alert('invalid file input'); // 첨부클릭 후 취소시의 대응책은 세우지 않았다.
        }
 
        //preview 영역에서 삭제 버튼 클릭시 해당 미리보기이미지 영역 삭제
        function deletePreview(obj) {
            var imgNum = obj.attributes['value'].value;
            delete files[imgNum];
            $("#preview .preview-box[value=" + imgNum + "]").remove();
            /*resizeHeight();*/
        }
 
        //client-side validation
        //always server-side validation required
        function validation(fileName) {
            fileName = fileName + "";
            var fileNameExtensionIndex = fileName.lastIndexOf('.') + 1;
            var fileNameExtension = fileName.toLowerCase().substring(
                    fileNameExtensionIndex, fileName.length);
            if (!((fileNameExtension === 'jpg')
                    || (fileNameExtension === 'gif') || (fileNameExtension === 'png'))) {
                alert('jpg, gif, png 확장자만 업로드 가능합니다.');
                return true;
            } else {
                return false;
            }
        }
 
        $(document).ready(function() {
            //submit 등록. 실제로 submit type은 아니다.
            $('.write-btn').on('click',function() {                        
                var form = $('.writeform')[0];
                var formData = new FormData(form);
    
                for (var index = 0; index < Object.keys(files).length; index++) {
                    //formData 공간에 files라는 이름으로 파일을 추가한다.
                    //동일명으로 계속 추가할 수 있다.
                    formData.append('files',files[index]);
                }

            });
            // <input type=file> 태그 기능 구현
            $('#attach input[type=file]').change(function() {
                addPreview($(this)); //preview form 추가하기
            });
        });
    </script>



<!-- <글쓰기 부분> -->

    <section class="blog-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <div class="blog-details-inner">
                        <div class="blog-detail-title">
							<form action="<%=request.getContextPath() %>/user/write" method="post" enctype="multipart/form-data" class="writeform">
								<!-- hidden -->
								<sec:authentication var="principal" property="principal" />
								<input type="hidden" name="member_id" value="${principal.user.member_id}"/>
								<input type="hidden" name="boardlist_numbers" value="<c:out value='1'/>">
								<input type="hidden" name="filter_numbers" value="<c:out value='1'/>">
								
								<!-- write Data -->

							    <div class="group-input">
							        <div class="writepic">
                                        <!-- 첨부 버튼 -->
                                        <div id="attach">
                                            <label class="waves-effect waves-teal btn-flat" for="uploadInputBox">📸사진첨부📸 👈🏻CLICK</label>
                                            <input id="uploadInputBox" style="display: none" type="file" name="file" accept="image/*" multiple />
                                        </div>
                                   
                                    <!-- 미리보기 영역 -->
                                        <div class="imgs_wrap">
                                            <div id="preview" class="content"></div>
                                        </div>
                                    </div>
                                 </div>
								<hr/>
								
								<div class="group-input">
									<div class="writetitle">
                                        <label for="title">제목 </label>
									    <input type="text" name="title" class="write_tilte" maxlength="20">
                                    </div>
								</div>
								
								<div class="group-input">
								    <div class="writeloc">
                                        <label for="location">위치</label>
                                        <input type="text" class="write_loc" name="location" maxlength="20" >
                                    </div>
								</div>

								<div class="group-input">
									<label for="contents">내용 </label>
									<div class="writecontentx">
									    <textarea rows="10" cols="50" name="contents" class="write_content" placeholder="최대 500자 까지 작성 가능합니다." maxlength="500"></textarea>
									</div>
								</div>

								<input type="submit" class="site-btn write-btn" value="글 게시"/>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	
<%@ include file="/WEB-INF/include/js-footer.jsp"%>
</body>
</html>