package com.android.correnegada;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class InformacoesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_informacoes);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sobre, menu);
		return true;
	}

}
