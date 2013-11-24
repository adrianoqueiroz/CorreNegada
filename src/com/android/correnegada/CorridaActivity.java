package com.android.correnegada;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class CorridaActivity extends Activity {
	Chronometer cronometro, cronometroRegressivo;
	Button btIniciar, btPausar;
	boolean isClickPause = false;
	long tempoQuandoParado = 0;
	long tempoDaAtividade = 180;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_corrida);
		
		cronometro();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.corrida, menu);
		return true;
	}
	
	public void cronometro(){
		cronometro = (Chronometer) findViewById(R.id.chronometer);
		cronometroRegressivo = (Chronometer) findViewById(R.id.cronometroRegressivo);
		
		btIniciar = (Button) findViewById(R.id.bt_cronometro);

		//btPausar = (Button) findViewById(R.id.bt_cronometro);

		//Botão INICIAR
		btIniciar.setOnClickListener(new View.OnClickListener() {
		 @Override
		 public void onClick(View arg0) {
		  if(isClickPause){ 
		   cronometro.setBase(SystemClock.elapsedRealtime() + tempoQuandoParado);
		   cronometro.start();
		   tempoQuandoParado = 0;
		   
		   cronometroRegressivo.setBase(SystemClock.elapsedRealtime() - tempoDaAtividade);
		   cronometroRegressivo.start();
		   tempoDaAtividade = 180;
		   
		   
		   isClickPause = false;
		   
		  }
		  else{
		   cronometro.setBase(SystemClock.elapsedRealtime());
		   cronometro.start();
		   tempoQuandoParado = 0;
		  }

		 }
		});

		/**
		//Botão PAUSAR
		btPausar.setOnClickListener(new View.OnClickListener() {
		 @Override
		 public void onClick(View arg0) {
		  if(isClickPause == false){ //entra para false;
		   tempoQuandoParado = m_chronometer.getBase() - SystemClock.elapsedRealtime();
		  }
		  m_chronometer.stop();
		  isClickPause = true;   
		 }
		});
**/
	
	}

}
