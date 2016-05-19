package aulaandroid.amanda.cadastrobebe.control;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import aulaandroid.amanda.cadastrobebe.R;
import aulaandroid.amanda.cadastrobebe.adapter.ListAdapter_Agenda;
import aulaandroid.amanda.cadastrobebe.dao.AgendaDAO;
import aulaandroid.amanda.cadastrobebe.dao.Contract;
import aulaandroid.amanda.cadastrobebe.model.Agenda;

/**
 * Created by amanda on 13/04/16.
 */
public class ActivityAgenda extends Activity implements AdapterView.OnItemClickListener, ActionBar.TabListener {

    private String TAG = "ActivityAgenda";
    private AgendaDAO agDAO;
    private Agenda ag;
    private EditText etDescricao, etData, etHorario;
    private ListView lvCompromissos;
    ActionBar.Tab tab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        agDAO = AgendaDAO.getInstance(ActivityAgenda.this);
        agDAO.open();

        ag = new Agenda();

        etDescricao = (EditText) findViewById(R.id.editText_desc);
        etData = (EditText) findViewById(R.id.editText_data);
        etHorario = (EditText) findViewById(R.id.editText_hora);
        lvCompromissos = (ListView) findViewById(R.id.listViewCompromissos);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Agenda");
        actionBar.setIcon(R.drawable.ic_action_good);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1 = actionBar.newTab().setText("Compromissos");
        tab1.setTabListener(ActivityAgenda.this);
        actionBar.addTab(tab1);

        tab2 = actionBar.newTab().setText("Adicionar Compromisso");
        tab2.setTabListener(ActivityAgenda.this);
        actionBar.addTab(tab2);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        agDAO.close();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        switch (tab.getPosition()){
            case 0:{
                setContentView(R.layout.activity_listagenda);


                lvCompromissos = (ListView) findViewById(R.id.listViewCompromissos);
                lvCompromissos.setOnItemClickListener(ActivityAgenda.this);

                carregarListView(agDAO.getLista());


                break;
            }
            case 1:{
                setContentView(R.layout.activity_agenda);


                etDescricao = (EditText) findViewById(R.id.editText_desc);
                etData = (EditText) findViewById(R.id.editText_data);
                etHorario = (EditText) findViewById(R.id.editText_hora);

                controlarEdicao(true);

                break;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_agenda, menu);
                return true;
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuitem_salvar:{
                salvar();
                return true;
            }
            case R.id.menuitem_excluir:{
                excluir();
                return true;
            }
            case R.id.menuitem_cancelar:{
                cancelar();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adpListView, View view, int position,
                            long id) {

        Cursor cursor = (Cursor) adpListView.getItemAtPosition(position);

        tab2.select();
        onTabSelected(tab2, null);


        ag.setId_compromisso(cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Agenda.COLUNA_ID)));
        etDescricao.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Agenda.COLUNA_DESCRICAO)));
        etData.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Agenda.COLUNA_DATA)));
        etHorario.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Agenda.COLUNA_HORA)));

        controlarEdicao(true);

    }


    public void controlarEdicao(Boolean enabled){

        etDescricao.setEnabled(enabled);
        etData.setEnabled(enabled);
        etHorario.setEnabled(enabled);
    }

    public void limparFormulario(){
        etDescricao.setText(null);
        etData.setText(null);
        etHorario.setText(null);
    }

    public void caixaDeDialogoSimNao(){

        AlertDialog.Builder cxDialogo = new AlertDialog.Builder(this);

        cxDialogo.setTitle("Você tem certeza que deseja excluir este compromisso?");

        cxDialogo.setMessage("Este compromisso será apagado para sempre do dispositivo.");

        cxDialogo.setPositiveButton("SIM", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (agDAO.excluir(ag) != 0) {
                    carregarListView(agDAO.getLista());
                }
                limparFormulario();
                controlarEdicao(false);
                Toast.makeText(ActivityAgenda.this, "Compromisso excluído.", Toast.LENGTH_LONG).show();
            }
        });

        cxDialogo.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                limparFormulario();
                controlarEdicao(false);
                Toast.makeText(ActivityAgenda.this, "Compromisso não excluído.", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alert = cxDialogo.create();
        alert.show();
    }


    public void salvar() {

        if(etDescricao.getText().toString().isEmpty() ||
                etData.getText().toString().isEmpty() ||
                etHorario.getText().toString().isEmpty() ){
            Toast.makeText(ActivityAgenda.this, "Você deve preencher todos os campos.", Toast.LENGTH_SHORT).show();
        }
        else{
                ag.setDescricao(etDescricao.getText().toString());
                try {
                    ag.setData(new SimpleDateFormat("dd/MM/yyyy").parse(etData.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ag.setHorario(etHorario.getText().toString());

                if(agDAO.salvar(ag)){
                    Toast.makeText(this, "Compromisso salvo.",Toast.LENGTH_LONG).show();
                limparFormulario();
                controlarEdicao(true);
                carregarListView(agDAO.getLista());
                ag = new Agenda();
            }

        }
    }



    public void cancelar(){
        limparFormulario();
        controlarEdicao(false);
    }

    public void excluir(){
        if(etDescricao.getText().toString().isEmpty() &&
                etData.getText().toString().isEmpty() &&
                etHorario.getText().toString().isEmpty() ){
            Toast.makeText(ActivityAgenda.this, "Você deve preencher todos os campos.", Toast.LENGTH_SHORT).show();
        }else{
            caixaDeDialogoSimNao();
        }
    }



    public void carregarListView(Cursor cursor){

        final String[] DE = {Contract.Agenda.COLUNA_DESCRICAO, Contract.Agenda.COLUNA_DATA, Contract.Agenda.COLUNA_HORA};
        final int[] PARA = {R.id.tv_item_desc, R.id.tv_item_data, R.id.tv_item_hora};



                ListAdapter_Agenda dadosAdapter = new ListAdapter_Agenda(
                ActivityAgenda.this,
                R.layout.adapterlayout_agenda,
                cursor,
                DE,
                PARA,
                0);

        lvCompromissos.setAdapter(dadosAdapter);
    }




}
