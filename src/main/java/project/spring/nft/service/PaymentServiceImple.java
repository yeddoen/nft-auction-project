package project.spring.nft.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.nft.domain.PaymentVO;
import project.spring.nft.persistence.PaymentDAO;

@Service
public class PaymentServiceImple implements PaymentService {

	private static final Logger logger =
			LoggerFactory.getLogger(PaymentServiceImple.class);
	
	@Autowired
	private PaymentDAO dao;
	
	@Override
	public int insertPayment(PaymentVO vo) {
		logger.info("insertPayment() 호출");
		return dao.insertPayment(vo);
	}

	@Override
	public PaymentVO selectByMemberId(String memberId) {
		logger.info("selectByMemberId() 호출");
		return dao.selectByMemberId(memberId);
	}

	@Override
	public PaymentVO selectByArtNo(int artNo) {
		logger.info("selectByArtNi() 호출");
		return dao.selectByArtNo(artNo);
	}

}
