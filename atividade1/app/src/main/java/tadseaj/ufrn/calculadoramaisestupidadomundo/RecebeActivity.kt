package tadseaj.ufrn.calculadoramaisestupidadomundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class RecebeActivity : AppCompatActivity() {
    lateinit var textViewLabelVariavel: TextView;
    lateinit var editTextVariavel: EditText;
    lateinit var  buttonOK: Button;
    lateinit var buttonCancelar:Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recebe)

        val variavel = intent.extras?.getString("VARIAVEL")
        val valor = intent.extras?.getInt("VALOR")

         textViewLabelVariavel = findViewById<TextView>(R.id.textViewLabelVariavel)
         editTextVariavel = findViewById<EditText>(R.id.editTextVariavel)

        textViewLabelVariavel.text = variavel
        editTextVariavel.setText(valor.toString())

         buttonOK = findViewById<Button>(R.id.buttonOK)
         buttonCancelar = findViewById<Button>(R.id.buttonCancelar)

        buttonOK.setOnClickListener {
            val intent = Intent()
            val bundle = Bundle()

            //val editTextVariavel = findViewById<EditText>(R.id.editTextVariavel)
            bundle.putInt("VALOR", editTextVariavel.text.toString().toInt())
            intent.putExtras(bundle)

            setResult(RESULT_OK, intent)
            finish()
        }

        buttonCancelar.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}