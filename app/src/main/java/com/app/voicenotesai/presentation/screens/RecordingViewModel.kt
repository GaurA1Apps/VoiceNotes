package com.app.voicenotesai.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.voicenotesai.data.local.entities.AudioRecord
import com.app.voicenotesai.domain.repository.RecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordingsViewModel @Inject constructor(
    private val repository: RecordRepository
) : ViewModel() {

    private var _recordings = MutableStateFlow<List<AudioRecord>>(emptyList())
    val recordings: StateFlow<List<AudioRecord>> = _recordings

    init {
        getRecordings()
    }

    private fun getRecordings() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllRecords()
                .collectLatest {
                _recordings.value = it
            }
        }
    }

    fun insertRecord(record: AudioRecord) {
        viewModelScope.launch {
            repository.insertRecord(record)
        }
    }


}