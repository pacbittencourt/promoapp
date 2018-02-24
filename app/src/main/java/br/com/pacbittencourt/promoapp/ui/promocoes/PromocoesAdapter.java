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
import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import butterknife.BindView;
import butterknife.ButterKnife;

final class PromocoesAdapter
        extends RecyclerView.Adapter<PromocoesAdapter.PromocoesViewHolder> {

    private List<ResultsItem> promocoes;

    @Inject
    PromocoesAdapter() {
        this.promocoes = new ArrayList<>();
    }

    @Override
    public PromocoesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promocao, parent, false);
        return new PromocoesViewHolder(view);
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

    public void setData(List<ResultsItem> data) {
        this.promocoes = data;
        notifyDataSetChanged();
    }

    class PromocoesViewHolder
            extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_itemPromocao_nomePromocao)
        TextView tvNomePromocao;

        PromocoesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setPromocao(ResultsItem resultsItem) {
            tvNomePromocao.setText(resultsItem.getNome());
        }
    }
}
