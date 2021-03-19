package com.example.practicaseis;import androidx.appcompat.app.AppCompatActivity;import android.os.Bundle;import android.widget.Button;import com.google.gson.Gson;import java.io.BufferedWriter;import java.io.IOException;import java.io.OutputStream;import java.io.OutputStreamWriter;import java.net.Socket;public class MainActivity extends AppCompatActivity {    private Button derecha;    private Button arriba;    private Button izquierda;    private Button abajo;    private Button colorcito;    private BufferedWriter bwriter;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        derecha = findViewById(R.id.derecha);        arriba = findViewById(R.id.arriba);        izquierda = findViewById(R.id.izquierda);        abajo = findViewById(R.id.abajo);        colorcito = findViewById(R.id.colorcito);        derecha.setOnClickListener(        v -> {                    trabajocong(1);        }        );        arriba.setOnClickListener(                v -> {                            trabajocong(2);                }        );        izquierda.setOnClickListener(                v -> {                                trabajocong(3);                }        );        abajo.setOnClickListener(                v -> {                            trabajocong(4);                }        );        colorcito.setOnClickListener(                v -> {                            trabajocong(5);                }        );        new Thread(                () -> {                    try {                        Socket socket = new Socket("10.0.2.2",5000);                        OutputStream os= socket.getOutputStream();                        OutputStreamWriter osw= new OutputStreamWriter(os);                        bwriter= new BufferedWriter(osw);                    } catch (IOException e) {                        e.printStackTrace();                    }                }        ).start();    }    public void trabajocong(int b){       new Thread(              () -> {                  Gson gson = new Gson();                  Coordenadas obj = new Coordenadas(b);                  //Serialización                  String coordStr = gson.toJson(obj);                  try {                      bwriter.write(coordStr+"\n");                      bwriter.flush();                  } catch (IOException e) {                      e.printStackTrace();                  }              }       ).start();    }}