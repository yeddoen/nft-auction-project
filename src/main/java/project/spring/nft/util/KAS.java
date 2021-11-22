package project.spring.nft.util;

import java.io.IOException;

import com.klaytn.caver.methods.response.Quantity;

import xyz.groundx.caver_ext_kas.CaverExtKAS;

public class KAS {
	public static void getBlockNumber() throws IOException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI("0x2d883293342ec229951b2f2f95d09cd0dcf749b5", "KASKEMNC1D88Q7GH1TNVLZHR", "HOkyolJgnqehhk44F9ecIcbHCN6m-HBk-ARWMOYt");
		Quantity response = caver.rpc.klay.getBlockNumber().send();
		System.out.println(response.getResult());
	}

	public static void main(String[] args) throws IOException {
		getBlockNumber();
	}
	
}