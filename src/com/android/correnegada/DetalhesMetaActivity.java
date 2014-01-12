package com.android.correnegada;


import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class DetalhesMetaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhes_meta);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalhes_meta, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		CarregaDadosFormulario();
	}
	
	private void CarregaDadosFormulario(){
		Intent it = getIntent();
		int id = it.getIntExtra("id", 1);
		
		MetaDAO db = MetaDAO.getInstance(getBaseContext());
		Meta meta = db.getMetaByID(id);
		
//		
		EditText txtTreino = (EditText) findViewById(R.id.editMetaTreino);
		EditText txtAquecimento = (EditText) findViewById(R.id.editMetaAquecimento);
		EditText txtCaminhada = (EditText) findViewById(R.id.editMetaCaminhada);
		EditText txtTrote = (EditText) findViewById(R.id.editMetaTrote);
		EditText txtCorrida = (EditText) findViewById(R.id.editMetaCorrida);
		EditText txtTempoTotal = (EditText) findViewById(R.id.editMetaTempoTotal);
		
		txtTreino.setText(meta.getTreino());
		txtAquecimento.setText(String.valueOf(meta.getAquecimento()));
		txtCaminhada.setText(String.valueOf(meta.getCaminhada()));
		txtTrote.setText(String.valueOf(meta.getTrote()));
		txtCorrida.setText(String.valueOf(meta.getCorrida()));
		txtTempoTotal.setText(String.valueOf(meta.getTempoTotal()));
	}

}
