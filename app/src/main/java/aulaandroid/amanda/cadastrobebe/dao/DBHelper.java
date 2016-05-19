package aulaandroid.amanda.cadastrobebe.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by amanda on 07/03/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper myHelper;



    private DBHelper(Context context) {
        super(context, Contract.BD_NOME, null, Contract.BD_VERSAO);
    }


    public static DBHelper getInstance(Context context) {
        if(myHelper == null){
            myHelper = new DBHelper(context);
            return myHelper;
        }
        return myHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + Contract.Bebe.TABELA_NOME + " ("
                + Contract.Bebe.COLUNA_ID + " integer primary key autoincrement,"
                + Contract.Bebe.COLUNA_NOME + " text,"
                + Contract.Bebe.COLUNA_PESO + " text,"
                + Contract.Bebe.COLUNA_DATANASC + " text,"
                + Contract.Bebe.COLUNA_ALTURA + " text,"
                + Contract.Bebe.COLUNA_SEXO + " text,"
                + Contract.Bebe.COLUNA_IMAGEM + " blob);";

        String sql2 = "create table " + Contract.Vacinas.TABELA_NOME + " ("
                + Contract.Vacinas.COLUNA_ID + " integer primary key autoincrement,"
                + Contract.Vacinas.COLUNA_IDADE + " text,"
                + Contract.Vacinas.COLUNA_VACINAS + " text,"
                + Contract.Vacinas.COLUNA_DOSES + " text,"
                + Contract.Vacinas.COLUNA_REALIZADA + " text,"
                + Contract.Vacinas.COLUNA_DOENCAS + " text);";

        String sql3 = "create table " + Contract.Medicamentos.TABELA_NOME + " ("
                + Contract.Medicamentos.COLUNA_ID + " integer primary key autoincrement,"
                + Contract.Medicamentos.COLUNA_NOMEMED + " text,"
                + Contract.Medicamentos.COLUNA_DOSEMED + " text,"
                + Contract.Medicamentos.COLUNA_QUANTIDADEMED + " text);";

        String sql4 = "create table " + Contract.Agenda.TABELA_NOME + " ("
                + Contract.Agenda.COLUNA_ID + " integer primary key autoincrement,"
                + Contract.Agenda.COLUNA_DESCRICAO + " text,"
                + Contract.Agenda.COLUNA_DATA + " text,"
                + Contract.Agenda.COLUNA_HORA + " text);";

        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);

        ContentValues vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "AO NASCER");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina BCG");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Dose única");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Formas graves de tuberculose, principalmente miliar e meníngea");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);


        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "AO NASCER");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina hepatite B");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Dose ao nascer");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Hepatite B");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "2 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina adsorvida, difteria, tétano, pertussis, hepatite B e Haemophilus influenzae B");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Primeira dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Difteria, tétano, coqueluche, hepatite B, além de meningite e outras infecções causadas pelo Haemophilus influenzae tipo B");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "2 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina poliomelite 1, 2 e 3 (inativada)");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Primeira dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Poliomelite (paralisia infantil)");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "2 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina pneumocócica 10-valente");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Primeira dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Pneumonia, otite, meningite e outras doenças causadas pelo Pneumococo");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "2 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina rotavírus humano G1P1 [8]");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Primeira dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Diarreia por rotavírus");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "3 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina meningocócica C");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Primeira dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Doença invasiva causada por Neisseria meningitidis do sorogrupo C");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "4 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina adsorvida, difteria, tétano, pertussis, hepatite B e Haemophilus influenzae B");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Segunda dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Difteria, tétano, coqueluche, hepatite B, além de meningite e outras infecções causadas pelo Haemophilus influenzae tipo B");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);


        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "4 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina poliomelite 1, 2 e 3 (inativada)");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Segunda dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Poliomelite (paralisia infantil)");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "4 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina pneumocócica 10-valente");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Segunda dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Pneumonia, otite, meningite e outras doenças causadas pelo Pneumococo");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);


        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "4 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina rotavírus humano G1P1 [8]");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Segunda dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Diarreia por rotavírus");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "5 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina meningocócica C");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Segunda dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Doença invasiva causada por Neisseria meningitidis do sorogrupo C");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);


        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "6 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina adsorvida, difteria, tétano, pertussis, hepatite B e Haemophilus influenzae B");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Terceira dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Difteria, tétano, coqueluche, hepatite B, além de meningite e outras infecções causadas pelo Haemophilus influenzae tipo B");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);


        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "6 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina poliomelite 1, 2 e 3 (inativada)");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Terceira dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Poliomelite (paralisia infantil)");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);


        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "6 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina pneumocócica 10-valente");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Terceira dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Pneumonia, otite, meningite e outras doenças causadas pelo Pneumococo");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);


        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "9 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina febre amarela");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Dose inicial");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Febre amarela");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);


        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "12 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina sarampo, caxumba, rubéola");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Primeira dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Sarampo, caxumba e rubéola");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);


        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "12 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina pneumocócica 10-valente");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Reforço");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Pneumonia, otite, meningite e outras doenças causadas pelo Pneumococo");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);


        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "15 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina poliomelite 1, 2 e 3");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Reforço");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Poliomelite (paralisia infantil)");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "15 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina adsorvida difteria, tétano e pertussis");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Primeiro reforço");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Difteria, tétano e coqueluche");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "15 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina meningocócica C");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Reforço");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Doença invasiva causada por Neisseria meningitidis do sorogrupo C");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "15 MESES");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina sarampo, caxumba, rubéola");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Segunda dose");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Sarampo, caxumba e rubéola");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "4 ANOS");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina adsorvida difteria, tétano e pertussis");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Segundo reforço");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Difteria, tétano e coqueluche");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);



        vacinas = new ContentValues();
        vacinas.put(Contract.Vacinas.COLUNA_IDADE, "10 ANOS");
        vacinas.put(Contract.Vacinas.COLUNA_VACINAS, "Vacina febre amarela");
        vacinas.put(Contract.Vacinas.COLUNA_DOSES, "Uma dose a cada dez anos");
        vacinas.put(Contract.Vacinas.COLUNA_REALIZADA, " ");
        vacinas.put(Contract.Vacinas.COLUNA_DOENCAS, "Febre amarela");
        db.insertOrThrow(Contract.Vacinas.TABELA_NOME, null, vacinas);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Vacinas.TABELA_NOME);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("programa", "Upgrade da versão " + oldVersion + " para "
                + newVersion + ", destruindo tudo.");
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Bebe.TABELA_NOME);
        onCreate(db);

    }




}
