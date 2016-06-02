package aulaandroid.amanda.cadastrobebe.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import aulaandroid.amanda.cadastrobebe.R;
import aulaandroid.amanda.cadastrobebe.adapter.ListAdapter_Baby;
import aulaandroid.amanda.cadastrobebe.dao.BebeDAO;
import aulaandroid.amanda.cadastrobebe.dao.Contract;
import aulaandroid.amanda.cadastrobebe.model.Bebe;

/**
 * Created by Amanda on 03/12/2015.
 */
public class ActivityBaby extends Activity implements AdapterView.OnItemClickListener {

    private EditText etNome,etPeso, etDataNasc, etAltura, etSexo;
    private ListView lvlistagem;
    private ImageView img;
    private byte[] imagem = null;
    private  Bebe bb;



    private BebeDAO bbdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_infobaby);
        getActionBar().setTitle("Cadastro do Bebê");
        bbdao = BebeDAO.getInstance(this.getBaseContext());
        bbdao.open();

        bb = new Bebe();
        bb.setId_bebe(new Integer(0));

        etNome = (EditText) findViewById(R.id.etNome);
        etPeso = (EditText) findViewById(R.id.etPeso);
        etDataNasc = (EditText) findViewById(R.id.etDataNasc);
        etAltura = (EditText) findViewById(R.id.etAltura);
        etSexo = (EditText) findViewById(R.id.etSexo);
        lvlistagem = (ListView) findViewById(R.id.lvbebes);
        img = (ImageView) findViewById(R.id.imvImagem);


        controlarEdicao(true);
        carregarListView(bbdao.getLista());
        lvlistagem.setOnItemClickListener(ActivityBaby.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bb, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuitem_salvar: {
                salvar();
                return true;
            }

            case R.id.menuitem_excluir: {
                excluir();
                return true;

            }
            case R.id.menuitem_cancelar: {
                cancelar();
                return true;

            }
        }
        return false;
    }

    public void onItemClick(AdapterView<?> adpListView, View view, int position,
                            long id) {
        Cursor cursor = (Cursor) adpListView.getItemAtPosition(position);
        //bb.setId_bebe(cursor.getColumnIndexOrThrow(Contract.Bebe.COLUNA_ID));
        bb.setId_bebe(cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Bebe.COLUNA_ID)));
        etNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Bebe.COLUNA_NOME)));
        etPeso.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Bebe.COLUNA_PESO)));
        etDataNasc.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Bebe.COLUNA_DATANASC)));
        etAltura.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Bebe.COLUNA_ALTURA)));
        etSexo.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Bebe.COLUNA_SEXO)));

        if(cursor.getBlob(cursor.getColumnIndexOrThrow("imagem")) != null){
            img.setImageBitmap(BitmapFactory.decodeByteArray(cursor.getBlob(cursor.getColumnIndexOrThrow("imagem")), 0, cursor.getBlob(cursor.getColumnIndexOrThrow("imagem")).length));
            imagem = cursor.getBlob(cursor.getColumnIndexOrThrow("imagem"));
        }
        controlarEdicao(true);

    }

    public void carregarImagem(View v){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri arquivoUri = data.getData();
            img.setImageURI(arquivoUri);
            try {
                Bitmap bitmap =  BitmapFactory.decodeStream(getContentResolver().openInputStream(arquivoUri));
                byte[] img = getBitmapAsByteArray(bitmap);
                imagem = img;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }



    public void caixaDeDialogoSimNao() {

        AlertDialog.Builder cxDialogo = new AlertDialog.Builder(this);
        cxDialogo.setMessage("Você tem certeza que deseja excluir este bebê?");
        cxDialogo.setPositiveButton("SIM", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (bbdao.excluir(bb) != 0) {
                    carregarListView(bbdao.getLista());
                    bb = new Bebe();
                }
                limparFormulario();
                controlarEdicao(true);
                Toast.makeText(ActivityBaby.this, "Bebê excluído.", Toast.LENGTH_LONG).show();
            }
        });

        cxDialogo.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                limparFormulario();
                controlarEdicao(true);
                bb = new Bebe();
                Toast.makeText(ActivityBaby.this, "Não foi excluído.", Toast.LENGTH_LONG).show();
            }
        });


        AlertDialog alert = cxDialogo.create();
        alert.show();
                   }


   public void salvar()   {

        if(etNome.getText().toString().isEmpty() ||
                etPeso.getText().toString().isEmpty() ||
                etDataNasc.getText().toString().isEmpty()||
                etAltura.getText().toString().isEmpty() ||
                etSexo.getText().toString().isEmpty()
                ){
            Toast.makeText(ActivityBaby.this, "Você deve preencher todos os campos.", Toast.LENGTH_SHORT).show();
        }
        else{
            bb.setNome(etNome.getText().toString());
            bb.setPeso(Double.parseDouble(etPeso.getText().toString()));
            try {
                bb.setDatanasc(new SimpleDateFormat("dd/MM/yyyy").parse(etDataNasc.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            bb.setAltura(Double.parseDouble(etAltura.getText().toString()));
            bb.setSexo(etSexo.getText().toString());
            bb.setImagem(imagem);

            if(bbdao.salvar(bb)){
                Toast.makeText(this, "Bebê salvo com sucesso.", Toast.LENGTH_LONG).show();
                limparFormulario();
                controlarEdicao(true);
                carregarListView(bbdao.getLista());
                bb = new Bebe();
            }

        }
    }

    public void excluir(){
        if(etNome.getText().toString().isEmpty() &&
                etPeso.getText().toString().isEmpty() &&
                etDataNasc.getText().toString().isEmpty() &&
                etAltura.getText().toString().isEmpty() &&
                etSexo.getText().toString().isEmpty()
                ){
            Toast.makeText(ActivityBaby.this, "Você deve preencher todos os campos.", Toast.LENGTH_SHORT).show();
        }else{
            caixaDeDialogoSimNao();
            controlarEdicao(true);
            carregarListView(bbdao.getLista());
        }

    }

    public void cancelar(){
        limparFormulario();
    }

    public boolean onQueryTextChange(String nome) {
        if(nome.isEmpty()){
            carregarListView(bbdao.getLista());
        }else{
            carregarListView(bbdao.getListaByNome(nome));
        }
        return false;
    }


    public void controlarEdicao(Boolean enabled){
        etNome.setEnabled(enabled);
        etPeso.setEnabled(enabled);
        etDataNasc.setEnabled(enabled);
        etAltura.setEnabled(enabled);
        etSexo.setEnabled(enabled);
    }


    public void limparFormulario(){
        etNome.setText(null);
        etPeso.setText(null);
        etDataNasc.setText(null);
        etAltura.setText(null);
        etSexo.setText(null);
        img.setImageResource(R.drawable.imgbebe);
        imagem = null;
    }



    public void carregarListView(Cursor cursor){

        final String[] DE = {Contract.Bebe.COLUNA_NOME, Contract.Bebe.COLUNA_PESO, Contract.Bebe.COLUNA_DATANASC, Contract.Bebe.COLUNA_ALTURA, Contract.Bebe.COLUNA_SEXO};
        final int[] PARA = {R.id.tv_item_nome, R.id.tv_item_peso, R.id.tv_item_datanasc, R.id.tv_item_altura,R.id.tv_item_sexo};
                ListAdapter_Baby dadosAdapter = new ListAdapter_Baby(
                ActivityBaby.this,
                R.layout.bebe_listview,
                cursor,
                DE,
                PARA,
                0);

        lvlistagem.setAdapter(dadosAdapter);
    }
}
