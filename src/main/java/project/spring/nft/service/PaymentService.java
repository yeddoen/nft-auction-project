package project.spring.nft.service;

import project.spring.nft.domain.PaymentVO;

public interface PaymentService {
	
	int insertPayment(PaymentVO vo);
	
	PaymentVO selectByMemberId(String memberId);
	
	PaymentVO selectByArtNo(int artNo);

}
