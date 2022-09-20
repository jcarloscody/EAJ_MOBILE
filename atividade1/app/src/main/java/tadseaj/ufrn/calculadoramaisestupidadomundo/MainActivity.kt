package tadseaj.ufrn.calculadoramaisestupidadomundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    var variavelX:Int = 0
    var variavelY:Int = 0
    var resultado:Int = 0
    lateinit var textViewVariávelX: TextView;
    lateinit var textViewVariávelY: TextView;
    lateinit var textViewResultado: TextView;
    lateinit var buttonSetVariávelX: Button;
    lateinit var buttonSetVariávelY: Button;
    lateinit var buttonSetCalcular: Button;

    val setVariavelXlauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == RESULT_OK){
            variavelX = result.data!!.getIntExtra("VALOR", 0)
            //val textViewVariávelX = findViewById<TextView>(R.id.textViewVariávelX)
            textViewVariávelX.text ="${variavelX}"
        }else{
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
        }
    }

    val setVariavelYlauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == RESULT_OK){
            variavelY = result.data!!.getIntExtra("VALOR", 0)
           // val textViewVariávelY = findViewById<TextView>(R.id.textViewVariávelY)
            textViewVariávelY.text ="${variavelY}"
        }else {
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         textViewVariávelX = findViewById<TextView>(R.id.textViewVariávelX)
         textViewVariávelY = findViewById<TextView>(R.id.textViewVariávelY)
         textViewResultado = findViewById<TextView>(R.id.textViewResultado)

        textViewVariávelX.text = "${variavelX}"
        textViewVariávelY.text = "${variavelY}"
        textViewResultado.text = "${resultado}"

        buttonSetVariávelX = findViewById<Button>(R.id.buttonSetVariávelX)
        buttonSetVariávelY = findViewById<Button>(R.id.buttonSetVariávelY)
        buttonSetCalcular = findViewById<Button>(R.id.buttonSetCalcular)

        buttonSetVariávelX.setOnClickListener {
            val intent = Intent(this, RecebeActivity::class.java)

            val bundle = Bundle()

            bundle.putString("VARIAVEL", "variavel X")
            bundle.putInt("VALOR", variavelX)
            intent.putExtras(bundle)

            setVariavelXlauncher.launch(intent)
        }
        buttonSetVariávelY.setOnClickListener {
            val intent = Intent(this, RecebeActivity::class.java)

            val bundle = Bundle()
            bundle.putString("VARIAVEL", "Variavel Y")
            bundle.putInt("VALOR", variavelY)
            intent.putExtras(bundle)

            setVariavelYlauncher.launch(intent)
        }

        buttonSetCalcular.setOnClickListener {
            textViewResultado.text = (variavelX + variavelY).toString()
            var r:String =  (variavelX + variavelY).toString();
            Toast.makeText(this, "Resultado = " + r, Toast.LENGTH_SHORT).show()
        }
    }
}