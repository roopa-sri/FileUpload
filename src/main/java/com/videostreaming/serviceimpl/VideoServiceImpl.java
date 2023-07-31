package com.videostreaming.serviceimpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.videostreaming.constant.ApplicationConstant;
import com.videostreaming.domain.Video;
import com.videostreaming.dto.ResponseDTO;
import com.videostreaming.repo.VideoRepository;
import com.videostreaming.service.VideoService;
import com.videostreaming.util.CommonUtil;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoRepository videoRepository;

	private final ResourceLoader resourceLoader;

	public VideoServiceImpl(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Value("${upload.directory}")
	private String uploadDirectory;

	@Override
	public ResponseDTO<String> uploadFile(MultipartFile file) throws Exception {

		if (file.isEmpty()) {
			return new ResponseDTO<>(false, ApplicationConstant.ERROR_CODE_404,
					CommonUtil.populateMessage(ApplicationConstant.SUCCESS_GET), "File upload failed.");
		}
		try {
			Path filePath = Paths.get(uploadDirectory, file.getOriginalFilename());
			Files.write(filePath, file.getBytes());

			Video video = new Video();
			video.setFileName(file.getOriginalFilename());
			video.setFileUrl(filePath.toString());
			videoRepository.save(video);
			return new ResponseDTO<>(false, ApplicationConstant.SUCCESS_CODE_200,
					CommonUtil.populateMessage(ApplicationConstant.SUCCESS_GET), "File uploaded successfully.");
		} catch (Exception exception) {
			throw new Exception(exception);
		}

	}

	private String getResourceUrl(String fileUrl) {
		try {
			Resource resource = resourceLoader.getResource("file:" + fileUrl);
			File file = resource.getFile();
			return file.toURI().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Error occurred while loading the file.";
		}
	}

	@GetMapping("/files/{filename}")
	public ResponseDTO<String> getFileUrl(@PathVariable String filename) {
		Optional<Video> videoEntityOptional = videoRepository.findByFilename(filename);
		if (videoEntityOptional.isPresent()) {
			Video fileEntity = videoEntityOptional.get();
			String fileUrl = fileEntity.getFileUrl();
			return new ResponseDTO<>(false, ApplicationConstant.SUCCESS_CODE_200,
					CommonUtil.populateMessage(ApplicationConstant.SUCCESS_GET), getResourceUrl(fileUrl));
		} else {
			return new ResponseDTO<>(false, ApplicationConstant.ERROR_CODE_404,
					CommonUtil.populateMessage(ApplicationConstant.SUCCESS_GET), "File Not found.");
		}
	}

	@Override
	public ResponseDTO<List<Video>> getAllFiles() {
		List<Video> videoList = new ArrayList<>();
		videoList = videoRepository.findAll();
		return new ResponseDTO<>(false, ApplicationConstant.SUCCESS_CODE_200,
				CommonUtil.populateMessage(ApplicationConstant.SUCCESS_GET), videoList);
	}

}
