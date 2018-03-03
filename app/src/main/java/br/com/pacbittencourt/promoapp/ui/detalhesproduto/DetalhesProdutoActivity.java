package br.com.pacbittencourt.promoapp.ui.detalhesproduto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.R;
import br.com.pacbittencourt.promoapp.domain.model.PromocoesItem;
import br.com.pacbittencourt.promoapp.injection.component.ActivityComponent;
import br.com.pacbittencourt.promoapp.ui.base.BaseMvpActivity;
import br.com.pacbittencourt.promoapp.ui.utils.Navigator;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesProdutoActivity
        extends BaseMvpActivity<DetalhesProdutoView, DetalhesProdutoPresenter>
        implements DetalhesProdutoView {

    @Inject
    DetalhesProdutoPresenter detalhesProdutoPresenter;

    @BindView(R.id.iv_detalhesProduto_imagem)
    ImageView ivImagem;

    @BindView(R.id.tv_detalhesProduto_nome)
    TextView tvNomeProduto;

    @BindView(R.id.tv_detalhesProduto_preco)
    TextView tvPrecoProduto;

    @BindView(R.id.tv_detalhesProduto_validade)
    TextView tvValidade;

    @BindView(R.id.tv_detalhesProduto_codigo)
    TextView tvCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getBundleExtra(Navigator.KEY_PROMOCAO_ITEM);
        PromocoesItem item = bundle.getParcelable(Navigator.KEY_PROMOCAO_ITEM_BUNDLE);
        setupViews(item);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(item.getTitulo());
        }

    }

    private void setupViews(PromocoesItem item) {
        Glide.with(this)
                .load("http://" + item.getUrlImage())
                .into(ivImagem);
        tvNomeProduto.setText(item.getTitulo());
        tvPrecoProduto.setText(getString(R.string.itemProduto_preco, String.valueOf(item.getPreco
                ())));
        tvValidade.setText(getString(R.string.detalhesProduto_validade, formatDate(item
                .getDataInicio()), formatDate(item.getDataTermino())));
        tvCodigo.setText(getString(R.string.itemProduto_codigo, item.getProduto().getCodigoInterno()));
    }

    private String formatDate(String data) {
        String novaData = data.split("T")[0];
        String[] dataFinal = novaData.split("-");
        return dataFinal[2] + "/" + dataFinal[1] + "/" + dataFinal[0];
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public DetalhesProdutoPresenter createPresenter() {
        return detalhesProdutoPresenter;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
