package com.natasha.mmzdemo.infrastructure.helpers

import com.ibm.icu.text.RuleBasedNumberFormat
import com.natasha.mmzdemo.domain.core.entity.Client
import com.natasha.mmzdemo.domain.core.entity.Si
import com.natasha.mmzdemo.domain.core.generatorsword.DocumentGenerator
import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.FileInputStream
import java.util.*

@Component
class InvoiceForPaymentDocxGenerator(@Value("./resources/f3.docx") _inputPath: String, @Value("./public/") _outPath: String) : DocumentGenerator(_inputPath, _outPath) {
    fun generate(client: Client, listSi: List<Si>, nameOfFile: String){
        val fileInputStream = FileInputStream(super.inputPath)
        docxFile = XWPFDocument(OPCPackage.open(fileInputStream))
        var table = docxFile.tables[3]

        var number: Int = 0
        var sum: Double = 0.0

        for (si in listSi){
            number++
            val row = table.createRow()
            row.getCell(0).text = "$number"
            row.getCell(1).text = si.name
            row.getCell(2).text = si.count.toString()
            row.getCell(3).text = "услуга"
            row.getCell(4).text = si._price.toString()
            row.getCell(5).text = "${si.count * si._price!!}"
            sum += si._price!! * si.count
        }

        val nf = RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT)

        super.replaceTextOnDocument(docxFile, "sum", sum.toString())
        super.replaceTextOnDocument(docxFile, "word", nf.format(sum))
        super.replaceTextOnDocument(docxFile, "count", number.toString())

        val i: Int = sum.toInt();
        val d2: Int = ((sum - i)*100).toInt()
        super.replaceTextOnDocument(docxFile, "simple", d2.toString())

        table = docxFile.tables[2]
        val row = table.getRow(0)
        val cell = row.getCell(1)
        cell.text = "${client.name}, ${client.inn}, ${client.address}, ${client.phoneClient}"
        super.generate(nameOfFile)
    }
}