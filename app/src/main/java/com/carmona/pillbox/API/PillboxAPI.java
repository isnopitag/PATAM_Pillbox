package com.carmona.pillbox.API;

import com.carmona.pillbox.Models.Cita;
import com.carmona.pillbox.Models.Login;
import com.carmona.pillbox.Models.Notificaciones;
import com.carmona.pillbox.Models.Receta;

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

    @GET("Server.php?action=recetas")
    Call<List<Receta>> recetas(@Query("id") String iduser);

    @GET("Server.php?action=citas")
    Call<List<Cita>> citas(@Query("id") String iduser);

    @GET("Server.php?action=enviar")
    Call<List<Notificaciones>> notificacion(@Query("id") String iduser,@Query("mensaje") String mensaje);

    @GET("Server.php?action=noti")
    Call<List<Notificaciones>> notificaciones(@Query("id") String iduser);
}
