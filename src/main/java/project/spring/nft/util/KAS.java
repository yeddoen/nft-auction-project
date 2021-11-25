package project.spring.nft.util;

import java.io.IOException;

import com.klaytn.caver.methods.response.Quantity;

import xyz.groundx.caver_ext_kas.CaverExtKAS;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.ApiException;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.wallet.model.Account;
import xyz.groundx.caver_ext_kas.kas.tokenhistory.TokenHistoryQueryOptions;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.tokenhistory.model.PageableNftContractDetails;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.kip17.model.Kip17TransactionStatusResponse;

public class KAS {
	// 최신 블록 넘버 호출하기!
	public static void getBlockNumber() throws IOException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI("1001", "KASKEMNC1D88Q7GH1TNVLZHR", "HOkyolJgnqehhk44F9ecIcbHCN6m-HBk-ARWMOYt");
		Quantity response = caver.rpc.klay.getBlockNumber().send();
		System.out.println("최신 블록 넘버 : " + response.getResult());
	}
	
	// KAS의 Wallet API를 사용해 계정생성하기!
	public static void createAccount() throws ApiException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI("1001", "KASKEMNC1D88Q7GH1TNVLZHR", "HOkyolJgnqehhk44F9ecIcbHCN6m-HBk-ARWMOYt");
		Account account = caver.kas.wallet.createAccount();
		System.out.println("Wallet API로 계정생성 : " + account);
	}
	
	// TokenHistory API를 사용하여 NFT 컨트랙트 리스트 호출
	public static void getNFTContractList() throws ApiException {
        CaverExtKAS caver = new CaverExtKAS();
        caver.initKASAPI("1001", "KASKEMNC1D88Q7GH1TNVLZHR", "HOkyolJgnqehhk44F9ecIcbHCN6m-HBk-ARWMOYt");
        // 쿼리 파라미터는 optional 파라미터이므로, 쿼리 파라미터 없이 실행할 수 있습니다.
        TokenHistoryQueryOptions options = new TokenHistoryQueryOptions();
        options.setSize((long)1);
        PageableNftContractDetails result = caver.kas.tokenHistory.getNFTContractList(options);
        System.out.println("NFT 컨트랙트 리스트 결과 : " + result);
    }
	
	// KAS의 KIP-17 API를 활용한 KIP-17(NFT) 컨트랙트 배포
	public static void deployKip17() throws ApiException {
	    CaverExtKAS caver = new CaverExtKAS("1001", "KASKEMNC1D88Q7GH1TNVLZHR", "HOkyolJgnqehhk44F9ecIcbHCN6m-HBk-ARWMOYt");
	    String operatorID = "operatorId";
	    String name = "My First KIP-17";
	    String symbol = "MFK";
	    String alias = "my-first-kip17";
//	    Kip17TransactionStatusResponse res = caver.kas.kip17.deploy(name, symbol, alias);
//	    System.out.println(res);
	  }

} // end class