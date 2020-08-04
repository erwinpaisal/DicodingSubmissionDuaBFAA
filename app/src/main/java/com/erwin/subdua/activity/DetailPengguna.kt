package com.erwin.subdua.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.erwin.subdua.R
import com.erwin.subdua.adapter.SectionsPagerAdapter
import com.erwin.subdua.data.DataPengguna
import kotlinx.android.synthetic.main.detail_pengguna.*

class DetailPengguna : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_pengguna)
        setData()
        viewPagerConfig()
    }

    private fun viewPagerConfig() {
        val sectionPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_page.adapter = sectionPagerAdapter
        tabss.setupWithViewPager(view_page)

        supportActionBar?.elevation = 0f
    }


    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            this.title = title
        }
    }

    @SuppressLint("SetTextI18n", "StringFormatInvalid")
    private fun setData() {
        val penggunaData = intent.getParcelableExtra(EXTRA_DATA) as DataPengguna
        penggunaData.name?.let { setActionBarTitle(it) }
        name_detail.text = penggunaData.name
        username_detail.text = penggunaData.username
        company_detail.text = getString(R.string.company, penggunaData.company)
        location_detail.text = getString(R.string.location, penggunaData.location)
        repository_detail.text = getString(R.string.repository, penggunaData.repository)
        Glide.with(this)
            .load(penggunaData.avatar)
            .into(avatar_detail)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.change_act_setting) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

}
