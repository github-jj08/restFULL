package bit.project.restfull.page;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private int startPage;
	private int endPage; 
	// startPage:1 endPage:10 ====> 1 2 3 4 5 6 7 8 9 10
	// startPage:1 endPage:5 ====> 1 2 3 4 5
	// startPage:11 endPage:20 ====> 11 12 13 14 15 16 17 18 19 20
	
	private boolean prev, next; 
	
	// private boolean prev;// '<<'
	// private boolean next;// '>>'
	
	private int total; 
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {	
	    this.cri = cri;
	    this.total = total; 
	    
	    this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
	    //cri.getPageNum() = 3;
	    // ceil(3 / 10.0) * 10 => 1 * 10 = 10
	    // 1. (3/10.0) = 0.3
	 	// 2. ceil(0.3) = 1
	 	// 3. 1* 10 = 10
	 	// 1 2 3 4 5 6 7 8 9 10
	    
	    // cri.getPageNum() = 13;
	    // 11 12 13 14 15 16 17 18 19 20
	    this.startPage = this.endPage - 9;
	    
	    int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
	    
	    //total = 512
	    //cri.getAmount()
	    //ceil(512 / 10.0) = 52
	    if (realEnd <= this.endPage) {
	      this.endPage = realEnd;
	    }
	    
	    this.prev = this.startPage > 1; 
	    this.next = this.endPage < realEnd;
		 
	}
	
	public String makeQuery(int page) {
		UriComponents uriComponentsBuilder = UriComponentsBuilder.newInstance().queryParam("pageNum", page) // pageNum = 3
				.queryParam("amount", cri.getAmount()) // pageNum=3&amount=10
				.build(); // ?pageNum=3&amount=10
		return uriComponentsBuilder.toUriString(); // ?pageNum=3&amount=10  
	}
	
	  

}	