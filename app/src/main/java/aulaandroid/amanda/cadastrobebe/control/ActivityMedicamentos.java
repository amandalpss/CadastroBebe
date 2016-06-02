package aulaandroid.amanda.cadastrobebe.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

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
        etQuantidade = (EditText) findViewById(R.id.editText_quant);
        controlarEdicao(true);

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
