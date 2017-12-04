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
import com.carmona.pillbox.Adapters.RecetaRVAdapter;
import com.carmona.pillbox.Models.Login;
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
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeFragment extends Fragment implements RecetaRVAdapter.RecetaRVAdapterListener,
        Callback<List<Receta>> {

    private OnFragmentInteractionListener mListener;
    private static final String TAG = "HomeFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

    private int iduser;
    private RecetaRVAdapter mRecetaRVAdapter;
    private ArrayList<Receta> mReceta;

    Context context;
    RecyclerView recyclerView;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view.setTag(TAG);

        iduser = APP_USER;
        mReceta= new ArrayList<Receta>();

        recyclerView = (RecyclerView) view.findViewById(R.id.rvHome);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        mRecetaRVAdapter = new RecetaRVAdapter(view.getContext(), mReceta);
        recyclerView.setAdapter(mRecetaRVAdapter);

        recetas();
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

    public void recetas() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PillboxAPI pillboxAPI = retrofit.create(PillboxAPI.class);
        Call<List<Receta>> call = pillboxAPI.recetas(""+iduser);
        call.enqueue(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResponse(Call<List<Receta>> call, Response<List<Receta>> response) {

        if(response.isSuccessful()) {
            List<Receta> RecetaList = response.body();
            mReceta.clear();
            for (Receta receta : RecetaList) {
                mReceta.add(receta);
            }
            mRecetaRVAdapter.notifyDataSetChanged();
        } else {
            System.out.println(response.errorBody());
        }

    }

    @Override
    public void onFailure(Call<List<Receta>> call, Throwable t) {

    }

    @Override
    public void OnItemClicked(Receta aReceta) {

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
