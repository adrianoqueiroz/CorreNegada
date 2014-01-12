package com.android.correnegada;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;

public class PrincipalActivity extends Activity {

	ListView listView;

	Button btMetas, btResultados, btDicas, btCorrer, btSettings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		/*Meta meta = new Meta(1, "Treino 1",3,4,10,10,50);
		Meta meta2 = new Meta(2, "Treino 2",3,4,10,10,50);
		Meta meta3 = new Meta(3, "Treino 3",3,4,10,10,50);
		Meta meta4 = new Meta(4, "Treino 4",3,4,10,10,50);
		Meta meta5 = new Meta(5, "Treino 5",3,4,10,10,50);
		MetaDAO metaDAO = MetaDAO.getInstance(getBaseContext());
		
		
		metaDAO.Salvar(meta);
		metaDAO.Salvar(meta2);
		metaDAO.Salvar(meta3);
		metaDAO.Salvar(meta4);
		metaDAO.Salvar(meta5);
		
		metaDAO.fecharConexao();*/

		
        //final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        //final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
				
		btMetas = (Button) findViewById(R.id.btMetas);
		btResultados = (Button) findViewById(R.id.btResultados);
		btDicas = (Button) findViewById(R.id.btDicas);
		btCorrer = (Button) findViewById(R.id.btCorrer);
		btSettings = (Button) findViewById(R.id.btSettings);
		
		btCorrer.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent telaCorrida = new Intent(PrincipalActivity.this,
						CorridaActivity.class);
				
				v.startAnimation(animScale);
				
				PrincipalActivity.this.startActivity(telaCorrida);
			}
		});

		btResultados.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				arg0.startAnimation(animRotate);
			}});
		
		btDicas.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				arg0.startAnimation(animRotate);
				
				Intent dicas = new Intent(getBaseContext(), DicasActivity.class);
				PrincipalActivity.this.startActivity(dicas);	
			}});
		
		btSettings.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				arg0.startAnimation(animRotate);
				
				Intent settings = new Intent(getBaseContext(), ConfiguracoesActivity.class);
				PrincipalActivity.this.startActivity(settings);	
			}});
	}
   

	public void metasClick(View v) {
		Intent TelaMetas = new Intent(getBaseContext(), MetasActivity.class);
		startActivity(TelaMetas);
	}
	
	public void dicasOnClick(View v) {
		Intent dicas = new Intent(getBaseContext(), DicasActivity.class);
		startActivity(dicas);
	}	
	
	public void locaisTreinoClick(View v) {
		Intent LocaisTreino = new Intent(getBaseContext(), LocaisTreinoActivity.class);
		startActivity(LocaisTreino);
	}
	
	public void iniciarCorrida(View v) {
		Intent TelaCorrida = new Intent(getBaseContext(), CorridaActivity.class);
		startActivity(TelaCorrida);
	}

	public void atividadesClick(View v) {

		Intent atividades = new Intent(getBaseContext(), ListarAtividadesActivity.class);
		startActivity(atividades);
	}

	public void AlterarConfiguracoesClick(View v) {

		Intent TelaConf = new Intent(getBaseContext(),
				ConfiguracoesActivity.class);
		startActivity(TelaConf);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

}
