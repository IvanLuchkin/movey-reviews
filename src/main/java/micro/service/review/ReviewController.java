package micro.service.review;

import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.ReviewResultsPage;
import com.uwetrottmann.tmdb2.services.MoviesService;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

@RestController
@RequestMapping("/review")
public class ReviewController {
    Tmdb tmdb = new Tmdb("df42876914d18a938f38411b2d1042e5");
    MoviesService moviesService = tmdb.moviesService();

    @GetMapping("/{movieId}")
    public ResponseEntity<ReviewResultsPage> getReviews(@PathVariable Integer movieId) throws Exception {
        Response<ReviewResultsPage> response = moviesService.reviews(movieId, 1, "").execute();
        if (response.isSuccessful()) {
            ReviewResultsPage reviews = response.body();
            return ResponseEntity.of(Optional.ofNullable(reviews));
        } else {
            throw new Exception();
        }
    }
}
