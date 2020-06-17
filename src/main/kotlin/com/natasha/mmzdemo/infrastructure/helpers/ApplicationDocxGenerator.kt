package com.natasha.mmzdemo.infrastructure.helpers

import com.natasha.mmzdemo.application.controllers.application.dto.Si
import com.natasha.mmzdemo.domain.core.generatorsword.DocumentGenerator
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.FileOutputStream
import java.util.*


@Component
class ApplicationDocxGenerator(@Value("./resources/f1.docx") _inputPath: String, @Value("./resources/") _outPath: String) : DocumentGenerator(_inputPath, _outPath) {
    fun generate(listSi: List<Si>, date: Date, nameOfFile: String): XWPFDocument {
        var table = docxFile.tables[0]
        var number: Int = 1
        for (si in listSi){
            var row = table.createRow()
            row.getCell(0).text = "$number"
            row.getCell(1).text = si.name
            row.getCell(2).text = si.description
            row.getCell(3).text = si.type
            row.getCell(4).text = si.factoryNumber
            row.getCell(5).text = "${si.count}"
            row.getCell(6).text = si.numberOnRegister
            row.getCell(7).text = si.note
            number++
        }

        for (p in docxFile.paragraphs) {
            val runs = p.runs
            if (runs != null) {
                for (r in runs) {
                    var text = r.getText(0)
                    println(text)
                    if (text != null && text.contains("dateTime")) {
                        text = text.replace("dateTime", dateFormat.format(date))
                        r.setText(text, 0)
                    }
                }
            }
        }

        outputStream = FileOutputStream(outputPath + nameOfFile)
        docxFile.write(outputStream)

        outputStream.close()
        return docxFile
    }
}