package project.spring.nft.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.web3j.protocol.exceptions.TransactionException;

import com.klaytn.caver.Caver;
import com.klaytn.caver.kct.kip17.KIP17;
import com.klaytn.caver.methods.response.Quantity;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import project.spring.nft.domain.ArtVO;
import xyz.groundx.caver_ext_kas.CaverExtKAS;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.ApiException;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.wallet.model.Account;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.wallet.model.AccountStatus;
import xyz.groundx.caver_ext_kas.kas.tokenhistory.TokenHistoryQueryOptions;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.tokenhistory.api.TokenApi;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.tokenhistory.model.Nft;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.tokenhistory.model.NftContractDetail;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.tokenhistory.model.PageableNftContractDetails;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.tokenhistory.model.PageableNfts;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.kip17.api.Kip17TokenApi;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.kip17.model.Kip17ContractInfoResponse;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.kip17.model.Kip17DeployResponse;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.kip17.model.Kip17TokenListResponse;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.kip17.model.Kip17TransactionStatusResponse;

public class KAS {
	// Authorization
	final static String chainId = "1001";
	final static String accessKeyId = "KASKEMNC1D88Q7GH1TNVLZHR";
	final static String secretAccessKey = "HOkyolJgnqehhk44F9ecIcbHCN6m-HBk-ARWMOYt";
	final static String contractAlias = "test";

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
		options.setSize((long) 1);
		PageableNftContractDetails result = caver.kas.tokenHistory.getNFTContractList(options);
		System.out.println("NFT 컨트랙트 리스트 결과 : " + result);
	}

	// KAS의 KIP-17 API를 활용한 KIP-17(NFT) 컨트랙트 배포
	public void deployKip17() throws ApiException {
		CaverExtKAS caver = new CaverExtKAS();
		// 사용자의 현재 네트워크, accesskeyid, secretkeyid..
		caver.initKASAPI(chainId, accessKeyId, secretAccessKey);

		// 11.25 현아. 이렇게 하자. api 인증은 내 계정. 컨트랙트 내 계정. 작품 등록시에
		// 토큰 발행.

		String name = "My First KIP-17";
		String symbol = "MFK";
		String alias = "my-first-kip17";
		String chainId = caver.getKas().getKip17().getChainId();
		Kip17DeployResponse res = caver.kas.kip17.deploy(name, symbol, alias);

		System.out.println("KIP-17 컨트랙트 배포 response result : " + res + ", 네트워크 상황 : " + chainId); // 결제대금안넣었음.
	}

	// KAS의 KIP-17 API를 활용한 alias를 매개변수로 KIP-17(NFT) 컨트랙트 조회
	public void selectKip17() throws ApiException {
		CaverExtKAS caver = new CaverExtKAS();
		// 사용자의 현재 네트워크, accesskeyid, secretkeyid..
		caver.initKASAPI(chainId, accessKeyId, secretAccessKey);

		String alias = "test"; // Token의 별명도 사용자의 아이디
		String chainId = caver.getKas().getKip17().getChainId();

		Kip17ContractInfoResponse contractRes = caver.kas.kip17.getContract(alias);

		System.out.println("KIP-17 별명 " + alias + " 이름의 컨트랙트 조회 : " + contractRes);
		System.out.println("KIP-17 alias(memberid)를 매개변수로 컨트랙트 주소조회 : " + contractRes.getAddress());
	} // 해당 alias가 없으면 오류가 뜸..!

	// KAS의 KIP-17 API를 활용해 NFT 발행.
	public void mintKip17() throws NoSuchMethodException, IOException, InstantiationException, ClassNotFoundException,
			IllegalAccessException, InvocationTargetException, TransactionException, ApiException {
		// caver-java-ext-kas로 KIP-17 토큰을 발행하는 경우, API request의 body에 필요한 데이터를 함수의 파라미터로
		// 전송합니다.
		// caver.kas.kip17.mint((addressOrAlias, to, tokenId, tokenURI);
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI(chainId, accessKeyId, secretAccessKey);

		ArtVO vo = new ArtVO();
		System.out.println(vo.getMemberAccount());

		// TODO : 토큰 소유자의 지갑. 매개변수로 받아오기.
		String to = "0x2d883293342EC229951b2f2F95D09Cd0DcF749B5"; // kaikas 지갑.
		String id = "0x17"; // 토큰의 아이디, 기존의 아이디와 중복불가능.
		String uri = "C:\\Study\\JsonUpload\\metadata.json"; // 메타데이터주소.

		Kip17TransactionStatusResponse response = caver.kas.kip17.mint(contractAlias, to, id, uri);

		System.out.println("KIP-17 토큰 발행 response result " + response);
		response.getTransactionHash();

	} // end mint()

	// KAS의 KIP-17 API를 활용해 특정 컨트랙트 주소에서 발행된 NFT 삭제(소각)
	public void deleteKip17() throws ApiException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI(chainId, accessKeyId, secretAccessKey);

		String contractAddress = "0x37b23fa7289b8a3055839fdf36d2bed9d7714665"; // test
		String from = "0x2d883293342ec229951b2f2f95d09cd0dcf749b5";
		String tokenId = "0x8967";
		String sender = "0x2d883293342ec229951b2f2f95d09cd0dcf749b5";
		String owner = "0x2d883293342ec229951b2f2f95d09cd0dcf749b5";
		String to = "0x0000000000000000000000000000000000000000";

		// 0x0000000000000000000000000000000000000000 로 이동시켜서 소각하기.

		Kip17TransactionStatusResponse res = caver.kas.kip17.transfer(contractAddress, sender, owner, to, tokenId);

		System.out.println("KIP-17 토큰 삭제 response result " + res);

	} // end delete()

	// KAS의 KIP-17 API를 활용해 특정 컨트랙트 주소에서 발행된 NFT들 조회.
	public void selectedContractKip17() throws ApiException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI(chainId, accessKeyId, secretAccessKey);

		String address = "0x37b23fa7289b8a3055839fdf36d2bed9d7714665"; // 컨트랙트의 주소
		NftContractDetail detail = caver.kas.tokenHistory.getNFTContract(address);
		System.out.println("KIP-17 특정 컨트랙트 계정의 NFT 토큰 조회 : " + detail);
	} // end select()

	// KAS의 KIP-17 API를 활용해 특정 컨트랙트 주소에서 발행된 모든 ! NFT들 조회.
	public void selecteNftKip17() throws ApiException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI(chainId, accessKeyId, secretAccessKey);

		String address = "0x37b23fa7289b8a3055839fdf36d2bed9d7714665"; // 컨트랙트의 주소
		PageableNfts detail = caver.kas.tokenHistory.getNFTList(address);
		System.out.println("KIP-17 특정 컨트랙트 계정의 모든 NFT 토큰 조회 : " + detail);
	} // end select()

	// KAS의 KIP-17 API를 활용해 특정 컨트랙트 주소에서 발행된 하나의 NFT 조회.
	public void selectOneNftKip17() throws ApiException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI(chainId, accessKeyId, secretAccessKey);

		ArtVO vo = new ArtVO();
		vo.getArtTokenId();

		String address = "0x94b74823e4a49e6e2c10ee3df814c2bf5e273899"; // 컨트랙트의 주소
		String tokenId = "0xd727";

		Nft nft = caver.kas.tokenHistory.getNFT(address, tokenId);

		System.out.println("KIP-17 특정 컨트랙트 계정의 하나의 NFT 정보를 조회 : " + nft);

	}

	// my-first-kip17 토큰의 모든 리스트 정보 조회하기.
	public void selectTokenListKip17() throws ApiException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI(chainId, accessKeyId, secretAccessKey);

		Kip17TokenListResponse response = caver.kas.kip17.getTokenList("my-first-kip17");
		System.out.println(response);

	}

	// KAS의 KIP-17 API를 활용해 토큰 전송하기 (transfer)
	public void transferKip17() throws ApiException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI(chainId, accessKeyId, secretAccessKey);

		String contractAlias = "my-first-kip17";
		String ownerAddress = "0x{OwnerAddress}";
		String recipientAddress = "0x{RecipientAddress}";
		String tokenId = "0x321";
		Kip17TransactionStatusResponse response = caver.kas.kip17.transfer(contractAlias, ownerAddress, ownerAddress,
				recipientAddress, tokenId);
		System.out.println(response);

	}
	
	public void deleteAccountKip17( ) throws ApiException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI(chainId, accessKeyId, secretAccessKey);
		
		String address = "0xe876Ab429A92C81A14F2aaD9B987A1fEb50d004a";
		
		AccountStatus result = caver.kas.wallet.deleteAccount(address);
		System.out.println("계정삭제 결과 : " + result);
	}

} // end class