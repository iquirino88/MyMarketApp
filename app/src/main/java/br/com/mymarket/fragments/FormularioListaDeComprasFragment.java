package br.com.mymarket.fragments;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import br.com.mymarket.R;
import br.com.mymarket.activities.ListaComprasActivity;
import br.com.mymarket.enuns.HttpMethod;
import br.com.mymarket.helpers.FormularioListaCompraHelper;
import br.com.mymarket.model.Grupo;
import br.com.mymarket.model.ListaCompra;
import br.com.mymarket.navegacao.EstadoListaComprasActivity;
import br.com.mymarket.tasks.PersistObjectTask;

public class FormularioListaDeComprasFragment extends Fragment {
	
	private FormularioListaCompraHelper formularioListaCompraHelper;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_form_list_compras, container, false);
    	final ListaComprasActivity activity = ((ListaComprasActivity)this.getActivity());
        setGrupos(activity.getListaGrupos());
        formularioListaCompraHelper = new FormularioListaCompraHelper(view);
    	formularioListaCompraHelper.colocarListaComprasNoFormulario(activity.getItemSelecionado());
        Button button = (Button)view.findViewById(R.id.btn_form_lista_compras);
    	button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ListaCompra listaCompra = formularioListaCompraHelper.recuperarListaCompra();
                if (validarListaCompra(listaCompra)) {
                    String json = new Gson().toJson(listaCompra);
                    if(activity.getItemSelecionado() == null){
                        new PersistObjectTask(activity,json, HttpMethod.POST).execute();
                    }else{
                        new PersistObjectTask(listaCompra.getId(),activity,json,HttpMethod.PUT).execute();
                    }
                }
            }

            private boolean validarListaCompra(ListaCompra listaCompra) {
                if (listaCompra.getNome() == null || listaCompra.getNome().isEmpty()) {
                    Toast.makeText(activity, getString(R.string.form_lista_compra_validar_nome), Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (listaCompra.getGrupo() == null) {
                    Toast.makeText(activity, getString(R.string.form_lista_compra_validar_grupo), Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }

        });
        return view;
    }

    public void setGrupos(List<Grupo> list){
        Spinner spinner = (Spinner)view.findViewById(R.id.form_list_grupos);
        ArrayAdapter<Grupo> adapter = new ArrayAdapter<Grupo>(this.getActivity(), android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
