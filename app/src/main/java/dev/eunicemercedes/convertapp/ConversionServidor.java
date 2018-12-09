package dev.eunicemercedes.convertapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

public class ConversionServidor extends AsyncTask<Void,Void , Void> {
Button button;

    public ConversionServidor(Button button) {
        this.button = button;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid );
        button.setVisibility(View.VISIBLE);
        Log.i(TAG,"proceso toma dato completado");
    }




    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(Conexiones.construirConsultaConversion( ));
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();


            InputStream inputStream = conexion.getInputStream();

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");


            if (scanner.hasNext()) {

                JSONObject resultado = new JSONObject(scanner.next());
                Log.i(TAG,resultado.toString());
                Conversions.initializeConversions(resultado);
                //        conversion = new Conversion(resultado, conversions[0].getValorConvertir(), conversions[0].getMonedaDestino());
                conexion.disconnect();
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "doInBackground: ",e );

        } catch (Exception ex) {
            Log.e(TAG, "doInBackground: ",ex );
        }

        return null;
    }


}
