package bit.project.restfull.page;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	//페이징 처리 할때 필요한 정보들
	private int startPage;//화면에 보여지는 페이지 시작번호
	private int endPage; //화면에 보여지는 끝번호
	// 예) startPage:1 endPage:10 ====> 1 2 3 4 5 6 7 8 9 10
	// 예) startPage:1 endPage:5 ====> 1 2 3 4 5
	// 예) startPage:11 endPage:20 ====> 11 12 13 14 15 16 17 18 19 20
	
	private boolean prev, next; //이전과 다음으로 이동가능한 링크 표시
	
	// private boolean prev;// '<<'버튼 출력여부
	// private boolean next;// '>>'버튼 출력여부
	
	private int total; //전체 게시글 수
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {	
	    this.cri = cri;
	    this.total = total; //전체 데이타 수
	    
	    this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
	    // 현재페이지 정보로 부터 끝페이지에 대한 연산
	    //cri.getPageNum() = 3;
	    //하단  페이징 바에 보여줄 페이징바 개수
	    // 연산 : ceil(3 / 10.0) * 10 => 1 * 10 = 10까지
	    // 1. (3/10.0) = 0.3
	 	// 2. ceil(0.3) = 1
	 	// 3. 1* 10 = 10
	 	// 1 2 3 4 5 6 7 8 9 10
	    
	    // cri.getPageNum() = 13;
	    // 하단 페이징 바에 보여줄 페이징바 개수 == 10개
	    // 연산 : ceil(13/10.0) * 10 => 2 * 10 = 20까지
	    // 11 12 13 14 15 16 17 18 19 20
	    this.startPage = this.endPage - 9;
	    
	    //Total을 통한 endPage의 재계산
	    //10개씩 보여주는 경우 전체 데이터 수가 80개라고 가정하면 끝번호는 10이 아닌 8이 됨
	    int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
	    
	    //total = 512
	    //cri.getAmount()
	    //ceil(512 / 10.0) = 52
	    // 51.2의결과를 올림 => 51페이지 까지는 한 화면당 10개의 게시물이 출력
	    ///마지막 52페이지에는 0.2에 해당하는 2개의 게시물이 출력
	    if (realEnd <= this.endPage) {
	      this.endPage = realEnd;
	    }
	    
	    //시작번호가 1보다 큰경우 존재
	    this.prev = this.startPage > 1; 
	    //realEnd가 끝번호(endPage)보다 큰 경우에만 존재
	    this.next = this.endPage < realEnd;
		 
	}
	
	public String makeQuery(int page) {
		UriComponents uriComponentsBuilder = UriComponentsBuilder.newInstance().queryParam("pageNum", page) // pageNum = 3
				.queryParam("amount", cri.getAmount()) // pageNum=3&amount=10
				.build(); // ?pageNum=3&amount=10
		return uriComponentsBuilder.toUriString(); // ?pageNum=3&amount=10 리턴 
	}
	
	  

}	