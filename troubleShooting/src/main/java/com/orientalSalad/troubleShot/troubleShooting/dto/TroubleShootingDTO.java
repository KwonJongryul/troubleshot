package com.orientalSalad.troubleShot.troubleShooting.dto;

import java.util.List;
import java.util.Set;

import com.orientalSalad.troubleShot.global.dto.BaseDTO;
import com.orientalSalad.troubleShot.member.dto.SimpleMemberDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TroubleShootingDTO extends BaseDTO {
	@Schema(description = "제목")
	private String title;
	@Schema(description = "카테고리")
	private String category;
	@Schema(description = "내용")
	private String context;
	@Schema(description = "의존성")
	private String dependency;
	@Schema(description = "공개범위 (0 : 전체공개, 1 : 비공개)")
	private int scope;
	@Schema(description = "작성자")
	private SimpleMemberDTO writer;
	@Schema(description = "해결 여부")
	private boolean solved;
	@Schema(description = "조회 수",hidden = true)
	private int viewCount;
	@Schema(description = "좋아요 수",hidden = true)
	private int likeCount;
	@Schema(description = "덧글 수",hidden = true)
	private int replyCount;
	@Schema(description = "솔루션 수",hidden = true)
	private int answerCount;
	@Schema(description = "다중 태그")
	private List<String> tags;
	@Schema(description = "덧글 목록",hidden = true)
	private Set<TroubleShootingReplyDTO> replies;
	@Schema(description = "솔루션 목록",hidden = true)
	private Set<TroubleShootingAnswerDTO> answers;
	@Schema(description = "로그인한 유저가 좋아요를 눌렀는지 확인",hidden = true)
	private boolean loginLike;
	@Schema(description = "북마크",hidden = true)
	private boolean favorite;
	@Schema(description = "등록한 기기")
	private long postType;


}