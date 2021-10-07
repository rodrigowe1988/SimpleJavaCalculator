package com.example.simplejavacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

//implementar uma View para reconhecer os clicks. Usando o método OnClickListener na MainActivity
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //criar os objetos e os botôes da calculadora
    private Button numeroZero, numeroUm, numeroDois, numeroTres, numeroQuatro, numeroCinco, numeroSeis, numeroSete,
            numeroOito, numeroNove, ponto, soma, subtracao, multiplicacao, divisao, igual, botao_limpar;

    private TextView txtExpressao, txtResultado;
    //como coloquei o backspace como imagem, preciso usar um método diferente
    private ImageView backspace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //iniciando o método de início dos componentes
        IniciarComponentes();

        //esconder a barra superior
        getSupportActionBar().hide();

        //eventos dos componetes do tipo button, recuperar o "CLICK"
        numeroZero.setOnClickListener(this);
        numeroUm.setOnClickListener(this);
        numeroDois.setOnClickListener(this);;
        numeroTres.setOnClickListener(this);
        numeroQuatro.setOnClickListener(this);
        numeroCinco.setOnClickListener(this);
        numeroSeis.setOnClickListener(this);
        numeroSete.setOnClickListener(this);
        numeroOito.setOnClickListener(this);
        numeroNove.setOnClickListener(this);
        ponto.setOnClickListener(this);
        soma.setOnClickListener(this);
        subtracao.setOnClickListener(this);
        multiplicacao.setOnClickListener(this);
        divisao.setOnClickListener(this);

        //botão 'C' de limpar, para limpar tanto o campo das Expressões, quanto de Resultado
        botao_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtExpressao.setText("");
                txtResultado.setText("");
            }
        });

        //botão para limpar somente um número de cada vez
        //para fazer isso foi preciso pegar o tamanho da Expressão (length) e diminuir dela o último
        //elemento, por isso o "string.length() -1;", salvando o resultado numa outra variável que
        // aparecerá na tela com o número atualizado
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView expressao = findViewById(R.id.txt_expressao);
                String string = expressao.getText().toString();

                if (!string.isEmpty()) {

                    byte    var0 = 0;
                    int     var1 = string.length()-1;
                    String  txtExpressao =  string.substring(var0, var1);
                    expressao.setText(txtExpressao);
                }
                txtResultado.setText("");
            }
        });

        //método para o botão de IGUAL '=' usando o try/catch
        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Expression expressao = new ExpressionBuilder(txtExpressao.getText().toString()).build();
                    //variável do tipo double que recebe a Expressão
                    double resultado = expressao.evaluate();
                    //variável do tipo long para números grandes
                    long longResult = (long) resultado;


                    if (resultado == (double) longResult) {
                        txtResultado.setText((CharSequence) String.valueOf(longResult));
                    } else {
                        txtResultado.setText((CharSequence) String.valueOf(resultado));
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    //recuperar do activity_main.xml os id dos elementos da aplicação
    private void IniciarComponentes() {
        numeroZero      = findViewById(R.id.numero_zero);
        numeroUm        = findViewById(R.id.numero_um);
        numeroDois      = findViewById(R.id.numero_dois);
        numeroTres      = findViewById(R.id.numero_tres);
        numeroQuatro    = findViewById(R.id.numero_quatro);
        numeroCinco     = findViewById(R.id.numero_cinco);
        numeroSeis      = findViewById(R.id.numero_seis);
        numeroSete      = findViewById(R.id.numero_sete);
        numeroOito      = findViewById(R.id.numero_oito);
        numeroNove      = findViewById(R.id.numero_nove);
        ponto           = findViewById(R.id.ponto);
        soma            = findViewById(R.id.soma);
        subtracao       = findViewById(R.id.subtracao);
        multiplicacao   = findViewById(R.id.multiplicacao);
        divisao         = findViewById(R.id.divisao);
        igual           = findViewById(R.id.igual);
        botao_limpar    = findViewById(R.id.bt_limpar);
        txtExpressao    = findViewById(R.id.txt_expressao);
        txtResultado    = findViewById(R.id.txt_resultado);
        backspace       = findViewById(R.id.backspace);
    }
    //criando um método responsável que vai acrescentar os cálculos que estão sendo realizados
    public void AcrescentarUmaExpressao(String string, boolean limpar_dados)
    {
        if (txtResultado.getText().equals("")) {
            txtExpressao.setText(" ");
        }

        if (limpar_dados)
        {
            txtResultado.setText(" ");
            //acrescentar uma string para a nossa expressão
            txtExpressao.append(string);
        } else {
            txtExpressao.append(txtResultado.getText());
            txtExpressao.append(string);
            txtResultado.setText(" ");
        }
    }

    //método responsável pelo click dos botões
    @Override
    public void onClick(View view) {
        //o getId é usado para recuperar o Id do botão que foi clicado
        switch (view.getId()) {
            case R.id.numero_zero:
                AcrescentarUmaExpressao("0",true);
                break;
            case R.id.numero_um:
                AcrescentarUmaExpressao("1", true);
                break;
            case R.id.numero_dois:
                AcrescentarUmaExpressao("2", true);
                break;
            case R.id.numero_tres:
                AcrescentarUmaExpressao("3", true);
                break;
            case R.id.numero_quatro:
                AcrescentarUmaExpressao("4", true);
                break;
            case R.id.numero_cinco:
                AcrescentarUmaExpressao("5", true);
                break;
            case R.id.numero_seis:
                AcrescentarUmaExpressao("6", true);
                break;
            case R.id.numero_sete:
                AcrescentarUmaExpressao("7", true);
                break;
            case R.id.numero_oito:
                AcrescentarUmaExpressao("8", true);
                break;
            case R.id.numero_nove:
                AcrescentarUmaExpressao("9", true);
                break;
            case R.id.ponto:
                AcrescentarUmaExpressao(".", true);
                break;
            case R.id.soma:
                AcrescentarUmaExpressao("+", false);
                break;
            case R.id.subtracao:
                AcrescentarUmaExpressao("-", false);
                break;
            case R.id.divisao:
                AcrescentarUmaExpressao("/", false);
                break;
            case R.id.multiplicacao:
                AcrescentarUmaExpressao("*", false);
                break;





        }
    }
}

