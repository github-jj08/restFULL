package bit.project.restfull.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import bit.project.restfull.service.AdminBoardService;
import bit.project.restfull.vo.AdminBoardVO;
import bit.project.restfull.vo.DestinationVO;
import bit.project.restfull.vo.GoodsVO;
import bit.project.restfull.vo.SidoguVO;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
public class TravelCourseController {

	//������ ���� �޼ҵ尡 adminBoardService�� �̹� �����ϹǷ� admin���񽺿� �ִ� �� ���پ�

   @Autowired
   private AdminBoardService adboardService;
   
   @GetMapping("/travelcourse")
   public String travelCourse() {
	   return "travelcourse";
   }
   
   
   @ResponseBody
   @GetMapping("/getSigunguCode/{sidoCode}")
   public List<SidoguVO> getSigunguCode(@PathVariable(value="sidoCode") int sidoCode) {
      log.info("sidoCode : " + sidoCode);
      //sidoNum�� �ش��ϴ� �����ڵ���� �ҷ���
      List<SidoguVO> optionList = adboardService.getOptionList(sidoCode);
      return optionList;
   }
   
   @ResponseBody
   @GetMapping("/getDest/{sigungu_code}")
   public List<DestinationVO> getDests(@PathVariable(value="sigungu_code") int sigungu_code) {
      log.info("sigungu_code : " + sigungu_code);
      //sigungu_code�� �ش��ϴ� ���������� �ҷ���(���� adboardService�� sigungu_code�� �޾� ����� ����ϴ� ���ο� �޼ҵ� �߰�)
      List<DestinationVO> destlist = adboardService.getDestList(sigungu_code); //select * from destination where sigungu_code = 11110;
      return destlist;
   }
   
   @ResponseBody
   @PostMapping("/myTravelCourse")
   public List<GoodsVO> addTravelCourse(HttpServletRequest req) {
      log.info("addTravelCourse");
      String[] destinations = req.getParameterValues("myCourse[]");
      log.info(destinations.length);
      for(int i=0;i<destinations.length;i++) {
    	  log.info("������ ��� ? " + destinations[i]);
      }
      List<GoodsVO> goodslist = adboardService.getRGoods(destinations);
      return goodslist;
   }
}