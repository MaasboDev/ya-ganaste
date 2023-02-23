package com.maasbodev.yaganaste.di

import com.maasbodev.yaganaste.data.remote.BankApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object BankApiModule {

	@Provides
	@Singleton
	fun provideCountryApiService(builder: Retrofit.Builder): BankApiService {
		return builder
			.build()
			.create(BankApiService::class.java)
	}

	@Provides
	@Singleton
	fun provideRetrofit(): Retrofit.Builder {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(MoshiConverterFactory.create())
	}
}

private const val BASE_URL = "https://dev.obtenmas.com/catom/api/challenge/"