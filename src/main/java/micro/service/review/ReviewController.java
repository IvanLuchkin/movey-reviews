package micro.service.review;

import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.ReviewResultsPage;
import com.uwetrottmann.tmdb2.services.MoviesService;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Value("${api.key}")
    private String apiKey;

    Tmdb tmdb = new Tmdb(apiKey);
    MoviesService moviesService = tmdb.moviesService();

    private static final Logger log = Logger.getLogger(String.valueOf(ReviewController.class));

    @GetMapping("/{movieId}")
    public ResponseEntity<ReviewResultsPage> getReviews(@PathVariable Integer movieId) throws Exception {
        log.info("get review for  - " + movieId);
        Response<ReviewResultsPage> response = moviesService.reviews(movieId, 1, "").execute();
        if (response.isSuccessful()) {
            ReviewResultsPage reviews = response.body();
            return ResponseEntity.of(Optional.ofNullable(reviews));
        } else {
            log.warning("response not successful");
            throw new Exception();
        }
    }
}
