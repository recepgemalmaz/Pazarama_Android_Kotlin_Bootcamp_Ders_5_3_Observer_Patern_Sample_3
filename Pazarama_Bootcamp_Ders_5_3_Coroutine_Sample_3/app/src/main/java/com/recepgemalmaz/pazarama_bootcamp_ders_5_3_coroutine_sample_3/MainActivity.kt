package com.recepgemalmaz.pazarama_bootcamp_ders_5_3_coroutine_sample_3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.recepgemalmaz.pazarama_bootcamp_ders_5_3_coroutine_sample_3.ui.theme.Pazarama_Bootcamp_Ders_5_3_Coroutine_Sample_3Theme
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class MainActivity : ComponentActivity() {

    fun Test(p: Int) {
        if (p % 2 == 0) {
            println("Çift Sayi")
        } else {
            //handle edilmemis exception bu sekilde calsirsa program coker
            throw Exception("Tek Sayi Hatasi")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var errhandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e("Hata", throwable.message.toString())

        }
        CoroutineScope(Dispatchers.Default +errhandler).launch {
            //Ancak bu sekilde supervisorScope kullanarak ikinci launch calisir
            supervisorScope {
                //Bu sekilde progrsam cokmez ancak birinci launch ta hata handle edildigi icin ikinci launch calismaz
                //Bunun onue gecmek icin supervisorScope kullanilabilir
                launch {
                    Test(3)
                }
                delay(1000)
                launch {
                    Test(8)
                    Log.e("Hata", "Ikinci launch calisdi")
                }
            }


        }
    }
}


//CoroutineExceptionHandler kullanimi
/*
class MainActivity : ComponentActivity() {

    fun Test(p:Int){
        if (p%2 ==0){
            println("Çift Sayi")
        }
        else{
            //handle edilmemis exception bu sekilde calsirsa program coker
            throw Exception("Tek Sayi Hatasi")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var errhandler =CoroutineExceptionHandler{
            coroutineContext, throwable ->
            Log.e("Hata",throwable.message.toString())

        }
        CoroutineScope(errhandler).launch {
            //Bu sekilde try catch ile handle edebiliriz boylece program cokmez
            //Ayrica ic ice n ekadar launch olustursakta hatayi yakaliycaktir.
            Test(6)
            delay(1000)
            Test(3)
        }


    }
}
 */

//Normal try catch ile handle edilmesi
//Alttaki sekilde ic ice launclar veya scope lar icinde try catch
//kullanilmasi saglikli olmaz onun yerine coroutineExceptionHandler kullanilabilir.

/*
class MainActivity : ComponentActivity() {

    fun Test(p:Int){
        if (p%2 ==0){
            println("Çift Sayi")
        }
        else{
            //handle edilmemis exception bu sekilde calsirsa program coker
            throw Exception("Tek Sayi")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch{
            //Bu sekilde try catch ile handle edebiliriz boylece program cokmez
            try {

                Test(6)
                delay(1000)
                Test(3)
            }
            catch (e:Exception){
                println(e.message)

            }


        }


    }
}

 */

