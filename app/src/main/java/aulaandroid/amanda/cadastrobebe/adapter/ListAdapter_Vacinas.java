package aulaandroid.amanda.cadastrobebe.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import aulaandroid.amanda.cadastrobebe.dao.Contract;

/**
 * Created by amanda on 23/03/16.
 */
public class ListAdapter_Vacinas extends SimpleCursorAdapter {

    private final String TAG = "ActivityVacinas";



    public ListAdapter_Vacinas(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            Log.d(TAG, "No construtor. Tamanho do cursor: " + c.getCount());
        }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        if(cursor.moveToNext()) {
            Log.d(TAG, "chamou bindview no adaptador. Tamanho do cursor: " + cursor.getCount());
            Log.d(TAG, "" + cursor.getString(cursor.getColumnIndex(Contract.Vacinas.COLUNA_VACINAS)));
            Log.d(TAG, "" + cursor.getString(cursor.getColumnIndex(Contract.Vacinas.COLUNA_DOSES)));
            Log.d(TAG, "" + cursor.getString(cursor.getColumnIndex(Contract.Vacinas.COLUNA_DOENCAS)));
        }
    }

    }
