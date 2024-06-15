package twitter_simplify.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twitter_simplify.springsecurity.entities.Role;
import twitter_simplify.springsecurity.entities.Tweet;


@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
