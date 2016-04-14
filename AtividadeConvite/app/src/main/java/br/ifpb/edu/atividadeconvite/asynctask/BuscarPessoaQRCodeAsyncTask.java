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

public class BuscarPessoaQRCodeAsyncTask extends AsyncTask<Integer, Void, Response> {

    private PessoaCallBack pessoaCallBack;

    @Override
    protected Response doInBackground(Integer... numeros) {

        Response response = null;

        Integer valor = numeros[0];
        Gson gson = new Gson();

        Log.i("EditTextListener", "doInBackground (JSON): ");

        try {

            response = HttpService.sendJSONPostResquest("pesquisar/nome/", gson.toJson(valor));

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
