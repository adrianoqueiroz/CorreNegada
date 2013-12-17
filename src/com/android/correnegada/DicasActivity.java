package com.android.correnegada;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DicasActivity extends Activity {
	private List<Dica> dicas = new ArrayList<Dica>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dicas);
		
		popularListaDicas();
		popularListView();
		registrarClickCallBack();
		
	}

	private class MyListAdapter extends ArrayAdapter<Dica>{
		public MyListAdapter(){
			super(DicasActivity.this, R.layout.dicas_item_view, dicas);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemView = convertView;
			if(itemView == null){
				itemView = getLayoutInflater().inflate(R.layout.dicas_item_view, parent, false);
			}
			
			Dica dicaAtual = dicas.get(position);
			//icone
			ImageView imageView = (ImageView) itemView.findViewById(R.id.item_icon);
			imageView.setImageResource(dicaAtual.getImagemID());
			
			//Categoria
			TextView categoria = (TextView) itemView.findViewById(R.id.item_categoria);
			categoria.setText(dicaAtual.getCategoria());

			//Dica
			TextView dica = (TextView) itemView.findViewById(R.id.item_dica);
			dica.setText(dicaAtual.getDica());
			return itemView;
		}
	}

	private void popularListaDicas() {
		dicas.add(new Dica(R.drawable.alimentacao, "Alimentação", "Quando você corre, seu corpo obtém a energia do glicogênio muscular de fácil acesso (carboidratos armazenados e processados) e das reservas de gordura menos acessíveis. Todos nós temos reservas de gorduras, mas precisamos garantir altos níveis de glicogênio antes, durante e após as corridas. É onde entra a nutrição inteligente."));
		dicas.add(new Dica(R.drawable.preparacao, "Antes da Corrida", "Algumas pessoas podem correr 30 minutos depois de fazer um lanche; outras precisam ficar em jejum por horas para exercitar-se com conforto. Não é essencial alimentar-se antes de corridas de uma hora, mas corridas longas exigem planejamento. Um pouco de energia à mão faz diferença. Uma barra de cereais e um suco de frutas funcionam bem para a maioria das pessoas. Evite gorduras e proteínas, porque sua digestão é difícil."));
		dicas.add(new Dica(R.drawable.durante, "Durante a corrida", "Vale a pena se reabastecer de energia em corridas de mais de 45 minutos e entre as repetições, nas sessões de velocismo curtas e intensas. É essencial manter o estômago confortável, portanto evite alimentos sólidos, exceto os de digestão fácil como barras energéticas e balas de geléia. Outra opção são as bebidas esportivas e os géis"));
	}

	private void popularListView() {
		ArrayAdapter<Dica> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.dicasListView);
		list.setAdapter(adapter);
		
	}

	
	private void registrarClickCallBack() {
		ListView list = (ListView) findViewById(R.id.dicasListView);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
					long id) {
				// TODO Auto-generated method stub
				Dica clickedDica = dicas.get(position);
				String mensagem = "Você clicou na posição " + position
						+ "Dica " + clickedDica.getDica();
				Toast.makeText(DicasActivity.this, mensagem, Toast.LENGTH_LONG).show();
			}
			
		});
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dicas, menu);
		return true;
	}

}
