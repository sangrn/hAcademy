package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/download")
public class FileDownload extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// uuid의 이름으로 다운로드 > origin의 이름으로 다운로드

		String fileName = req.getParameter("filename");
		String saveDirectory = "c:\\upload";

		String filePath = saveDirectory + File.separator + fileName;

		System.out.println(filePath);

		FileInputStream fis = new FileInputStream(filePath);
		String mimeType = getServletContext().getMimeType(filePath);

		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}

		System.out.println(mimeType);

		// 사용자의 브라우저에 따른 파일이름 변경
		String userAgent = req.getHeader("User-Agent");
		System.out.println(userAgent);

//		fileName = ""?
		BoardService service = new BoardServiceImpl();
		fileName = service.findOriginBy(fileName.substring(fileName.indexOf("/") + 1));

		if (userAgent.toLowerCase().contains("trident")) {
			fileName = URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");
		} else {
			fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		}

		// 응답헤더 설정
		resp.setContentType(mimeType);
		resp.setHeader("Content-DisPosition", "attachment; filename=" + fileName);

		// 출력 스트림 지정
		ServletOutputStream sos = resp.getOutputStream();
		int b;

		while ((b = fis.read()) != -1) {
			sos.write(b);
		}

		fis.close();
		sos.close();

	}
}