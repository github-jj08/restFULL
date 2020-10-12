package bit.project.restfull.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import bit.project.restfull.mapper.PaymentMapper;
import bit.project.restfull.vo.PaymentVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor
@Service
public class PaymentService {
	
	@Inject
	private PaymentMapper paymentMapper;
	
	public List<PaymentVO> getPayList(String destination_name){
		return paymentMapper.getPayList(destination_name);
		
	}
	public List<PaymentVO> getPayList2(){
		return paymentMapper.getPayList2();
		
	}
	
}

