package br.com.pacbittencourt.promoapp.ui.produtos;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.pacbittencourt.promoapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public final class ProdutosCategoriaFiltroDialogFragment
        extends DialogFragment
        implements View.OnClickListener {

    private static final String LISTA_CATEGORIAS = "lista_categorias";
    private static final String CATEGORIA_SELECIONADA = "categoria_selecionada";

    @BindView(R.id.rg_categoriaFiltro_categorias)
    RadioGroup rgCategorias;

    private FiltroCategoriaDialogListener listener;

    interface FiltroCategoriaDialogListener {
        void onFiltroCategoriaDialogConfirmClick(int selecionado);
    }

    public static ProdutosCategoriaFiltroDialogFragment newInstance(List<String> categorias, int
            categoriaSelecionada) {
        ProdutosCategoriaFiltroDialogFragment fragment = new
                ProdutosCategoriaFiltroDialogFragment();
        Bundle bundle = new Bundle();
        ArrayList<String> listaCategorias = categorias != null ?
                new ArrayList<String>(categorias) : null;
        bundle.putStringArrayList(LISTA_CATEGORIAS, listaCategorias);
        bundle.putInt(CATEGORIA_SELECIONADA, categoriaSelecionada);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(), R.layout.fragment_dialog_filtro_categoria, null);
        ButterKnife.bind(this, view);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        setCategorias();

        return dialog;
    }

    private void setCategorias() {
        List<String> categorias = getArguments().getStringArrayList(LISTA_CATEGORIAS);

        if (categorias == null) {
            dismiss();
            return;
        }

        int categoriaSelecionada = getArguments().getInt(CATEGORIA_SELECIONADA);

        for (int i = 0; i < categorias.size(); i++) {
            RadioButton radioButton = createRadioButton(categorias.get(i), i);

            radioButton.setOnClickListener(this);
            if (categoriaSelecionada == i) {
                radioButton.setChecked(true);
            }
            rgCategorias.addView(radioButton);
        }
    }

    private RadioButton createRadioButton(String text, int id) {
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT);

        RadioButton radioButton = (RadioButton) View.inflate(
                getActivity(), R.layout.categoria_filtro_radio_button, null);
        radioButton.setId(id);
        radioButton.setText(text);
        radioButton.setLayoutParams(params);

        return radioButton;
    }

    public void setListener(FiltroCategoriaDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        int seleciontado = view.getId();
        listener.onFiltroCategoriaDialogConfirmClick(seleciontado);
        dismiss();
    }
}
