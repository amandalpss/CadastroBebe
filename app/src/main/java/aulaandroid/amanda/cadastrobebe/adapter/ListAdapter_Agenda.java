package aulaandroid.amanda.cadastrobebe.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.SimpleCursorAdapter;

/**
 * Created by amanda on 12/05/16.
 */
public class ListAdapter_Agenda extends SimpleCursorAdapter{

    public ListAdapter_Agenda(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);


    }


}
