package sopt._39sopkathonserverandroid1.domain.post.exception;
import sopt._39sopkathonserverandroid1.domain.post.code.PostErrorCode;
import sopt._39sopkathonserverandroid1.global.exception.BusinessException;
public class PostNotFoundException extends BusinessException { public PostNotFoundException() { super(PostErrorCode.POST_NOT_FOUND); } }
