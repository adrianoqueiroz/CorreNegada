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
	
	long tempoAquecimento = 15000;	
	long tempoCaminhada = 20000;
	long tempoTrote = 30000;
	long tempoCorrida = 30000;	
	long tempoDesaquecimento = 15000;
	long tempoTotal = 130000;

	boolean naoIniciado = true;
	
	TextView cronometroRegressivo;
	TextView atividade;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_corrida);

		btIniciar = (Button) findViewById(R.id.bt_cronometro);

		btIniciar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cronometro();
				cronometroRegressivo(0);
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

	
	
	public void cronometroRegressivo(final int idAtividade) {
		if (naoIniciado){
			naoIniciado = false;
			
			cronometroRegressivo = (TextView) findViewById(R.id.countDown);
			
			atividade = (TextView) findViewById(R.id.tVAtividadeAtual);
			
			long tempoDaAtividade = 0;
			
			switch (idAtividade){
			case 0:
				atividade.setText("Aquecimento");
				tempoDaAtividade = tempoAquecimento;
				break;
			case 1:
				atividade.setText("Caminhada");	
				tempoDaAtividade = tempoCaminhada;
				break;
			case 2:
				atividade.setText("Trote");
				tempoDaAtividade = tempoTrote;
				break;
			case 3:
				atividade.setText("Corrida");
				tempoDaAtividade = tempoCorrida;
				break;	
			case 4:
				atividade.setText("Desaquecimento");
				tempoDaAtividade = tempoDesaquecimento;
				break;
			}
			
			new CountDownTimer(tempoDaAtividade, 1000) {
			
			public void onTick(long millisUntilFinished) {
				long minutos = millisUntilFinished / 1000 / 60;
				long segundos = millisUntilFinished / 1000 % 60;
				if(minutos < 10){
					if(segundos < 10){
						cronometroRegressivo.setText("0" + minutos + ":0" + segundos);
					} else {
						cronometroRegressivo.setText("0" + minutos + ":" + segundos);
					}
				} else {
					if(segundos < 10){
						cronometroRegressivo.setText(minutos + ":0" + segundos);
					} else {
						cronometroRegressivo.setText("0" + minutos + ":" + segundos);
					}
					
				}
			}

			public void onFinish() {
				
				//se o cronometro nao chegou no tempo totalproxima atividade se o tempo final nao foi atingido
				//TODO: recuperar tempo do cronometro principal
				long tempoAtualCronometro = 1000;
				
				if (tempoTotal > tempoAtualCronometro ){
					if(idAtividade < 3){
						naoIniciado = true;
						cronometroRegressivo(idAtividade + 1);
					} else {
						naoIniciado = true;
						cronometroRegressivo(1);
					}
				} else {
					naoIniciado = true;
					cronometroRegressivo(4);					
				}
				
				
			}
		}.start();
		} else {
			
		}
	}

}
