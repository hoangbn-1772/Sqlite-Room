package com.example.sqliteroomsample.ui.activity.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sqliteroomsample.data.model.room.Gender
import com.example.sqliteroomsample.data.repository.GenderLocalRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RoomViewModel(private val genderLocalRepository: GenderLocalRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _genderLiveData = MutableLiveData<String>()

    val genderLiveData: LiveData<String>
        get() = _genderLiveData

    fun insertGender(gender: Gender) {
        compositeDisposable.add(genderLocalRepository.insertGender(gender)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _genderLiveData.value = "Ok"
                },
                {
                    _genderLiveData.value = it.toString()
                }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
    }
}
