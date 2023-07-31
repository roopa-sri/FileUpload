package com.videostreaming.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.videostreaming.domain.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

	@Query("select v from Video v where fileName = :filename")
	Optional<Video> findByFilename(String filename);

}