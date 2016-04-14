package br.ifpb.edu.atividadeconvite.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import br.ifpb.edu.atividadeconvite.R;
import br.ifpb.edu.atividadeconvite.adapter.PessoasCustomAdapter;
import br.ifpb.edu.atividadeconvite.callback.PessoaCallBack;
import br.ifpb.edu.atividadeconvite.entidades.Pessoa;

public class MainActivity extends Activity implements PessoaCallBack{

    List<Pessoa> pessoas;
    PessoasCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nomeButton = (Button) findViewById(R.id.buscaNome);
        Button qrcodeButton = (Button) findViewById(R.id.buscaQR);

        nomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuscarNomeActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });
        qrcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
                scanIntegrator.initiateScan();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        IntentResult scanningResult = IntentIntegrator.parseActivityResult
                (requestCode, resultCode, intent);

        if (scanningResult != null) {
            String conteudoLido = scanningResult.getContents();

        }else{

            Log.i("AtividadeConvite", "onActivityResult: Nenhum dado Recebido");

        }
    }

    @Override
    public void backBuscarNome(List<Pessoa> names) {

        this.pessoas.clear();
        this.pessoas.addAll(names);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void errorBuscarNome(String error) {

        pessoas.clear();
        adapter.notifyDataSetChanged();

        Log.i("AtividadeConvite", "onActivityResult: ErrorBuscaNome");
    }
}
