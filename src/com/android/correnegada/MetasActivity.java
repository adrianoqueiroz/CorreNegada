package com.android.correnegada;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MetasActivity extends Activity {

	Button btInicio;
	Button btNovaMeta;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_metas);
        btInicio = (Button) findViewById(R.id.btInicio);
        
        btInicio.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent TelaMetas = new Intent(MetasActivity.this,PrincipalActivity.class);
				MetasActivity.this.startActivity(TelaMetas);
				MetasActivity.this.finish();
			}
		});
        btNovaMeta = (Button) findViewById(R.id.btNova);
        //popularListView();
        selecionaItemMenu();               
	}
	
	@Override
	public void onResume(){
		super.onResume();
		popularListView();
	}
	
	public void criarNovaMetaClick(View v){
		
		Intent novaMeta = new Intent(getBaseContext(), NovaMetaActivity.class);
		startActivity(novaMeta);
	}
	
	private void popularListView(){
		MetaDAO db = MetaDAO.getInstance(getBaseContext());
		List<Meta> metas = db.recuperarTodos();
		ArrayAdapter<Meta> adapter = new ArrayAdapter<Meta>(
				this,
				R.layout.da_item,
				metas);
		ListView list = (ListView)findViewById(R.id.lVMetas);
		list.setAdapter(adapter);
	}
	
	private void selecionaItemMenu(){
		ListView list = (ListView) findViewById(R.id.lVMetas);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {	
			@Override
			public void onItemClick(AdapterView<?> paret, View viewClicked, int posicao, long id){
				TextView textViewItem = (TextView) viewClicked;
				String meta = (String) textViewItem.getText();
				String[] array = meta.split(" ");
				int _id = Integer.parseInt(array[0]);
				String mensagem = "Vendo detalhes de " + textViewItem.getText().toString();
				Toast.makeText(MetasActivity.this, mensagem, Toast.LENGTH_SHORT).show();
				
				Intent trocarActivity = new Intent( MetasActivity.this, DetalhesMetaActivity.class);
				trocarActivity.putExtra("id", _id);
                startActivity(trocarActivity);
			}
		});
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.metas, menu);
		return true;
	}

}
