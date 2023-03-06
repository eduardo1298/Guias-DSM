package com.example.firebasekotlincrud


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasekotlincrud.databinding.ActivityCategoryListBinding


class CategoryListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backImageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.categorylist.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("data", 1);
            it.context.startActivity(intent)
        }


        binding.categorylist2.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("data", 2);
            it.context.startActivity(intent)
        }

        binding.categorylist3.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("data", 3);
            it.context.startActivity(intent)
        }

        binding.categorylist4.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("data", 4);
            it.context.startActivity(intent)
        }

    }

}