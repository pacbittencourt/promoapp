package br.com.pacbittencourt.promoapp.ui.produtos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.R;
import br.com.pacbittencourt.promoapp.domain.model.PromocoesItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

final class ProdutosAdapter
        extends RecyclerView.Adapter<ProdutosAdapter.ProdutosViewHolder> {

    private List<PromocoesItem> produtos;

    private OnProdutoClickListener listener;

    @Inject
    ProdutosAdapter() {
        this.produtos = new ArrayList<>();
    }

    void setPromocao(List<PromocoesItem> promocao) {
        this.produtos = promocao;
        notifyDataSetChanged();
    }

    interface OnProdutoClickListener {
        void onPordutoClick(PromocoesItem promocoesItem);
    }

    void setListener(OnProdutoClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ProdutosAdapter.ProdutosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produto, parent, false);
        return new ProdutosViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ProdutosAdapter.ProdutosViewHolder holder, int position) {
        PromocoesItem item = produtos.get(position);
        holder.setProduto(item);
    }

    @Override
    public int getItemCount() {
        return produtos != null ? produtos.size() : 0;
    }

    class ProdutosViewHolder
            extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_itemProduto_imagem)
        ImageView ivImagem;

        @BindView(R.id.tv_itemProduto_nome)
        TextView tvNome;

        @BindView(R.id.tv_itemProduto_preco)
        TextView tvPreco;

        private final OnProdutoClickListener listener;

        ProdutosViewHolder(View itemView, OnProdutoClickListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
        }

        void setProduto(PromocoesItem produto) {
            tvNome.setText(produto.getTitulo());
            tvPreco.setText(tvPreco.getContext().getString(R.string.itemProduto_preco, String
                    .valueOf(produto.getPreco())));
            setImagem(produto.getUrlImage());
        }

        void setImagem(String urlImagem) {
            Glide.with(ivImagem.getContext())
                    .load("http://" + urlImagem)
                    .into(ivImagem);
        }

        @OnClick(R.id.cv_itemProduto_container)
        void onProdutoClicked() {
            listener.onPordutoClick(produtos.get(getAdapterPosition()));
        }
    }
}
