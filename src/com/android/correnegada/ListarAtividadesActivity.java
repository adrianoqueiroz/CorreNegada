package com.android.correnegada;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class ListarAtividadesActivity extends Activity implements LocationListener{

	private double la;
	private double lo;
	
	TabHost abas;
	private static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abas_atividade);
		
		abas = (TabHost) findViewById(R.id.tabhost);
		abas.setup();
		 
		TabSpec descritor = abas.newTabSpec("aba1");
		descritor.setContent(R.id.atividades);
		descritor.setIndicator(getString(R.string.lblAtividade));
		abas.addTab(descritor);
		 
		descritor = abas.newTabSpec("aba2");
		descritor.setContent(R.id.personalizar);
		descritor.setIndicator(getString(R.string.lblPersonalizar));
		abas.addTab(descritor);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		SQLiteDatabase db = openOrCreateDatabase("atividades.db", Context.MODE_PRIVATE, null);
		
		StringBuilder stb = new StringBuilder();
		
		stb.append("Create table if not exists atividades (");
		stb.append("_id integer primary key, ");
		stb.append("tipoAtividade varchar(50), ");
		stb.append("caloriasPerdidas varchar(8), ");
		stb.append("la double(10,9), ");
		stb.append("lo double(10,9));");
		db.execSQL(stb.toString());
		
		Cursor cursor = db.rawQuery("select * from atividades", null);
		String[] from = {"tipoAtividade", "caloriasPerdidas", "la", "lo"};
		
		int[] to = {R.id.txvAtividade, R.id.txvCaloria, R.id.txvLat, R.id.txvLon}; 
		SimpleCursorAdapter adp = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_listar_modelo, cursor, from , to);
		
		ListView ltwDados = (ListView)findViewById(R.id.atividades);
		ltwDados.setAdapter(adp);
		
//		LTWDADOS.SETONITEMCLICKLISTENER(NEW ADAPTERVIEW.ONITEMCLICKLISTENER() {
//
//			@OVERRIDE
//			PUBLIC VOID ONITEMCLICK(ADAPTERVIEW ADAPTER, VIEW V, INT POSITION, LONG ID) {
//				
//				SQLITECURSOR C = (SQLITECURSOR) ADAPTER.GETADAPTER().GETITEM(POSITION);
//				INTENT I = NEW INTENT(GETBASECONTEXT(), EDITAR.CLASS);
//				I.PUTEXTRA("ID", C.GETINT(0));
//				STARTACTIVITY(I);
//			}
//		});
		
		db.close();
	}
	
	public void cadastrarAtividade_Click(View v){
		
		EditText tipoAtividade = (EditText) findViewById(R.id.txtAtividade);
		EditText caloriasPerdidas = (EditText) findViewById(R.id.txtCaloria);
		
		if (tipoAtividade.getText().toString().length() <= 0){
			tipoAtividade.setError(CAMPO_OBRIGATORIO);
			tipoAtividade.requestFocus();
		}else{
			if (caloriasPerdidas.getText().toString().length() <= 0){
				caloriasPerdidas.setError(CAMPO_OBRIGATORIO);
				caloriasPerdidas.requestFocus();
			}else{
			
				try{
					LocationManager localizacaoDispositivo = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
					Criteria criteria = new Criteria();
					
					String provider = localizacaoDispositivo.NETWORK_PROVIDER;										
					Location location = localizacaoDispositivo.getLastKnownLocation(provider);
					
					la = location.getLatitude();
					lo = location.getLongitude();
					
					SQLiteDatabase db = openOrCreateDatabase("atividades.db", Context.MODE_PRIVATE, null);
					
					ContentValues ctv = new ContentValues();
					ctv.put("tipoAtividade", tipoAtividade.getText().toString());
					ctv.put("caloriasPerdidas", caloriasPerdidas.getText().toString());
					ctv.put("la", la);
					ctv.put("lo", lo);
					
					if (db.insert("atividades", "_id", ctv) > 0){
						abas.setCurrentTab(0);
						onResume();
						Toast.makeText(getBaseContext(), "Atividade cadastrada.", Toast.LENGTH_LONG).show();						
					}else{
						Toast.makeText(getBaseContext(), "Erro! Atividade não cadastrado. ", Toast.LENGTH_LONG).show();
					}
				}catch(Exception e){
					Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}			
		}
	}
	
	public void cancelar_click(View v){
		
		abas.setCurrentTab(0);
	}

	@Override
	public void onLocationChanged(Location location) {
	
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
