package com.dicoding.tourismapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Tambahkan anotasi @HiltAndroidApp dan hapus seluruh isi body
@HiltAndroidApp
open class MyApplication : Application()