package aulaandroid.amanda.cadastrobebe.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import aulaandroid.amanda.cadastrobebe.R;

/**
 * Created by amanda on 07/03/16.
 */


    public class ListAdapter_Baby extends SimpleCursorAdapter {


        public ListAdapter_Baby(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            super.bindView(view, context, cursor);
            ImageView imvImagem = (ImageView) view.findViewById(R.id.imv_item);
            if (cursor.getBlob(cursor.getColumnIndexOrThrow("imagem")) != null) {

                Bitmap bitmap = BitmapFactory.decodeByteArray(cursor.getBlob(cursor.getColumnIndexOrThrow("imagem")), 0, cursor.getBlob(cursor.getColumnIndexOrThrow("imagem")).length);
                imvImagem.setImageBitmap(bitmap);
            }else{
                imvImagem.setImageResource(R.drawable.imgbebe);
            }
        }
    }
