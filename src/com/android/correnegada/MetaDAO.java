package com.android.correnegada;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MetaDAO {
	
	public static final String NOME_TABELA = "Metas";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_TREINO = "treino";
    public static final String COLUNA_AQUECIMENTO = "aquecimento";
    public static final String COLUNA_CAMINHADA = "caminhada";
    public static final String COLUNA_TROTE = "trote";
    public static final String COLUNA_CORRIDA = "corrida";
    public static final String COLUNA_TEMPO_TOTAL = "tempoTotal";
    
    public static final String SCRIPT_CRIACAO_TABELA_METAS = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA + "("
            + COLUNA_ID + " INTEGER PRIMARY KEY, " + COLUNA_TREINO + " TEXT, " + COLUNA_AQUECIMENTO + " INTEGER, "
            + COLUNA_CAMINHADA + " INTEGER, " + COLUNA_TROTE + " INTEGER, " + COLUNA_CORRIDA + " INTEGER, " +
            COLUNA_TEMPO_TOTAL + " INTEGER" +");";
    
    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;
    
    private SQLiteDatabase dataBase = null;
    
    private static MetaDAO instance;
    
    public static MetaDAO getInstance(Context context) {
        if(instance == null)
            instance = new MetaDAO(context);
        return instance;
    }
    
    private MetaDAO(Context context) {
        PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(context);
        dataBase = persistenceHelper.getWritableDatabase();
    }
    
    public void Salvar(Meta meta) {
        ContentValues values = gerarContentValuesMeta(meta);
        dataBase.insert(NOME_TABELA, null, values);
    }
    
    public List<Meta> recuperarTodos() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Meta> metas = construirMetaPorCursor(cursor);
 
        return metas;
    }
    
    public Meta getMetaByID(int id){
    	String sql = "select * from " + NOME_TABELA + " where id = " + id;
    	Cursor cursor = dataBase.rawQuery(sql, null);
    	List<Meta> m = construirMetaPorCursor(cursor);
    	return m.get(0);
    }
     
    public void deletar(Meta meta) {
    	 
        String[] valoresParaSubstituir = {
                String.valueOf(meta.getId())
        };
 
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }
    
    public void editar(Meta meta) {
        ContentValues valores = gerarContentValuesMeta(meta);
 
        String[] valoresParaSubstituir = {
                String.valueOf(meta.getId())
        };
 
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }
    
    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen()){
            dataBase.close();
            instance = null;
        }
    }
    
    private List<Meta> construirMetaPorCursor(Cursor cursor) {
        List<Meta> metas = new ArrayList<Meta>();
        if(cursor == null)
            return metas;
         
        try {
 
            if (cursor.moveToFirst()) {
                do {
 
                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexTreino = cursor.getColumnIndex(COLUNA_TREINO);
                    int indexAquecimento = cursor.getColumnIndex(COLUNA_AQUECIMENTO);
                    int indexCaminhada = cursor.getColumnIndex(COLUNA_CAMINHADA);
                    int indexTrote = cursor.getColumnIndex(COLUNA_TROTE);
                    int indexCorrida = cursor.getColumnIndex(COLUNA_CORRIDA);
                    int indexTempoTotal = cursor.getColumnIndex(COLUNA_TEMPO_TOTAL);
 
                    int id = cursor.getInt(indexID);
                    String treino = cursor.getString(indexTreino);
                    long aquecimento = cursor.getLong(indexAquecimento);
                    long caminhada = cursor.getLong(indexCaminhada);
                    long trote = cursor.getLong(indexTrote);
                    long corrida = cursor.getLong(indexCorrida);
                    long tempoTotal = cursor.getLong(indexTempoTotal);
                    
 
                    Meta meta = new Meta(id, treino, aquecimento, caminhada, trote, corrida, tempoTotal);
 
                    metas.add(meta);
 
                } while (cursor.moveToNext());
            }
             
        } finally {
            cursor.close();
        }
        return metas;
    }
    
    
    private ContentValues gerarContentValuesMeta(Meta meta) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_TREINO, meta.getTreino());
        values.put(COLUNA_AQUECIMENTO, meta.getAquecimento());
        values.put(COLUNA_CAMINHADA, meta.getCaminhada());
        values.put(COLUNA_TROTE, meta.getTrote());
        values.put(COLUNA_CORRIDA, meta.getCorrida());
        values.put(COLUNA_TEMPO_TOTAL, meta.getTempoTotal());
        
 
        return values;
    }

}
