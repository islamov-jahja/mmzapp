package com.natasha.mmzdemo.domain.core.generatorsword

import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.aspectj.lang.annotation.After
import java.io.BufferedOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.annotation.PreDestroy


abstract class DocumentGenerator(_inputPath: String, _outputPath: String) {

    protected lateinit var docxFile: XWPFDocument
    protected val outputPath = _outputPath
    protected lateinit var outputStream: FileOutputStream
    protected lateinit var dateFormat: SimpleDateFormat
    init {
        val fileInputStream = FileInputStream(_inputPath)
        docxFile = XWPFDocument(OPCPackage.open(fileInputStream))
        dateFormat = SimpleDateFormat("MM.WW.yyyy")
    }

    @PreDestroy
    fun onDestroy(){
        outputStream.close()
    }
}