package aulaandroid.amanda.cadastrobebe.dao;

import android.provider.BaseColumns;

/**
 * Created by amanda on 07/03/16.
 */
public final class Contract {


    public static final String BD_NOME = "alomamae.db";
    public static final int BD_VERSAO = 1;


    private Contract() {
    }

    public static abstract class Bebe implements BaseColumns {
        public static final String TABELA_NOME = "bebes";
        public static final String COLUNA_ID = "_id";
        public static final String COLUNA_NOME = "nome";
        public static final String COLUNA_DATANASC = "datanasc";
        public static final String COLUNA_PESO = "peso";
        public static final String COLUNA_ALTURA = "altura";
        public static final String COLUNA_SEXO = "sexo";
        public static final String COLUNA_IMAGEM = "imagem";
    }

    public static abstract class Vacinas implements BaseColumns {
        public static final String TABELA_NOME = "vacinas";
        public static final String COLUNA_ID = "_id";
        public static final String COLUNA_IDADE = "idade";
        public static final String COLUNA_VACINAS = "vacinas";
        public static final String COLUNA_DOSES = "doses";
        public static final String COLUNA_REALIZADA = "vacinarealizada";
        public static final String COLUNA_DOENCAS = "doencas_evitadas";

    }


    public static abstract class Agenda implements BaseColumns {
        public static final String TABELA_NOME = "agenda";
        public static final String COLUNA_ID = "_id";
        public static final String COLUNA_DESCRICAO = "descricao";
        public static final String COLUNA_DATA = "data";
        public static final String COLUNA_HORA = "horario";

    }


    public static abstract class Medicamentos implements BaseColumns {
        public static final String TABELA_NOME = "medicamentos";
        public static final String COLUNA_ID = "_id";
        public static final String COLUNA_NOMEMED = "nome";
        public static final String COLUNA_DOSEMED = "dosagem";
        public static final String COLUNA_QUANTIDADEMED = "quantidade";



    }
}