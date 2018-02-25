package br.com.pacbittencourt.promoapp.ui.promocoes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.R;
import br.com.pacbittencourt.promoapp.domain.model.Resultados;
import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

final class PromocoesAdapter
        extends RecyclerView.Adapter<PromocoesAdapter.PromocoesViewHolder> {

    private List<ResultsItem> promocoes;

    private PromocaoClickListener listener;

    @Inject
    PromocoesAdapter() {
        this.promocoes = new ArrayList<>();
    }

    interface PromocaoClickListener {
        void onPromocaoClick(ResultsItem promocao, int position);
    }

    @Override
    public PromocoesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promocao, parent, false);
        return new PromocoesViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(PromocoesViewHolder holder, int position) {
        ResultsItem item = promocoes.get(position);
        holder.setPromocao(item);
    }

    @Override
    public int getItemCount() {
        return promocoes != null ? promocoes.size() : 0;
    }

    void setListener(PromocaoClickListener listener) {
        this.listener = listener;
    }

    public void setData(Resultados data) {
        this.promocoes = data.getResults();
        notifyDataSetChanged();
    }

    class PromocoesViewHolder
            extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_itemPromocao_nomePromocao)
        TextView tvNomePromocao;

        private final PromocaoClickListener listener;

        PromocoesViewHolder(View itemView, PromocaoClickListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
        }

        void setPromocao(ResultsItem resultsItem) {
            tvNomePromocao.setText(resultsItem.getNome());
        }

        @OnClick(R.id.btn_itemPromocao_verPromocao)
        void onVerPromocaoClicked() {
            listener.onPromocaoClick(promocoes.get(getAdapterPosition()),
                    getAdapterPosition());
        }
    }
}
