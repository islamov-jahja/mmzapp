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
class CertificateOfCompletionDocxGenerator(@Value("./resources/f4.docx") _inputPath: String, @Value("./public/") _outPath: String): DocumentGenerator(_inputPath, _outPath) {
    fun generate(client: Client, listSi: List<Si>, nameOfFile: String){
        val fileInputStream = FileInputStream(super.inputPath)
        docxFile = XWPFDocument(OPCPackage.open(fileInputStream))
        val table = docxFile.tables[0]

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

        val nds = sum * 0.2
        val sumWithNds = sum + nds

        super.replaceTextOnDocument(docxFile, "fio", client.fioDirector)
        super.replaceTextOnDocument(docxFile, "position", client.positionDirector)
        super.replaceTextOnDocument(docxFile, "organization", client.name)
        super.replaceTextOnDocument(docxFile, "inn", client.inn)
        super.replaceTextOnDocument(docxFile, "kpp", client.kpp)
        super.replaceTextOnDocument(docxFile, "address", client.address)
        super.replaceTextOnDocument(docxFile, "nds", nds.toString())
        super.replaceTextOnDocument(docxFile, "sum", sumWithNds.toString())

        val i: Int =sumWithNds.toInt();
        val d2: Int = ((sumWithNds - i)*100).toInt()

        super.replaceTextOnDocument(docxFile, "simp", d2.toString())
        super.replaceTextOnDocument(docxFile, "simple", d2.toString())

        val nf = RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT)

        super.replaceTextOnDocument(docxFile, "word", nf.format(sumWithNds))
        super.generate(nameOfFile)

    }
}