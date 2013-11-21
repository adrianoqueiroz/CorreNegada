package com.android.correnegada;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
	
        popularListView();
        selecionaItemMenu();               
	}
	
	public void criarNovaMetaClick(View v){
		
		Intent novaMeta = new Intent(getBaseContext(), NovaMetaActivity.class);
		startActivity(novaMeta);
	}
	
	private void popularListView(){
		//criar lista de items
		String[] lista = {"Meta 1", "Meta 2", "Meta 3", "Meta 4", "Meta 5", "Meta 6", "Meta 7", "Meta 8", "Meta 9", "Meta 10"};
		//build adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this, //context da activity
				R.layout.da_item, //layout usado na criacao
				lista);//itens mostrados
		
		//configurar a list view
		ListView list = (ListView)findViewById(R.id.lVMetas);
		list.setAdapter(adapter);
	}
	
	private void selecionaItemMenu(){
		ListView list = (ListView) findViewById(R.id.lVMetas);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {	
			@Override
			public void onItemClick(AdapterView<?> paret, View viewClicked, int posicao, long id){
				TextView textViewItem = (TextView) viewClicked;
				
				String mensagem;
				//teste de mudanca de tela
				switch(posicao){
				case 0:
					mensagem = "Vendo detalhes de " + textViewItem.getText().toString();
					Toast.makeText(MetasActivity.this, mensagem, Toast.LENGTH_LONG).show();

					Intent trocarActivity = new Intent( MetasActivity.this, DetalhesMetaActivity.class);
	                startActivityForResult(trocarActivity, 1);
					break;
				case 1:
					mensagem = "Vendo detalhes de " + textViewItem.getText().toString();
					Toast.makeText(MetasActivity.this, mensagem, Toast.LENGTH_LONG).show();

					Intent trocarActivity1 = new Intent( MetasActivity.this, DetalhesMetaActivity.class);
	                startActivityForResult(trocarActivity1, 1);
					break;

				case 2:
					mensagem = "Vendo detalhes de " + textViewItem.getText().toString();
					Toast.makeText(MetasActivity.this, mensagem, Toast.LENGTH_LONG).show();

					Intent trocarActivity2 = new Intent( MetasActivity.this, DetalhesMetaActivity.class);
	                startActivityForResult(trocarActivity2, 1);
					break;

				default:
					break;
					
				}
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
