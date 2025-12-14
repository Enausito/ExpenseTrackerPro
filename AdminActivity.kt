package com.ejemplo.expensetrackerpro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ejemplo.expensetrackerpro.databinding.ActivityAdminBinding
import com.ejemplo.expensetrackerpro.utils.FileHelper

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private lateinit var fileHelper: FileHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fileHelper = FileHelper(this)
        
        loadRawData()
        
        binding.btnExport.setOnClickListener {
            shareData()
        }
    }

    private fun loadRawData() {
        val rawContent = fileHelper.readRawFile()
        binding.tvLogContent.text = rawContent.ifEmpty { "Archivo vacío." }
    }
    
    private fun shareData() {
        val rawContent = binding.tvLogContent.text.toString()
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, rawContent)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Exportar Datos TXT")
        startActivity(shareIntent)
    }
}
