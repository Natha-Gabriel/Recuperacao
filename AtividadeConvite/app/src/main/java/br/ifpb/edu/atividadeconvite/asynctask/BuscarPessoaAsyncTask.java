package br.ifpb.edu.atividadeconvite.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import br.ifpb.edu.atividadeconvite.callback.PessoaCallBack;
import br.ifpb.edu.atividadeconvite.entidades.Pessoa;
import br.ifpb.edu.atividadeconvite.util.HttpService;
import br.ifpb.edu.atividadeconvite.util.Response;

public class BuscarPessoaAsyncTask  extends AsyncTask<Pessoa,Void,Response> {

    private PessoaCallBack pessoaCallBack;

    public BuscarPessoaAsyncTask(PessoaCallBack pessoaCallBack) {

        this.pessoaCallBack = pessoaCallBack;
    }

    @Override
    protected Response doInBackground(Pessoa... pessoas) {

        Response response = null;

        Pessoa pessoa  = pessoas[0];
        Gson gson = new Gson();

        Log.i("EditTextListener", "doInBackground (JSON): " + pessoa);

        try {

            response = HttpService.sendGetRequest("pesquisar/nome/", pessoa.getNome());

        } catch (IOException e) {

            Log.e("EditTextListener", e.getMessage());
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response response) {

        int codeHttp = response.getStatusCodeHttp();

        Log.i("EditTextListener", "Código HTTP: " + codeHttp
                + " Conteúdo: " + response.getContentValue());

        if (codeHttp != HttpURLConnection.HTTP_OK) {

            pessoaCallBack.errorBuscarNome(response.getContentValue());

        } else {

            Gson gson = new Gson();
            List<Pessoa> pessoas = gson.fromJson(response.getContentValue(),
                    new TypeToken<ArrayList<Pessoa>>(){}.getType());

            pessoaCallBack.backBuscarNome(pessoas);
        }
    }
}