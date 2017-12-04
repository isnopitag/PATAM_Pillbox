package com.carmona.pillbox.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carmona.pillbox.API.PillboxAPI;
import com.carmona.pillbox.Adapters.CitaRVAdapter;
import com.carmona.pillbox.Adapters.NotificacionRVAdapter;
import com.carmona.pillbox.Adapters.RecetaRVAdapter;
import com.carmona.pillbox.Models.Cita;
import com.carmona.pillbox.Models.Notificaciones;
import com.carmona.pillbox.Models.Receta;
import com.carmona.pillbox.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.carmona.pillbox.Config.Preferences.APP_USER;
import static com.carmona.pillbox.Config.Preferences.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NotificacionesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class NotificacionesFragment extends Fragment  implements NotificacionRVAdapter.NotificacionRVAdapterListener,
        Callback<List<Notificaciones>> {

    private int iduser;
    private ArrayList<Notificaciones> mNotificaciones;
    private static final String TAG = "notificacionFragment";
    private OnFragmentInteractionListener mListener;
    private NotificacionRVAdapter mNotificacionRVAdapter;
    Context context;
    RecyclerView recyclerView;

    public NotificacionesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_notificaciones, container, false);
        View view = inflater.inflate(R.layout.fragment_notificaciones, container, false);
        view.setTag(TAG);

        iduser = APP_USER;
        mNotificaciones= new ArrayList<Notificaciones>();

        recyclerView = (RecyclerView) view.findViewById(R.id.rvNotificaciones);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        mNotificacionRVAdapter = new NotificacionRVAdapter(view.getContext(), mNotificaciones);
        recyclerView.setAdapter(mNotificacionRVAdapter);

        notificaciones();


        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void OnItemClicked(Notificaciones aNotificaciones) {

    }

    public void notificaciones() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PillboxAPI pillboxAPI = retrofit.create(PillboxAPI.class);
        Call<List<Notificaciones>> call = pillboxAPI.notificaciones(""+iduser);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Notificaciones>> call, Response<List<Notificaciones>> response) {
        if(response.isSuccessful()) {
            List<Notificaciones> NotificacionesList = response.body();
            mNotificaciones.clear();
            for (Notificaciones notificaciones : NotificacionesList) {
                mNotificaciones.add(notificaciones);
            }
            mNotificacionRVAdapter.notifyDataSetChanged();
        } else {
            System.out.println(response.errorBody());
        }

    }

    @Override
    public void onFailure(Call<List<Notificaciones>> call, Throwable t) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
