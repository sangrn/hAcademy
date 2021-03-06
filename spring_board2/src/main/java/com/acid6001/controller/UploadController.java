package com.acid6001.controller;

import java.io.File; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.acid6001.domain.AttachDto;
import com.acid6001.mapper.AttachMapper;
import com.acid6001.service.BoardService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	@Getter
	private static String uploadFolder = "c:\\upload";
	@Setter @Autowired
	private AttachMapper attachMapper;
	
	
	@GetMapping("upload")
	public String uploadForm() {
		log.info("uploadForm");
		return "uploadAjax";
	}

	@PostMapping("upload")
	public @ResponseBody List<AttachDto> upload(List<MultipartFile> files, Model model) throws IOException {

		File uploadPath = new File(uploadFolder, getFolder());
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		List<AttachDto> list = new ArrayList<>();
		for (MultipartFile f : files) {
			String uuid = UUID.randomUUID().toString();
			String origin = f.getOriginalFilename();
			String ext = origin.substring(origin.lastIndexOf(".")+ 1 );
			Long size = f.getSize();
			
			
			String resultName = uuid + "." + ext;

			File file = new File(uploadPath, resultName);
			String mime = getMime(file);
			boolean image = isImage(file);
			
			// ????????? ??????, ???????????? ?????? ????????? ??????
			if (image) {
				FileOutputStream fos = new FileOutputStream(new File(uploadPath, "s_" + resultName));
				Thumbnailator.createThumbnail(f.getInputStream(), fos, 100, 100);
				fos.close();
			}
			f.transferTo(file);
			AttachDto dto = new AttachDto(origin, getFolder(), uuid, ext, mime, size, image);
			log.info(dto);
			list.add(dto);
			
		}
		return list;
	}
	
	
	@GetMapping("display")
	public ResponseEntity<byte[]> getFile(String fileName) throws IOException {
		log.info("fileName ::" + fileName);
		File file = new File(uploadFolder, fileName);
		log.info("file ::"+ file);
		
		ResponseEntity<byte[]> ret = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", Files.probeContentType(file.toPath()));
		ret = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		return ret;
	}
	
	@GetMapping("download")
	public ResponseEntity<Resource> download(String fileName, @RequestHeader("User-Agent") String userAgent) throws UnsupportedEncodingException {
		Resource resource = new FileSystemResource(uploadFolder +"/"+ fileName);
		if(!resource.exists()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		fileName = fileName.substring(fileName.indexOf("/") + 1);
		
		String resourceName = attachMapper.findBy(new AttachDto(fileName).getUuid()).getOrigin();
		if (userAgent.toLowerCase().contains("trident")) {
			resourceName = URLEncoder.encode(resourceName, "utf-8").replaceAll("\\+", "%20");
		} else {
			resourceName = new String(resourceName.getBytes("utf-8"), "iso-8859-1");
		}
		
		headers.add("Content-Type", "application/octet-stream");
		headers.add("Content-DisPosition", "attachment; filename=" + resourceName);
		
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	@PostMapping("deleteFile")
	public @ResponseBody String deleteFile(String fileName, Boolean image){
		new File(uploadFolder , fileName).delete();
		// ????????? ??????
		if(image) new File(uploadFolder ,new AttachDto(fileName).getThumb()).delete();
		return "deleted";
	}
	

	private String getFolder() {
		return new SimpleDateFormat(
//				String.format("yyyy%sMM%sdd", File.separator, File.separator)).format(new Date());
		"yyyy/MM/dd").format(new Date());
	}

	private boolean isImage(File file) throws IOException {
		return getMime(file) != null &&
				getMime(file).startsWith("image");
	}
	
	private String getMime(File file) throws IOException {
		return Files.probeContentType(file.toPath()) ;
	}
	
}
