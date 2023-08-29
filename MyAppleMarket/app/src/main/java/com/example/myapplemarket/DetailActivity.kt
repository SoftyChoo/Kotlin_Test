package com.example.myapplemarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplemarket.Products.productList
import com.example.myapplemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra(MainActivity.PRODUCT_POSITION, 0)
        val product = productList[position]
        val likeFirstState = product.isClicked
        var isClicked = likeFirstState

        binding.apply {
            detailAddress.text = product.address
            detailTitle.text = product.title
            detialContent.text = product.content
            detailPrice.text = product.price.toString()
            detailImg.setImageResource(product.image)
            likestate(isClicked)

            //좋아요버튼 처리
            detailLike.setOnClickListener {
                if (isClicked) {
                    product.isClicked = false
                } else {
                    Snackbar.make(view, R.string.snackbar, Snackbar.LENGTH_SHORT).show()
                    product.isClicked = true
                }
                isClicked = !isClicked
                likestate(isClicked)
            }
            //뒤로가기 버튼 클릭 시
            btnCancel.setOnClickListener {
                handleCancelButtonClick(position,isClicked,likeFirstState)
            }
        }
    }
    override fun onDestroy() {
//        handleCancelButtonClick(position,isClicked,likeFirstState)
        super.onDestroy()
    }

    private fun handleCancelButtonClick(position: Int, isClicked: Boolean, likeFirstState: Boolean) {
        intent = Intent()
        intent.putExtra(LIKE_IS_CLICKED, isClicked)
        intent.putExtra(MainActivity.PRODUCT_POSITION, position)
        intent.putExtra(LIKE_IS_CLICKED_STATE, likeFirstState)
        setResult(RESULT_OK, intent)
        finish()
    }
    private fun initView() = with(binding) {

    }

    private fun likestate(isClicked: Boolean) {
        if (isClicked) binding.detailLike.setImageResource(R.drawable.heart_fill)
        else binding.detailLike.setImageResource(R.drawable.heart)
    }

    companion object {
        val LIKE_IS_CLICKED = "like"
        val LIKE_IS_CLICKED_STATE = "state"
    }

}