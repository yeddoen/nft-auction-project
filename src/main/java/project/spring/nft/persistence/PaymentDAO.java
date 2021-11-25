package project.spring.nft.persistence;

import project.spring.nft.domain.PaymentVO;

// 결제 관련
public interface PaymentDAO {
 
	int insertPayment(PaymentVO vo);
	
	PaymentVO selectByMemberId(String memberId);
	
	PaymentVO selectByArtNo(int artNo);
}
