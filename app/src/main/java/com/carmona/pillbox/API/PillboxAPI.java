package com.carmona.pillbox.API;

import com.carmona.pillbox.Models.Login;

import org.w3c.dom.Comment;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by carmona on 2/12/17.
 */

public interface PillboxAPI {

    @GET("Server.php?action=login")
    Call<List<Login>> login(@Query("user") String user,@Query("pass") String pass);
}
