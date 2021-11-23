package project.spring.nft.service;

import java.util.List;

import project.spring.nft.domain.PaymentVO;

public interface PaymentService {
	
	int insertPayment(PaymentVO vo);
	
	PaymentVO selectByMemberId(String memberId);
	
	PaymentVO selectByArtNo(int artNo);

}
