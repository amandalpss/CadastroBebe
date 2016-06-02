package aulaandroid.amanda.cadastrobebe.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import aulaandroid.amanda.cadastrobebe.model.Bebe;
import aulaandroid.amanda.cadastrobebe.model.Medicamentos;

/**
 * Created by amanda on 02/05/16.
 */
public class MedicamentosDAO {


    private static MedicamentosDAO medDAO;
    private final Context context;
    private DBHelper myHelper;
    private SQLiteDatabase database;

    private MedicamentosDAO (Context context) {this.context = context;}

    public static MedicamentosDAO getInstance(Context context) {
        if (medDAO == null) {
            medDAO = new MedicamentosDAO(context);
            return medDAO;
        }
        return medDAO;
    }

    public void open() {
        myHelper = DBHelper.getInstance(context);
        database = myHelper.getWritableDatabase();
    }

    public void close() {
        database.close();

    }

    public Boolean salvar(Medicamentos med) {
        if (med.getId_medicamentos() == 0) {

            return inserir(med);
        }
        return alterar(med);
    }


    public Boolean inserir(Medicamentos med) {
        ContentValues valores = new ContentValues();

        valores.put(Contract.Medicamentos.COLUNA_NOMEMED, med.getNomemed());
        valores.put(Contract.Medicamentos.COLUNA_DOSEMED, med.getDosemed());
        valores.put(Contract.Medicamentos.COLUNA_QUANTIDADEMED, med.getQuantidademed());

        database.insert(Contract.Bebe.TABELA_NOME, null, valores);

        return true;
    }

    public Integer excluir(Medicamentos med) {
        return database.delete(Contract.Medicamentos.TABELA_NOME, Contract.Bebe.COLUNA_ID + " = " + med.getId_medicamentos(), null);
    }

    public Boolean alterar(Medicamentos med) {

        return true;
    }

}
