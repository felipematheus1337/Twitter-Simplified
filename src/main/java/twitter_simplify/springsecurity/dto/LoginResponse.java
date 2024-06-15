package twitter_simplify.springsecurity.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
