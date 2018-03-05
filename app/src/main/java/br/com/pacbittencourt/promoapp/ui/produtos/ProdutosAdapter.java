package br.com.pacbittencourt.promoapp.ui.produtos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.R;
import br.com.pacbittencourt.promoapp.domain.model.Produto;
import br.com.pacbittencourt.promoapp.domain.model.PromocaoItemAdapterSection;
import br.com.pacbittencourt.promoapp.domain.model.PromocoesItem;
import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.pacbittencourt.promoapp.ui.produtos.ProdutosAdapterItemType.CATEGORIA;
import static br.com.pacbittencourt.promoapp.ui.produtos.ProdutosAdapterItemType.ITEM;

final class ProdutosAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements Filterable {

    private List<PromocoesItem> produtos;

    private final List<ProdutosAdapterItem> produtosFiltrados;

    private final List<ProdutosAdapterItem> produtosFiltradosPesquisa;

    private OnProdutoClickListener listener;

    private Filter.FilterListener filterListener;

    private Filter filter;

    private List<String> categorias;

    private int categoriaSelecionada;

    @Inject
    ProdutosAdapter() {
        this.produtos = new ArrayList<>();
        this.produtosFiltrados = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.categoriaSelecionada = 0;
        this.produtosFiltradosPesquisa = new ArrayList<>();
    }

    void setPromocao(ResultsItem promocao) {
        if (promocao.getPromocoes() == null || promocao.getPromocoes().isEmpty())
            return;
        this.produtos = promocao.getPromocoes();
        filtrar(this.categoriaSelecionada);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return filter == null ? new ProdutosFilter() : filter;
    }

    void setCategorias(List<String> categorias) {
        categorias.add(0, "Todas");
        this.categorias = categorias;
    }

    List<String> getCategorias() {
        return categorias;
    }

    void filtrarCodigoNome(String newText) {
        if (newText.isEmpty()) {
            filtrar(this.categoriaSelecionada);
        } else {
            List<ProdutosAdapterItem> filtrados = new ArrayList<>();
            if (this.categoriaSelecionada != 0) {
                PromocaoItemAdapterSection section = new PromocaoItemAdapterSection
                        (((PromocaoItemAdapterSection) produtosFiltrados.get(0)).getNome());
                produtosFiltrados.remove(0);
                filtrados.add(section);
                for (ProdutosAdapterItem item : produtosFiltradosPesquisa) {
                    Produto produto = ((PromocoesItem) item).getProduto();
                    if (produto.getCodigoInterno().toLowerCase().contains(newText.toLowerCase()) ||
                            produto.getNomeProdutoBase().toLowerCase().contains(newText.toLowerCase()
                            )) {
                        filtrados.add(item);
                    }
                }
            } else {
                for (PromocoesItem item : produtos) {
                    Produto produto = item.getProduto();
                    if (produto.getCodigoInterno().toLowerCase().contains(newText.toLowerCase()) ||
                            produto.getNomeProdutoBase().toLowerCase().contains(newText.toLowerCase()
                            )) {
                        filtrados.add(item);
                    }
                }
            }
            produtosFiltrados.clear();
            produtosFiltrados.addAll(filtrados);
            notifyDataSetChanged();
        }
    }

    interface OnProdutoClickListener {
        void onProdutoClick(PromocoesItem promocoesItem);
    }

    void setListener(OnProdutoClickListener listener, Filter.FilterListener filterListener) {
        this.listener = listener;
        this.filterListener = filterListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CATEGORIA) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_categoria, parent, false);
            return new ItemCategoriaViewHolder(view);
        } else if (viewType == ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_produto, parent, false);
            return new ProdutosViewHolder(view, listener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == ITEM) {
            PromocoesItem item = (PromocoesItem) produtosFiltrados.get(position);
            ((ProdutosViewHolder) holder).setProduto(item);
        } else if (viewType == CATEGORIA) {
            PromocaoItemAdapterSection categoria = (PromocaoItemAdapterSection) produtosFiltrados
                    .get(position);
            ((ItemCategoriaViewHolder) holder).setCategoria(categoria.getNome());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return produtosFiltrados.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return produtosFiltrados.size();
    }

    void filtrar(int categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
        String parametro = categorias.get(categoriaSelecionada);
        getFilter().filter(parametro, filterListener);
    }

    private void atualizarProdutos(List<PromocoesItem> promocoesItems) {
        if (promocoesItems == null || promocoesItems.isEmpty()) return;
        produtosFiltrados.clear();
        produtosFiltradosPesquisa.clear();

        PromocaoItemAdapterSection section = new PromocaoItemAdapterSection(promocoesItems.get(0)
                .getCategoria().getNome());
        boolean sectionAdded = false;
        for (PromocoesItem promocoesItem : promocoesItems) {
            if (!promocoesItem.getCategoria().getNome().equals(section.getNome())) {
                section = new PromocaoItemAdapterSection(promocoesItem.getCategoria().getNome());
                sectionAdded = false;
            }

            if (!sectionAdded) {
                produtosFiltrados.add(section);
                sectionAdded = true;
            }
            produtosFiltrados.add(promocoesItem);
            produtosFiltradosPesquisa.add(promocoesItem);
        }
        notifyDataSetChanged();
    }

    private final class ProdutosFilter
            extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            results.count = 0;

            String categoria = charSequence.toString();

            List<PromocoesItem> itens = new ArrayList<>();

            if (!categoria.equals("Todas")) {
                for (PromocoesItem produto : produtos) {
                    if (produto.getCategoria().getNome().equals(categoria))
                        itens.add(produto);
                }
            } else {
                itens = produtos;
            }

            List<PromocoesItem> resultadosOrdenados = new ArrayList<>();
            for (String str : categorias) {
                for (PromocoesItem item : itens) {
                    if (str.equals(item.getCategoria().getNome()))
                        resultadosOrdenados.add(item);
                }
            }

            results.values = resultadosOrdenados;
            results.count = itens.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            atualizarProdutos((List<PromocoesItem>) filterResults.values);
        }
    }

    final class ProdutosViewHolder
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
            listener.onProdutoClick((PromocoesItem) produtosFiltrados.get(getAdapterPosition()));
        }
    }

    final class ItemCategoriaViewHolder
            extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_categoria_nome)
        TextView tvNome;

        ItemCategoriaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setCategoria(String categoria) {
            tvNome.setText(categoria);
        }
    }
}
