package aulaandroid.amanda.cadastrobebe.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;

import aulaandroid.amanda.cadastrobebe.model.Bebe;

/**
 * Created by amanda on 07/03/16.
 */
public class BebeDAO {

    private static BebeDAO bbDAO;
    private final Context context;
    private DBHelper myHelper;
    private SQLiteDatabase database;
    private Cursor listaByNome;


    private BebeDAO(Context context) {
        this.context = context;
    }

    public static BebeDAO getInstance(Context context) {
        if (bbDAO == null) {
            bbDAO = new BebeDAO(context);
            return bbDAO;
        }

        return bbDAO;
    }

    public void open() {
        myHelper = DBHelper.getInstance(context);
        database = myHelper.getWritableDatabase();
    }


    public void close() {
        database.close();

    }

    public Boolean salvar(Bebe bb) {
        if (bb.getId_bebe().intValue() == 0) {
            return inserir(bb);
        } else {
            return alterar(bb);
        }
    }

    public Boolean inserir(Bebe bb) {
        ContentValues valores = new ContentValues();

        //valores.put(Contract.Bebe.COLUNA_ID, 0);
        valores.put(Contract.Bebe.COLUNA_NOME, bb.getNome());
        valores.put(Contract.Bebe.COLUNA_PESO, bb.getPeso());
        valores.put(Contract.Bebe.COLUNA_DATANASC, new SimpleDateFormat("dd/MM/yyyy").format(bb.getDatanasc()));
        valores.put(Contract.Bebe.COLUNA_ALTURA, bb.getAltura());
        valores.put(Contract.Bebe.COLUNA_SEXO, bb.getSexo());
        valores.put(Contract.Bebe.COLUNA_IMAGEM, bb.getImagem());

        database.insert(Contract.Bebe.TABELA_NOME, null, valores);

        return true;
    }

    public Boolean alterar(Bebe bb) {
        ContentValues valores = new ContentValues();


        valores.put(Contract.Bebe.COLUNA_NOME, bb.getNome());
        valores.put(Contract.Bebe.COLUNA_PESO, bb.getPeso());
        valores.put(Contract.Bebe.COLUNA_DATANASC, new SimpleDateFormat("dd/MM/yyyy").format(bb.getDatanasc()));
        valores.put(Contract.Bebe.COLUNA_ALTURA, bb.getAltura());
        valores.put(Contract.Bebe.COLUNA_SEXO, bb.getSexo());
        valores.put(Contract.Bebe.COLUNA_IMAGEM, bb.getImagem());


        database.update(Contract.Bebe.TABELA_NOME, valores, Contract.Bebe.COLUNA_ID + " = " + bb.getId_bebe(), null);

        return true;
    }

    public Integer excluir(Bebe bb) {
        Log.d("BebeDAO", "Id no excluir = " + bb.getId_bebe());
        return database.delete(Contract.Bebe.TABELA_NOME, Contract.Bebe.COLUNA_ID + " = " + bb.getId_bebe(), null);
    }

    public Cursor getLista() {
        return database.rawQuery("SELECT  * FROM " + Contract.Bebe.TABELA_NOME, null);
    }

    public Cursor getListaByNome(String nome) {
        return database.rawQuery("SELECT  * FROM " + Contract.Bebe.TABELA_NOME + " WHERE nome LIKE '" + nome + "%'", null);
    }
}
