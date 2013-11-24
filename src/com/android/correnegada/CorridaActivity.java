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

	TextView cronometroRegressivo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_corrida);

		btIniciar = (Button) findViewById(R.id.bt_cronometro);

		btIniciar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				cronometro();
				cronometroRegressivo();
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

	public void cronometroRegressivo() {
		cronometroRegressivo = (TextView) findViewById(R.id.countDown);
		new CountDownTimer(tempoDaAtividade, 1000) {

			public void onTick(long millisUntilFinished) {
				cronometroRegressivo.setText(millisUntilFinished / 1000 / 60
						+ ":" + millisUntilFinished / 1000 % 60);
			}

			public void onFinish() {
				cronometroRegressivo.setText("Concluido!");
			}
		}.start();
	}

}
