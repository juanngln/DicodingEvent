package com.idcamp.dicodingevent.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.idcamp.dicodingevent.data.response.EventDetailItem
import com.idcamp.dicodingevent.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val eventId = intent.getStringExtra("EVENT_ID") ?: return
        detailViewModel.fetchEventDetail(eventId)

        detailViewModel.eventDetail.observe(this) { eventDetail ->
            setDetailData(eventDetail)
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setDetailData(event: EventDetailItem) {
        val remainingQuota = event.quota?.minus(event.registrants!!)

        Glide.with(this)
            .load(event.mediaCover)
            .into(binding.ivMediaCover)
        binding.tvName.text = event.name
        binding.tvOwner.text = event.ownerName
        binding.tvBeginTime.text = event.beginTime
        binding.tvQuota.text = remainingQuota.toString()
        binding.tvDescription.text = HtmlCompat.fromHtml(
            event.description.toString(),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        binding.btnActionButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.link))
            startActivity(intent)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
