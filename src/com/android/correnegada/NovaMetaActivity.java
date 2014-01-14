package com.android.correnegada;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NovaMetaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nova_meta);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nova_meta, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
		Button botaoSalvar = (Button) findViewById(R.id.btnSaveMetaSalvar);

		
		botaoSalvar.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				CriarMeta();
				
				Intent TelaMetas = new Intent(getBaseContext(), MetasActivity.class);
				startActivity(TelaMetas);
			}

			});
		

	}
	
	private void CriarMeta() {
		
		MetaDAO db = MetaDAO.getInstance(getBaseContext());		
		Meta meta = getMetaFormulario();
		db.Salvar(meta);
		Log.i("teste", "fudeu");
		db.fecharConexao();
		
	}
	
	private Meta getMetaFormulario(){
		
		EditText txtTreino = (EditText) findViewById(R.id.saveMetaTreino);
		EditText txtAquecimento = (EditText) findViewById(R.id.saveMetaAquecimento);
		EditText txtCaminhada = (EditText) findViewById(R.id.saveMetaCaminhada);
		EditText txtTrote = (EditText) findViewById(R.id.saveMetaTrote);
		EditText txtCorrida = (EditText) findViewById(R.id.saveMetaCorrida);
		EditText txtTempoTotal = (EditText) findViewById(R.id.saveMetaTempoTotal);
		Log.i("teste", "pegou tudo até agora" + txtAquecimento.getText().toString());
		
		String treino = txtTreino.getText().toString();
		Long aquecimento = (Long) Long.parseLong(txtAquecimento.getText().toString());
		Long caminhada = (Long)  Long.parseLong(txtCaminhada.getText().toString());
		Long trote = (Long) Long.parseLong(txtTrote.getText().toString());
		Long corrida = (Long) Long.parseLong(txtCorrida.getText().toString());
		Long tempoTotal = (Long) Long.parseLong(txtTempoTotal.getText().toString());
		
		Log.i("teste", "ainda tudo bem");
		Meta meta = new Meta(1, treino, aquecimento,
				caminhada , trote, corrida, tempoTotal);
		Log.i("teste", "criou a meta");
		
		return meta;
	}
	
	

}
