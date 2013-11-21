package com.android.correnegada;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class PrincipalActivity extends Activity {

	ListView listView;
	
	Button btMetas, btCorrer;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);       
        btMetas = (Button) findViewById(R.id.btMetas);
        btCorrer = (Button) findViewById(R.id.btCorrer);        
    }

    public void cadastrarMetasClick(View v){
				
		Intent TelaMetas = new Intent(getBaseContext(),MetasActivity.class);
		startActivity(TelaMetas);				
    }

    public void AlterarConfiguracoesClick(View v){
    	
    	Intent TelaConf = new Intent(getBaseContext(),ConfiguracoesActivity.class);
		startActivity(TelaConf);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }
    
}
