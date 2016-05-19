package aulaandroid.amanda.cadastrobebe.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import aulaandroid.amanda.cadastrobebe.model.Vacinas;

/**
 * Created by amanda on 18/03/16.
 */
public class VacinasDAO extends Activity {

    private static VacinasDAO vcDAO;
    private final Context context;
    private DBHelper myHelper;
    private SQLiteDatabase database;
    private Cursor listaByIdade;


    public VacinasDAO(Context context) {
            this.context = context;
    }

    public static VacinasDAO getInstance(Context context) {
        if (vcDAO == null) {
            vcDAO = new VacinasDAO(context);
            return vcDAO;
        }

        return vcDAO;
    }

    public void open() {
        myHelper = DBHelper.getInstance(context);
        database = myHelper.getWritableDatabase();
    }


    public void close() {
        database.close();
    }



        public Cursor getListaByIdade(String idade) {

            return database.rawQuery("SELECT * FROM "
                    + Contract.Vacinas.TABELA_NOME
                    + " WHERE " + Contract.Vacinas.COLUNA_IDADE
                    + " LIKE trim('" + idade + "')", null);
        }


    public Boolean alterar(Vacinas vacina) {
        ContentValues valores = new ContentValues();
        valores.put(Contract.Vacinas.COLUNA_IDADE, vacina.getIdade());
        valores.put(Contract.Vacinas.COLUNA_VACINAS, vacina.getVacinas());
        valores.put(Contract.Vacinas.COLUNA_DOSES, vacina.getDoses());
        valores.put(Contract.Vacinas.COLUNA_REALIZADA, vacina.getRealizada());
        valores.put(Contract.Vacinas.COLUNA_DOENCAS, vacina.getDoenca_evitada());

        database.update(Contract.Vacinas.TABELA_NOME, valores, Contract.Vacinas.COLUNA_ID + " = " + vacina.getId_vacina(), null);

        return true;
    }


        //SELECT distinct idade FROM vacinas
        //SELECT distinct * FROM vacinas where idade like '2 meses'







}
