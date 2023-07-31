package com.videostreaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.videostreaming.domain.Video;
import com.videostreaming.dto.ResponseDTO;
import com.videostreaming.service.VideoService;

@Controller
public class VideoController {

	@Autowired
	private VideoService videoService;

	@PostMapping("/upload")
	public ResponseEntity<ResponseDTO<String>> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
		return new ResponseEntity<>(videoService.uploadFile(file), HttpStatus.OK);
	}

	@GetMapping("/files/{filename}")
	public ResponseEntity<ResponseDTO<String>> getFileUrl(@PathVariable String filename) {
		return new ResponseEntity<>(videoService.getFileUrl(filename), HttpStatus.OK);
	}

	@GetMapping("/files")
	public ResponseEntity<ResponseDTO<List<Video>>> getAllFiles() {
		return new ResponseEntity<>(videoService.getAllFiles(), HttpStatus.OK);
	}
}