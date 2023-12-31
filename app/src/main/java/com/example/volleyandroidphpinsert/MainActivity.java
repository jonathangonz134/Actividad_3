package com.example.volleyandroidphpinsert;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Creating EditText.
    EditText FirstName, LastName, Email, Edad, Sexo, Telefono ;

    // Creating button;
    Button InsertButton;

    // Creating Volley RequestQueue.
    RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String FirstNameHolder, LastNameHolder, EmailHolder, EdadHolder, SexoHolder, TelefonoHolder ;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    String HttpUrl = "https://jonathangonz134.000webhostapp.com/registro_de_usuarios.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assigning ID's to EditText.
        FirstName = (EditText) findViewById(R.id.editTextFirstName);
        LastName = (EditText) findViewById(R.id.editTextLastName);
        Email = (EditText) findViewById(R.id.editTextEmail);
        Edad = (EditText) findViewById(R.id.editTexEdad);
        Sexo = (EditText) findViewById(R.id.editTextSexo);
        Telefono = (EditText) findViewById(R.id.editTextTelefono);

        // Assigning ID's to Button.
        InsertButton = (Button) findViewById(R.id.ButtonInsert);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        progressDialog = new ProgressDialog(MainActivity.this);

        // Adding click listener to button.
        InsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Showing progress dialog at user registration time.
                progressDialog.setMessage("Espere, estamos insertando sus datos en el Servidor");
                progressDialog.show();

                // Calling method to get value from EditText.
                GetValueFromEditText();

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing response message coming from server.
                                Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            private VolleyError error;


                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing error message if something goes wrong.
                                Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {

                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();

                        // Adding All values to Params.
                        params.put("name", FirstNameHolder);
                        params.put("username", LastNameHolder);
                        params.put("password", EmailHolder);
                        params.put("edad", EdadHolder);
                        params.put("sexo", SexoHolder);
                        params.put("telefono", TelefonoHolder);

                        return params;
                    }

                };

                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);

            }
        });

    }

    // Creating method to get value from EditText.
    public void GetValueFromEditText(){

        FirstNameHolder = FirstName.getText().toString().trim();
        LastNameHolder = LastName.getText().toString().trim();
        EmailHolder = Email.getText().toString().trim();
        EdadHolder = Edad.getText().toString().trim();
        SexoHolder = Sexo.getText().toString().trim();
        TelefonoHolder = Telefono.getText().toString().trim();

    }

}