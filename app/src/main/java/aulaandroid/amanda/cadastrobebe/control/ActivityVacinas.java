package aulaandroid.amanda.cadastrobebe.control;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import aulaandroid.amanda.cadastrobebe.R;
import aulaandroid.amanda.cadastrobebe.adapter.ListAdapter_Vacinas;
import aulaandroid.amanda.cadastrobebe.dao.Contract;
import aulaandroid.amanda.cadastrobebe.dao.VacinasDAO;
import aulaandroid.amanda.cadastrobebe.model.Vacinas;

/**
 * Created by amanda on 23/03/16.
 */
public class ActivityVacinas extends Activity {

    private final String TAG = "ActivityVacinas";
    private ListView lvlistagem;
    private TextView tvIdade;
    private Vacinas vacina;

    String[] idades = {"Ao nascer", "2 meses", "3 meses", "4 meses", "5 meses", "6 meses", "9 meses", "12 meses", "15 meses", "4 anos", "10 anos"};
    int index = 0; //índice para o array

    VacinasDAO vaciDAO = VacinasDAO.getInstance(ActivityVacinas.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacinas);

        lvlistagem = (ListView) findViewById(R.id.listViewActivityVacinas);
        tvIdade = (TextView) findViewById(R.id.textView14);

        vacina = new Vacinas();
        VacinasDAO.getInstance(ActivityVacinas.this).open();


        tvIdade.setText(idades[index]);
        carregarListView(vaciDAO.getListaByIdade(idades[index]));


        lvlistagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                //bb.setId_bebe(cursor.getColumnIndexOrThrow(Contract.Bebe.COLUNA_ID));

                vacina.setId_vacina(cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Vacinas.COLUNA_ID)));
                vacina.setDoenca_evitada(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Vacinas.COLUNA_DOENCAS)));
                vacina.setDoses(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Vacinas.COLUNA_DOSES)));
                vacina.setIdade(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Vacinas.COLUNA_IDADE)));
                vacina.setVacinas(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Vacinas.COLUNA_VACINAS)));
                if(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Vacinas.COLUNA_REALIZADA)).equals(" "))
                   vacina.setRealizada("✔");
                else
                    vacina.setRealizada(" ");
                vaciDAO.alterar(vacina);
                carregarListView(vaciDAO.getListaByIdade(idades[index]));
            }
        });

    }

    public void avancarDireita (View view) {
        index++;
        if(index < 11) {
            tvIdade.setText(idades[index]);
            carregarListView(vaciDAO.getListaByIdade(idades[index]));
        }else{
            index = 0;
            tvIdade.setText(idades[index]);
            carregarListView(vaciDAO.getListaByIdade(idades[index]));
        }
    }

    public void voltarEsquerda (View view) {
        index--;
        if(index < 0) {
            index = 10;
            tvIdade.setText(idades[index]);
            carregarListView(vaciDAO.getListaByIdade(idades[index]));
        } else{
            tvIdade.setText(idades[index]);
            carregarListView(vaciDAO.getListaByIdade(idades[index]));
        }

    }


    public void carregarListView(Cursor cursor){

        final String[] DE = {Contract.Vacinas.COLUNA_REALIZADA, Contract.Vacinas.COLUNA_VACINAS, Contract.Vacinas.COLUNA_DOSES};
        final int[] PARA = {R.id.tv_realizado, R.id.tv_vacinas, R.id.tv_doses};
        ListAdapter_Vacinas dadosAdapter = new ListAdapter_Vacinas(
                ActivityVacinas.this,
                R.layout.vacinas_listview,
                cursor,
                DE,
                PARA,
                0);
        Log.d(TAG, "tamanho cursor: " + cursor.getCount());
        while(cursor.moveToNext()){
            Log.d(TAG, "" + cursor.getString(cursor.getColumnIndex(Contract.Vacinas.COLUNA_VACINAS)));
            Log.d(TAG, "" + cursor.getString(cursor.getColumnIndex(Contract.Vacinas.COLUNA_DOSES)));
            Log.d(TAG, "" + cursor.getString(cursor.getColumnIndex(Contract.Vacinas.COLUNA_DOENCAS)));
        }
        lvlistagem.setAdapter(dadosAdapter);
    }


}










    //SELECT distinct idade FROM vacinas
























