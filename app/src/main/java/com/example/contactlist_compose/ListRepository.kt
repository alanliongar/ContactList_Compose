package com.example.contactlist_compose

class ListRepository {
    private val ale: Contact =
        Contact(id = 0, name = "Ale", tel = "+55 11 91234-5678", image = R.drawable.ale)
    private val bach: Contact =
        Contact(id = 1, name = "Bach", tel = "+55 11 91234-5678", image = R.drawable.bach)
    private val caianCorporativo: Contact =
        Contact(
            id = 2,
            name = "Caian Corporativo",
            tel = "+55 11 91234-5678",
            image = R.drawable.caian1
        )
    private val caianPessoal: Contact =
        Contact(
            id = 3,
            name = "Caian Pessoal",
            tel = "+55 11 91234-5678",
            image = R.drawable.caian2
        )
    private val ximbinha: Contact =
        Contact(id = 4, name = "Ximbinha", tel = "+55 11 91234-5678", image = R.drawable.chimbinha)
    private val srDefumado: Contact =
        Contact(id = 5, name = "Sr Defumado", tel = "+55 11 91234-5678", image = R.drawable.furtado)
    private val goku: Contact =
        Contact(id = 6, name = "Goku", tel = "+55 11 91234-5678", image = R.drawable.goku)
    private val lalauMachiavelli: Contact =
        Contact(
            id = 7,
            name = "Lalau Machiavelli",
            tel = "+55 11 91234-5678",
            image = R.drawable.lalau
        )
    private val miguelJefferson: Contact =
        Contact(
            id = 8,
            name = "Miguel Jefferson",
            tel = "+55 11 91234-5678",
            image = R.drawable.miguel
        )
    private val muniz: Contact =
        Contact(id = 9, name = "Muniz", tel = "+55 11 91234-5678", image = R.drawable.muniz)
    private val mussum: Contact =
        Contact(id = 10, name = "Mussum", tel = "+55 11 91234-5678", image = R.drawable.mussum)
    private val pericles: Contact =
        Contact(id = 11, name = "Pericles", tel = "+55 11 91234-5678", image = R.drawable.pericles)
    private val urecubaba: Contact =
        Contact(id = 12, name = "Urecubaba", tel = "+55 11 91234-5678", image = R.drawable.rockbama)
    private val silvao: Contact =
        Contact(id = 13, name = "Silvao", tel = "+55 11 91234-5678", image = R.drawable.silvao)
    private val gatinho: Contact =
        Contact(id = 14, name = "Gatinho", tel = "+55 11 91234-5678", image = R.drawable.gato)

    fun getContactList(): List<Contact> {
        return listOf(
            ale, bach, caianCorporativo, caianPessoal, ximbinha, srDefumado, goku,
            lalauMachiavelli, miguelJefferson, muniz, mussum, urecubaba, silvao, gatinho
        )
    }

    fun getContactById(id: Int): Contact {
        return getContactList().filter { it.id == id }.first()
    }

}