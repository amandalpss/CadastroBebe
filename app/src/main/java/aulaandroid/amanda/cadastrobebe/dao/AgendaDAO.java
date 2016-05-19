package aulaandroid.amanda.cadastrobebe.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import aulaandroid.amanda.cadastrobebe.model.Agenda;

/**
 * Created by amanda on 11/05/16.
 */
public class AgendaDAO {

    private static AgendaDAO agDAO;
    private final Context context;
    private DBHelper myHelper;
    private SQLiteDatabase database;
    private Cursor listaByData;


    private AgendaDAO(Context context) {
        this.context = context;
    }

    public static AgendaDAO getInstance(Context context) {
        if (agDAO == null) {
            agDAO = new AgendaDAO(context);
            return agDAO;
        }

        return agDAO;
    }

    public void open() {
        myHelper = DBHelper.getInstance(context);
        database = myHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

        public Boolean salvar(Agenda ag) {
            if (ag.getId_compromisso() == null) {
                return inserir(ag);
            } else {
                return alterar(ag);
            }
        }

    public Boolean inserir(Agenda ag) {
        ContentValues valores = new ContentValues();

        valores.put(Contract.Agenda.COLUNA_DESCRICAO, ag.getDescricao());
        valores.put(Contract.Agenda.COLUNA_HORA, ag.getHorario());
        valores.put(Contract.Agenda.COLUNA_DATA, ag.getData().toString());

        database.insert(Contract.Agenda.TABELA_NOME, null, valores);

        return true;
    }

    public Boolean alterar(Agenda ag) {
        ContentValues valores = new ContentValues();

        valores.put(Contract.Agenda.COLUNA_DESCRICAO, ag.getDescricao());
        valores.put(Contract.Agenda.COLUNA_HORA, ag.getHorario());
        valores.put(Contract.Agenda.COLUNA_DATA, ag.getData().toString());

        database.update(Contract.Agenda.TABELA_NOME, valores, Contract.Agenda.COLUNA_ID + " = " + ag.getId_compromisso(), null);
        return true;
    }

    public Integer excluir(Agenda ag) {

        return database.delete(Contract.Agenda.TABELA_NOME, Contract.Agenda.COLUNA_ID + " = " + ag.getId_compromisso(), null);
    }

    public Cursor getLista() {

        return database.rawQuery("SELECT  * FROM " + Contract.Agenda.TABELA_NOME, null);

    }

    public Cursor getListaByData(String data) {

        return database.rawQuery("SELECT  * FROM " + Contract.Agenda.TABELA_NOME + " WHERE data LIKE '" + data + "%'", null);
    }
}
