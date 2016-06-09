package aulaandroid.amanda.cadastrobebe.control;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import aulaandroid.amanda.cadastrobebe.R;
import aulaandroid.amanda.cadastrobebe.dao.Contract;
import aulaandroid.amanda.cadastrobebe.dao.MedicamentosDAO;
import aulaandroid.amanda.cadastrobebe.model.Medicamentos;

/**
 * Created by amanda on 02/05/16.
 */
    public class ActivityMedicamentos extends Activity implements AdapterView.OnItemClickListener {

    private EditText etNome,etDosagem,etQuantidade;
    private Medicamentos med;
    AlarmManager alarm;
    private PendingIntent pending_intent;


    private TimePicker alarmTimePicker;
    private Button btAdicionar;
    private Context context;

    private MedicamentosDAO meddao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
        getActionBar().setTitle("Registro de Medicamentos");
        meddao = MedicamentosDAO.getInstance(this.getBaseContext());
        meddao.open();


        etNome = (EditText) findViewById(R.id.editText_nomemed);
        etDosagem = (EditText) findViewById(R.id.editText_dosagem);
        etQuantidade = (EditText) findViewById(R.id.editText_quantidade);
        btAdicionar = (Button) findViewById(R.id.btn_add);
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
                controlarEdicao(true);

        this.context = this;
    }


    public void adcAlarme (View view) {

        final Intent myIntent = new Intent(this.context, AlarmReceiver.class);
        final Calendar calendar = Calendar.getInstance();

        //calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
        //calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());

        //final int hour = alarmTimePicker.getHour();
        //final int minute = alarmTimePicker.getMinute();

        calendar.set(Calendar.HOUR_OF_DAY, 3);
        calendar.set(Calendar.MINUTE, 01);


        myIntent.putExtra("extra", "yes");
        pending_intent = PendingIntent.getBroadcast(ActivityMedicamentos.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

    }

    public void removAlarme (View view) {

        final Intent myIntent = new Intent(this.context, AlarmReceiver.class);

        myIntent.putExtra("extra", "no");
        sendBroadcast(myIntent);

        alarm.cancel(pending_intent);



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_medicamentos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuitem_salvar: {
                salvar();
                return true;
            }

            case R.id.menuitem_excluir: {
                excluir();
                return true;

            }
            case R.id.menuitem_cancelar: {
                cancelar();
                return true;

            }
        }
        return false;
    }


    public void onItemClick(AdapterView<?> adpListView, View view, int position,
                           long id) {
        Cursor cursor = (Cursor) adpListView.getItemAtPosition(position);
        med.setId_medicamentos(cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Medicamentos.COLUNA_ID)));
        etNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Medicamentos.COLUNA_NOMEMED)));
        etDosagem.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Medicamentos.COLUNA_DOSEMED)));
        etQuantidade.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Medicamentos.COLUNA_QUANTIDADEMED)));


        controlarEdicao(true);
    }

    public void caixaDeDialogoSimNao() {

        AlertDialog.Builder cxDialogo = new AlertDialog.Builder(this);
        cxDialogo.setMessage("Você tem certeza que deseja excluir este medicamento?");
        cxDialogo.setPositiveButton("SIM", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (meddao.excluir(med) != 0) {
                    med = new Medicamentos();
                }
                limparFormulario();
                controlarEdicao(true);
                Toast.makeText(ActivityMedicamentos.this, "Medicamento excluído.", Toast.LENGTH_LONG).show();
            }
        });

        cxDialogo.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                limparFormulario();
                controlarEdicao(true);
                med = new Medicamentos();
                Toast.makeText(ActivityMedicamentos.this, "Não foi excluído.", Toast.LENGTH_LONG).show();
            }
        });


        AlertDialog alert = cxDialogo.create();
        alert.show();
    }

    public void salvar() {

        if(etNome.getText().toString().isEmpty() ||
                etDosagem.getText().toString().isEmpty() ||
                etQuantidade.getText().toString().isEmpty()){
            Toast.makeText(ActivityMedicamentos.this, "Você deve preencher todos os campos.", Toast.LENGTH_SHORT).show();
        }
        else{
            med.setNomemed(etNome.getText().toString());
            med.setDosemed(Double.parseDouble(etDosagem.getText().toString()));
            med.setQuantidademed(Integer.parseInt(etQuantidade.getText().toString()));

            if(meddao.salvar(med)){
                Toast.makeText(this, "Bebê salvo com sucesso.", Toast.LENGTH_LONG).show();
                limparFormulario();
                controlarEdicao(true);
                //carregarListView(meddao.getLista());
                med = new Medicamentos();
            }
        }
    }

    public void excluir(){
        if(etNome.getText().toString().isEmpty() &&
                etDosagem.getText().toString().isEmpty() &&
                etQuantidade.getText().toString().isEmpty())
        {
            Toast.makeText(ActivityMedicamentos.this, "Você deve preencher todos os campos.", Toast.LENGTH_SHORT).show();
        }else{
            caixaDeDialogoSimNao();
            controlarEdicao(true);
            //carregarListView(meddao.getLista());
        }
        }

    public void cancelar(){
        limparFormulario();
    }



    public void controlarEdicao(Boolean enabled){
        etNome.setEnabled(enabled);
        etDosagem.setEnabled(enabled);
        etQuantidade.setEnabled(enabled);

    }

    public void limparFormulario() {
        etNome.setText(null);
        etDosagem.setText(null);
        etQuantidade.setText(null);


    }

}
