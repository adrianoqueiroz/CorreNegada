package com.android.correnegada;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetalhesMetaActivity extends Activity {
	
	private int id;

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

		Intent it = getIntent();
		id = it.getIntExtra("id", 1);
		
		setDadosFormulario();
		
		Button botaoSalvar = (Button) findViewById(R.id.btnEditMetaSalvar);
		Button botaoExcluir = (Button) findViewById(R.id.btnEditMetaExcluir);
		
		botaoSalvar.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				SalvaMeta();
				
				Intent TelaMetas = new Intent(getBaseContext(), MetasActivity.class);
				startActivity(TelaMetas);
			}});
		

		botaoExcluir.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				ExcluiMeta();
				
			}});
	}
	
	private void setDadosFormulario(){
		
		MetaDAO db = MetaDAO.getInstance(getBaseContext());
		Meta meta = db.getMetaByID(id);
		db.fecharConexao();
//		
		EditText txtTreino = (EditText) findViewById(R.id.editMetaTreino);
		EditText txtAquecimento = (EditText) findViewById(R.id.editMetaAquecimento);
		EditText txtCaminhada = (EditText) findViewById(R.id.editMetaCaminhada);
		EditText txtTrote = (EditText) findViewById(R.id.editMetaTrote);
		EditText txtCorrida = (EditText) findViewById(R.id.saveMetaCorrida);
		EditText txtTempoTotal = (EditText) findViewById(R.id.editMetaTempoTotal);
		
		txtTreino.setText(meta.getTreino());
		txtAquecimento.setText(String.valueOf(meta.getAquecimento()));
		txtCaminhada.setText(String.valueOf(meta.getCaminhada()));
		txtTrote.setText(String.valueOf(meta.getTrote()));
		txtCorrida.setText(String.valueOf(meta.getCorrida()));
		txtTempoTotal.setText(String.valueOf(meta.getTempoTotal()));
		
	}
	
	private void SalvaMeta( ){
		
		MetaDAO db = MetaDAO.getInstance(getBaseContext());
		
		Meta meta = getMetaFormulario();
		Log.i("teste", "aqui ainda funciona");
		db.updateMeta(meta);
		Log.i("teste", "fudeu");
		db.fecharConexao();
	}
	
	@SuppressWarnings("deprecation")
	public void ExcluiMeta(){
		
		
		AlertDialog builder = new AlertDialog.Builder(this).create();
		builder.setTitle("Excluir...");
		builder.setMessage("Pan! Se você realmente deseja excluir, clique em Ok!," +
				" Caso tenha sido idiota o suficiente para fazer isso por engano, se vire!");
		builder.setButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int id) {
				// TODO Auto-generated method stub
				MetaDAO db = MetaDAO.getInstance(getBaseContext());
				Meta meta = getMetaFormulario();
				db.deletar(meta);
				db.fecharConexao();
				Intent TelaMetas = new Intent(getBaseContext(), MetasActivity.class);
				startActivity(TelaMetas);
			}
		});
		builder.show();
		
		
	}
	
	private Meta getMetaFormulario(){
		
		EditText txtTreino = (EditText) findViewById(R.id.editMetaTreino);
		EditText txtAquecimento = (EditText) findViewById(R.id.editMetaAquecimento);
		EditText txtCaminhada = (EditText) findViewById(R.id.editMetaCaminhada);
		EditText txtTrote = (EditText) findViewById(R.id.editMetaTrote);
		EditText txtCorrida = (EditText) findViewById(R.id.editMetaCorrida);
		EditText txtTempoTotal = (EditText) findViewById(R.id.editMetaTempoTotal);
		Log.i("teste", "pegou tudo até agora" + txtAquecimento.getText().toString());
		
		String treino = txtTreino.getText().toString();
		Long aquecimento = (Long) Long.parseLong(txtAquecimento.getText().toString());
		Long caminhada = (Long)  Long.parseLong(txtCaminhada.getText().toString());
		Long trote = (Long) Long.parseLong(txtTrote.getText().toString());
		Long corrida = (Long) Long.parseLong(txtCorrida.getText().toString());
		Long tempoTotal = (Long) Long.parseLong(txtTempoTotal.getText().toString());
		
		Log.i("teste", "ainda tudo bem");
		Meta meta = new Meta(id, treino, aquecimento,
				caminhada , trote, corrida, tempoTotal);
		Log.i("teste", "criou a meta");
		
		return meta;
	}

}
