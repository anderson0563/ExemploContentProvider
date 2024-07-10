package br.santos.anderson.exemplocontentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private boolean permissionReadContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReadContactsPermission();

        if(permissionReadContacts){
            final ListView listaPessoas = (ListView)findViewById(R.id.lista);

            List<EntidadeContatos> ListaContatos = new ArrayList<EntidadeContatos>();

            Contatos Contato = new Contatos(this);

            ListaContatos = Contato.getContatos();

            // adapter que sera o source para a listview

            ArrayAdapter<EntidadeContatos> adapter = new ArrayAdapter<EntidadeContatos>(this,android.R.layout.simple_list_item_1, ListaContatos);

            //seta o adapter para o listview

            listaPessoas.setAdapter(adapter);

        }else{
            Toast.makeText(this, "Sem permissão de acesso aos contatos", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Prompts the user for permission to use the device location.
     */
    private void getReadContactsPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            permissionReadContacts = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }

}