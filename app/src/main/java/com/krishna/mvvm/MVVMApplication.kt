package com.krishna.mvvm

import android.app.Application
import com.krishna.mvvm.data.db.AppDatabase
import com.krishna.mvvm.data.network.MyApi
import com.krishna.mvvm.data.network.NetworkConnectionInterceptor
import com.krishna.mvvm.data.repositary.QuotesRepositary
import com.krishna.mvvm.data.repositary.UserRepositary
import com.krishna.mvvm.ui.auth.AuthViewModelFactory
import com.krishna.mvvm.ui.home.profile.ProfileViewModelFactory
import com.krishna.mvvm.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication :Application(),KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepositary(instance(),instance()) }
        bind() from singleton { QuotesRepositary(instance(),instance()) }
        bind() from provider { AuthViewModelFactory(instance())  }
        bind() from provider { ProfileViewModelFactory(instance())  }
        bind() from provider { QuotesViewModelFactory(instance())  }
    }
}