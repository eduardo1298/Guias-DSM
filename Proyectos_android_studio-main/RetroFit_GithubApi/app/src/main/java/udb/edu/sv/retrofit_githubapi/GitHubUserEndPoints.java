package udb.edu.sv.retrofit_githubapi;


import udb.edu.sv.retrofit_githubapi.GitHubUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface GitHubUserEndPoints {


    @GET("/users/{user}")


    Call<GitHubUser> getUser(@Path("user") String user);
}
