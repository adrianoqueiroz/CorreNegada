package com.android.correnegada;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class CorridaActivity extends Activity {
	Chronometer cronometro;
	Button btIniciar, btPausar;
	boolean isClickPause = false;
	long tempoQuandoParado = 0;
	long tempoDaAtividade = 30000;
	

	boolean pausado = true;
	
	TextView cronometroRegressivo;
	//TextView atividade = (TextView) findViewById(R.id.tVAtividade);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_corrida);

		btIniciar = (Button) findViewById(R.id.bt_cronometro);

		btIniciar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//atividade.setText("Aquecimento");
				cronometro();
				cronometroRegressivo(tempoDaAtividade);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.corrida, menu);
		return true;
	}

	public void cronometro() {
		cronometro = (Chronometer) findViewById(R.id.chronometer);

		// btPausar = (Button) findViewById(R.id.bt_cronometro);

		// Botão INICIAR
		if (isClickPause) {
			cronometro.setBase(SystemClock.elapsedRealtime()
					+ tempoQuandoParado);
			cronometro.start();
			tempoQuandoParado = 0;

			isClickPause = false;

		} else {
			cronometro.setBase(SystemClock.elapsedRealtime());
			cronometro.start();
			tempoQuandoParado = 0;

		}

		/**
		 * //Botão PAUSAR btPausar.setOnClickListener(new View.OnClickListener()
		 * {
		 * 
		 * @Override public void onClick(View arg0) { if(isClickPause == false){
		 *           //entra para false; tempoQuandoParado =
		 *           m_chronometer.getBase() - SystemClock.elapsedRealtime(); }
		 *           m_chronometer.stop(); isClickPause = true; } });
		 **/

	}

	public void cronometroRegressivo(long tempoDaAtividade) {
		cronometroRegressivo = (TextView) findViewById(R.id.countDown);
		
		if (pausado){
			pausado = false;
		new CountDownTimer(tempoDaAtividade, 1000) {
			
			public void onTick(long millisUntilFinished) {
				cronometroRegressivo.setText(millisUntilFinished / 1000 / 60
						+ ":" + millisUntilFinished / 1000 % 60);
			}

			public void onFinish() {
				
				//atividade.setText("Corrida Leve");
				cronometroRegressivo(600000);
				//cronometroRegressivo.setText("10:00");
				
				//TODO: 
			}
		}.start();
		} else {
			
		}
	}

}
