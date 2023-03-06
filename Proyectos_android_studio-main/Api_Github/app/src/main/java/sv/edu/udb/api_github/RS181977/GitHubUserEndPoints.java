package sv.edu.udb.api_github.RS181977;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubUserEndPoints {
    @GET("/users/{user}")
    Call<GitHubUser> getUser(@Path("user") String user);
}
