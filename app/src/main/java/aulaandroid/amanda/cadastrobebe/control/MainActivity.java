package aulaandroid.amanda.cadastrobebe.control;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import aulaandroid.amanda.cadastrobebe.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaprinc);
        getActionBar().setTitle("Alô Mamãe");
    }


    public void abrirBaby (View view) {
        startActivity(new Intent(MainActivity.this, ActivityBaby.class));
    }


    public void abrirchoro (View view) {
        startActivity(new Intent(MainActivity.this, ActivityChoro.class));
    }

    public void abrirmama (View view) {
        startActivity(new Intent(MainActivity.this, ActivityMama.class));
    }

    public void abrirFralda (View view) {
        startActivity(new Intent(MainActivity.this, ActivityHigiene.class));
    }

    public void abrirVacina (View view) {
        startActivity(new Intent(MainActivity.this, ActivityVacinas.class));

    }

    public void abrirChupeta (View view) {
        //startActivity(new Intent(MainActivity.this, ActivityHigiene.class));
        Toast.makeText(MainActivity.this, "Em desenvolvimento.",Toast.LENGTH_SHORT).show();
    }

    public void abrirTrem (View view) {
        //startActivity(new Intent(MainActivity.this, ActivityHigiene.class));
        Toast.makeText(MainActivity.this, "Em desenvolvimento.",Toast.LENGTH_SHORT).show();
    }

    public void abrirBerco (View view) {
        //startActivity(new Intent(MainActivity.this, ActivityHigiene.class));
        Toast.makeText(MainActivity.this, "Em desenvolvimento.",Toast.LENGTH_SHORT).show();
    }

    public void abrirCarrinho(View view) {
        startActivity(new Intent(MainActivity.this, ActivityAgenda.class));

    }


    }

