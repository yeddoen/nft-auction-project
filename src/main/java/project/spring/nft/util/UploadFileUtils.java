package project.spring.nft.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

	public static String uploadFile(String uploadPath, String originalName, byte[] byteData) throws Exception {
		S3Util s3 = new S3Util();
		String bucketName = "nftauctionbucket";
		// 랜덤의 uid 를 만들어준다.
		UUID uid = UUID.randomUUID();

		// savedName : /570d570a-7af1-4afe-8ed5-391d660084b7_g.JPG 같은 형식으로 만들어준다.
		String savedName = "/" + uid.toString() + "_" + originalName;

		logger.info("업로드 경로 : " + uploadPath);
		// \2017\12\27 같은 형태로 저장해준다.
		String savedPath = calcPath(uploadPath);

		String uploadedFileName = null;

		uploadedFileName = (savedPath + savedName).replace(File.separatorChar, '/');
		// S3Util 의 fileUpload 메서드로 파일을 업로드한다.
		s3.fileUpload(bucketName, uploadPath + uploadedFileName, byteData);

		logger.info(uploadedFileName);
		// s3.fileUpload(bucketName, new File(fileName))

		/*
		 * 썸네일 만들기 FileCopyUtils.copy(byteData, target);
		 * 
		 * String extension = savedName.substring(savedName.lastIndexOf(".") + 1); //확장자
		 * 
		 * String result = null; if (MediaUtil.getMediaType(extension) != null) { result
		 * = createThumbnail(uploadPath, savePath, saveName); } else { //미디어확장자가 아니면
		 * logger.info("미디어 확장자가 아닙니다."); result="fail"; }
		 */

		return uploadedFileName; // 업로드된 파일이름 반환

	} // end uploadFile()

	// 업로드한 날짜 년/월/일 파일경로 생성해주는 클래스
	private static String calcPath(String uploadPath) {

		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + cal.get(Calendar.YEAR);

		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);

		logger.info(datePath);

		return datePath;
	}// end calcPath()

	private static void makeDir(String uploadPath, String... paths) {

		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}

		for (String path : paths) {

			File dirPath = new File(uploadPath + path);

			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}// end makeDir()
}