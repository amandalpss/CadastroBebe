package aulaandroid.amanda.cadastrobebe.control;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import aulaandroid.amanda.cadastrobebe.R;
import aulaandroid.amanda.cadastrobebe.dao.MedicamentosDAO;

/**
 * Created by amanda on 02/05/16.
 */
    public class ActivityMedicamentos extends Activity {

    private EditText etNome,etDosagem,etQuantidade;

    private MedicamentosDAO meddao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
        getActionBar().setTitle("Registro de Medicamentos");
        //meddao = MedicamentosDAO.getInstance(this.getBaseContext());
        //meddao.open();


        etNome = (EditText) findViewById(R.id.editText_nome);
        etDosagem = (EditText) findViewById(R.id.editText_dosagem);
        etQuantidade = (EditText) findViewById(R.id.editText_quant);
    }







}
