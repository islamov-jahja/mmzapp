package com.natasha.mmzdemo.infrastructure.helpers

import com.natasha.mmzdemo.domain.core.entity.Client
import com.natasha.mmzdemo.domain.core.generatorsword.DocumentGenerator
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ContractDocxGenerator(@Value("./resources/f2.docx") _inputPath: String, @Value("./public/") _outPath: String) : DocumentGenerator(_inputPath, _outPath) {
    fun generate(client: Client, nameOfFile: String){
        super.replaceTextOnDocument(docxFile, "organization", client.name)
        super.replaceTextOnDocument(docxFile, "position", client.positionDirector)
        super.replaceTextOnDocument(docxFile, "fio", client.fioDirector)
        super.replaceTextOnDocument(docxFile, "email", client.emailOfClient)
        super.replaceTextOnDocument(docxFile, "address", client.address)
        super.replaceTextOnDocument(docxFile, "bik", client.bicClientBank)
        super.replaceTextOnDocument(docxFile, "inn", client.inn)
        super.replaceTextOnDocument(docxFile, "kpp", client.kpp)
        super.replaceTextOnDocument(docxFile, "umber", client.phoneClient)
        super.replaceTextOnDocument(docxFile, "rs", client.paymentAccount)
        super.replaceTextOnDocument(docxFile, "kors", client.correspondentAccount)
        super.generate(nameOfFile)
    }
}