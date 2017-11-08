package com.jesusramirez.proyecto.views.fragmentsMaestro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jesusramirez.proyecto.BaseDeDatos.CRUD_Tablas;
import com.jesusramirez.proyecto.Modelos.Grupos;
import com.jesusramirez.proyecto.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MaestroCrearGrupoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MaestroCrearGrupoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaestroCrearGrupoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Instanciar controles
    Spinner spinnerHora;
    View vista;
    CRUD_Tablas datos;

    public static EditText nombreMateria,salon,capacidad,faltasPermitidas;
    public static Spinner hora;
    public static CheckBox lun,mar,mie,jue,vie,sab,dom;
    public static String horaSeleccionada,diasClase;

    public MaestroCrearGrupoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MaestroCrearGrupoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MaestroCrearGrupoFragment newInstance(String param1, String param2) {
        MaestroCrearGrupoFragment fragment = new MaestroCrearGrupoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_maestro_crear_grupo, container, false);

        //Enlazar controles
        datos = CRUD_Tablas.obtenerInstancia(getContext());
        nombreMateria = (EditText) vista.findViewById(R.id.etnombregrupo_creargrupo);
        salon = (EditText) vista.findViewById(R.id.etsalon_creargrupo);
        capacidad = (EditText) vista.findViewById(R.id.etcapacidad_creargrupo);
        faltasPermitidas = (EditText) vista.findViewById(R.id.etfaltaspermitidas_creargrupo);
        lun = (CheckBox) vista.findViewById(R.id.checklunes_creargrupos);
        mar = (CheckBox) vista.findViewById(R.id.checkmartes_creargrupos);
        mie = (CheckBox) vista.findViewById(R.id.checkmiercoles_creargrupos);
        jue = (CheckBox) vista.findViewById(R.id.checkjueves_creargrupos);
        vie = (CheckBox) vista.findViewById(R.id.checkviernes_creargrupos);
        sab = (CheckBox) vista.findViewById(R.id.checksabado_creargrupos);
        dom = (CheckBox) vista.findViewById(R.id.checkdomingo_creargrupos);
        spinnerHora = (Spinner) vista.findViewById(R.id.spinnerHoraClase);

        ArrayList<String>listaHoraClase=new ArrayList<String>();
        listaHoraClase.add("Hora de clase");
        for (int i=1;i<25;i++){
            listaHoraClase.add(i+":00");
        }
        ArrayAdapter<CharSequence>adaptadorHora =new  ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,listaHoraClase);
        spinnerHora.setAdapter(adaptadorHora);
        spinnerHora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Hora: "+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                horaSeleccionada = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                horaSeleccionada = "";
            }
        });
        return vista;
    }

    public void diasDeClase(){

        if (lun.isChecked()==true){
            diasClase+="L";
        }
        if (mar.isChecked()){
            diasClase+="M";
        }
        if (mie.isChecked()){
            diasClase+="M";
        }
        if (jue.isChecked()){
            diasClase+="J";
        }
        if (vie.isChecked()){
            diasClase+="V";
        }
        if (sab.isChecked()){
            diasClase+="S";
        }
        if (dom.isChecked()){
            diasClase+="D";
        }
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
