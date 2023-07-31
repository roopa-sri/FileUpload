package com.videostreaming.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.videostreaming.domain.Video;
import com.videostreaming.dto.ResponseDTO;

public interface VideoService {

	ResponseDTO<String> uploadFile(MultipartFile file) throws Exception;

	ResponseDTO<String> getFileUrl(String filename);

	ResponseDTO<List<Video>> getAllFiles();

}
