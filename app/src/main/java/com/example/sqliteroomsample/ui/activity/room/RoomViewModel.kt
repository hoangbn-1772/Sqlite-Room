package com.example.sqliteroomsample.ui.activity.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sqliteroomsample.data.model.room.User
import com.example.sqliteroomsample.data.repository.UserLocalRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RoomViewModel(private val repository: UserLocalRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _insertUserLiveData = MutableLiveData<String>()

    val insertUserLiveData: LiveData<String>
        get() = _insertUserLiveData

    private val _getUserLiveData = MutableLiveData<List<User>>()

    val getUserLiveDate: LiveData<List<User>>
        get() = _getUserLiveData

    fun insertUser(user: User) {
        compositeDisposable.add(
            repository.insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _insertUserLiveData.value = it.toString()
                    },
                    {
                        _insertUserLiveData.value = it.toString()
                    }
                )
        )
    }

    fun getUser() {
        compositeDisposable.add(
            repository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { users ->
                        _getUserLiveData.value = users
                    },
                    { error ->
                        _getUserLiveData.value = mutableListOf()
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
