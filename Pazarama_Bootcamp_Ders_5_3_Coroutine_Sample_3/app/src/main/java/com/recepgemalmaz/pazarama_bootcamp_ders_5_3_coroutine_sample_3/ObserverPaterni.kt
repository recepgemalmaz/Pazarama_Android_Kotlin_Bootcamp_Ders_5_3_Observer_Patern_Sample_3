package com.recepgemalmaz.pazarama_bootcamp_ders_5_3_coroutine_sample_3

interface IObserver {

    fun onAction(value: String) {

    }

}

interface IObserverable {
    fun Register(observer: IObserver)
    fun UnRegister(observer: IObserver)
    fun Notify(value: String) {

    }
}

class StockEntity : IObserverable {

    public var Marka: String = ""
    public var Fiyat: Int = 0
    public var Stok: Int = 0

    private var observers: ArrayList<IObserver>? = null
    init {
        observers = ArrayList()
    }

    fun Satis(adet :Int){
        this.Stok -= adet
        Notify("Stokta $Stok adet kaldi")
    }

    fun Indirim (oran :Int){
        this.Fiyat -= (this.Fiyat * oran) / 100
        Notify("Fiyat $Fiyat TL oldu")
    }

    override fun Register(observer: IObserver) {
        observers!!.add(observer)
    }
    override fun UnRegister(observer: IObserver) {
        observers!!.remove(observer)
    }
    override fun Notify(value: String) {
        for (o in this.observers!!) {
            o.onAction(value)
        }

    }
}

abstract class View: IObserver {

    var Name: String = ""
    var Text : String = ""

    override fun onAction(value: String) {
        this.Text = value
    }
    abstract fun Bind(subject: IObserverable)
    abstract fun UnBind(subject: IObserverable)

}

class TextView: View() {

    override fun Bind(subject: IObserverable) {
        subject.Register(this)
    }

    override fun UnBind(subject: IObserverable) {
        subject.UnRegister(this)
    }

}

fun Display(v: View) {
    println("Name: ${v.Name} Text: ${v.Text}")
}

fun main(){
    var stockEntity = StockEntity()
    stockEntity.Marka = "Samsung"
    stockEntity.Fiyat = 1000
    stockEntity.Stok = 100

    var txt1 = TextView()
    txt1.Name = "t1"
    var txt2 = TextView()
    txt2.Name = "t2"

    txt1.Bind(stockEntity)
    txt2.Bind(stockEntity)

    Display(txt1)
    Display(txt2)

    stockEntity.Satis(10)

    Display(txt1)
    Display(txt2)

    stockEntity.Indirim(50)

    Display(txt1)
    Display(txt2)






}


//observer patterni nedir?
//observer patterni bir nesnenin diger nesneleri dinlemesini saglayan bir patterndir.