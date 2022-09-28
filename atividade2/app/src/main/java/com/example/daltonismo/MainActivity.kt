package com.example.daltonismo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.daltonismo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var teste = Teste("","","")

    lateinit var binding:ActivityMainBinding

    val setResposta = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if(result.resultCode == RESULT_OK){
            var tipo = result.data!!.getStringExtra("respostaItem").toString()
            if (tipo.equals("1")){
                teste.resp1 = result.data!!.getStringExtra("resposta").toString()
            }else if (tipo.equals("2")){
                teste.resp2 = result.data!!.getStringExtra("resposta").toString()
            }else if (tipo.equals("3")){
                teste.resp3 = result.data!!.getStringExtra("resposta").toString()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        binding.buttonTeste1.setOnClickListener{

            val intent = Intent(this, ImageActivity::class.java)
            val b = Bundle()
            b.putInt("teste",1)
            intent.putExtras(b)
            setResposta.launch(intent)
        }
        binding.buttonTeste2.setOnClickListener{

            val intent = Intent(this, ImageActivity::class.java)
            val b = Bundle()
            b.putInt("teste",2)
            intent.putExtras(b)
            setResposta.launch(intent)
        }
        binding.buttonTeste3.setOnClickListener{

            val intent = Intent(this, ImageActivity::class.java)
            val b = Bundle()
            b.putInt("teste",3)
            intent.putExtras(b)
            setResposta.launch(intent)
        }

        binding.buttonClean.setOnClickListener {
            clean()

        }


        binding.button4.setOnClickListener {
            var acertos = 0
            if(teste.resp1.equals("") || teste.resp2.equals("") || teste.resp3.equals("")){
                Toast.makeText(this, "Faça primeiro todos os testes", Toast.LENGTH_SHORT).show()
            }else{
                if(teste.resp1.equals("29")) acertos++
                if (teste.resp2.equals("74")) acertos++
                if(teste.resp3.equals("2")) acertos++

                if(acertos < 3){
                    binding.textViewResultado.text = "Procure um oftamologista!"
                }else{
                    binding.textViewResultado.text="Você não possui daltonismo!"
                }
            }
        }

    }

    fun clean(){
        binding.textResposta1.text = ""
        binding.textResposta2.text = ""
        binding.textResposta3.text = ""
        binding.textViewResultado.text = "Resultado do teste"
        teste.resp1 = ""
        teste.resp2 = ""
        teste.resp3 = ""
    }

    override fun onResume() {
        super.onResume()
        binding.teste = teste
    }
}