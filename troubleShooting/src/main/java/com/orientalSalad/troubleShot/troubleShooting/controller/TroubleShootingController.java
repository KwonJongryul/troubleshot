package com.orientalSalad.troubleShot.troubleShooting.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orientalSalad.troubleShot.global.dto.ResultDTO;
import com.orientalSalad.troubleShot.global.utill.Authentication;
import com.orientalSalad.troubleShot.troubleShooting.dto.RequestTroubleShootingDTO;
import com.orientalSalad.troubleShot.troubleShooting.dto.ResponseTroubleShootingDTO;
import com.orientalSalad.troubleShot.troubleShooting.dto.ResponseTroubleShootingListDTO;
import com.orientalSalad.troubleShot.troubleShooting.dto.SearchTroubleShootingDTO;
import com.orientalSalad.troubleShot.troubleShooting.dto.TroubleShootingDTO;
import com.orientalSalad.troubleShot.troubleShooting.dto.TroubleShootingReplyDTO;
import com.orientalSalad.troubleShot.troubleShooting.service.TroubleShootingService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/trouble-shooting")
@RequiredArgsConstructor
@Log4j2
public class TroubleShootingController {
	private final TroubleShootingService troubleShootingService;
	private final Authentication authentication;

	@Operation(summary = "트러블 슈팅 문서 등록")
	@PostMapping("")
	public ResponseEntity<?> insertTroubleShooting(
		HttpServletRequest request, @RequestBody RequestTroubleShootingDTO requestTroubleShootingDTO) throws Exception {
		log.info("====== 트러블 슈팅 문서 등록 시작 =====");
		//로그인 확인
		authentication.checkLogin(request,requestTroubleShootingDTO);
		//트러블슈팅 문서 등록
		troubleShootingService.insertTroubleShooting(requestTroubleShootingDTO);

		ResultDTO resultDTO = ResultDTO.builder()
			.success(true)
			.message("트러블 슈팅 문서 등록이 성공했습니다.")
			.build();
		
		log.info("====== 트러블 슈팅 문서 등록 끝 =====");
		return new ResponseEntity<ResultDTO>(resultDTO, HttpStatus.OK);
	}
	@Operation(summary = "트러블 슈팅 문서 삭제")
	@DeleteMapping("/{seq}")
	public ResponseEntity<?> deleteTroubleShooting(
		HttpServletRequest request,
		@RequestBody RequestTroubleShootingDTO requestTroubleShootingDTO,
		@PathVariable(name = "seq") Long seq) throws
		Exception {
		log.info("====== 트러블 슈팅 문서 삭제 시작 =====");
		//로그인 확인
		authentication.checkLogin(request,requestTroubleShootingDTO);
		//pk 설정
		requestTroubleShootingDTO.getTroubleShooting().setSeq(seq);
		//트러블슈팅 문서 삭제
		troubleShootingService.deleteTroubleShooting(requestTroubleShootingDTO);

		ResultDTO resultDTO = ResultDTO.builder()
			.success(true)
			.message("트러블 슈팅 문서 수정이 성공했습니다.")
			.build();

		log.info("====== 트러블 슈팅 문서 삭제 끝 =====");
		return new ResponseEntity<ResultDTO>(resultDTO, HttpStatus.OK);
	}
	@Operation(summary = "트러블 슈팅 문서 수정")
	@PutMapping("/{seq}")
	public ResponseEntity<?> updateTroubleShooting(
		HttpServletRequest request,
		@RequestBody RequestTroubleShootingDTO requestTroubleShootingDTO,
		@PathVariable(name = "seq") Long seq) throws
		Exception {
		log.info("====== 트러블 슈팅 문서 수정 시작 =====");
		//로그인 확인
		authentication.checkLogin(request,requestTroubleShootingDTO);
		//pk 설정
		requestTroubleShootingDTO.getTroubleShooting().setSeq(seq);
		//트러블슈팅 문서 수정
		troubleShootingService.updateTroubleShooting(requestTroubleShootingDTO);

		ResultDTO resultDTO = ResultDTO.builder()
			.success(true)
			.message("트러블 슈팅 문서 수정이 성공했습니다.")
			.build();

		log.info("====== 트러블 슈팅 문서 수정 끝 =====");
		return new ResponseEntity<ResultDTO>(resultDTO, HttpStatus.OK);
	}
	@Operation(summary = "트러블 슈팅 덧글 달기")
	@PostMapping("/{seq}/reply")
	public ResponseEntity<?> insertReply(@PathVariable(name = "seq") long seq,@RequestBody TroubleShootingReplyDTO troubleShootingReplyDTO) throws Exception {
		log.info("====== 트러블 슈팅 문서 덧글 달기 시작 =====");

		troubleShootingService.insertTroubleShooingReply(troubleShootingReplyDTO);

		ResultDTO resultDTO = ResultDTO.builder()
			.success(true)
			.message("트러블 슈팅 문서 덧글 등록이 성공했습니다.")
			.build();

		log.info("====== 트러블 슈팅 문서 덧글 달기 끝 =====");
		return new ResponseEntity<ResultDTO>(resultDTO, HttpStatus.OK);
	}

	@Operation(summary = "트러블 슈팅 단일 문서 상세 내용 검색")
	@GetMapping("/{seq}")
	public ResponseEntity<?> findTroubleShooting(@PathVariable(name = "seq") long seq) throws Exception {
		log.info("====== 트러블 슈팅 문서 pk 탐색 시작 =====");
		TroubleShootingDTO troubleShootingDTO = troubleShootingService.findTroubleShootingBySeq(seq);

		log.info(troubleShootingDTO);

		ResponseTroubleShootingDTO resultDTO = ResponseTroubleShootingDTO.builder()
			.success(true)
			.message(seq+"번 트러블 슈팅 문서 검색을 성공했습니다.")
			.troubleShooting(troubleShootingDTO)
			.build();
		
		log.info("====== 트러블 슈팅 문서 pk 탐색 끝 =====");
		return new ResponseEntity<ResponseTroubleShootingDTO>(resultDTO, HttpStatus.OK);
	}
	@Operation(summary = "트러블 슈팅 문서 목록 검색")
	@GetMapping("")
	public ResponseEntity<?> findTroubleShootingList(
		@ModelAttribute SearchTroubleShootingDTO searchParam) throws
		Exception {
		log.info("====== 트러블 슈팅 문서 목록 검색 시작 =====");
		log.info(searchParam);

		List<TroubleShootingDTO> troubleShootingDTOList = troubleShootingService.findTroubleShootingList(searchParam);
		Long totalCount = troubleShootingService.countTroubleShootingList(searchParam);

		ResponseTroubleShootingListDTO resultDTO = ResponseTroubleShootingListDTO.builder()
			.success(true)
			.message("트러블 슈팅 문서 목록 검색을 성공했습니다.")
			.totalCount(totalCount)
			.troubleShootingList(troubleShootingDTOList)
			.build();

		log.info("====== 트러블 슈팅 문서 목록 검색 끝 =====");
		return new ResponseEntity<ResponseTroubleShootingListDTO>(resultDTO, HttpStatus.OK);
	}
}
