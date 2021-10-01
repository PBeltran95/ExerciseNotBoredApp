package ar.com.example.notbored.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.com.example.notbored.data.Category
import ar.com.example.notbored.data.RecreationalActivity
import ar.com.example.notbored.domain.repository.DataRepository

class SharedViewModel: ViewModel() {

    private var _categorySelected = MutableLiveData<Category>()
    val categorySelected : LiveData<Category> = _categorySelected

    fun saveCategorySelected(category: Category) {
        _categorySelected.value = category
    }

    private var _randomFromCategory = MutableLiveData<RecreationalActivity>()
    val randomFromCategory:LiveData<RecreationalActivity> = _randomFromCategory

    fun getRandomFromCategory(category: Category){
        _randomFromCategory.value = category.activities[(0..2).random()]
    }

    private var _totallyRandomSuggestion = MutableLiveData<RecreationalActivity>()
    val totallyRandomSuggestion = _totallyRandomSuggestion

    fun onRandomPressed(){
        _totallyRandomSuggestion.value = DataRepository.listOfCategories[(0..10).random()].activities[(0..2).random()]
    }

    private var _priceValue = MutableLiveData<String>()
    val priceValue:LiveData<String> = _priceValue

    fun priceToString(price: Double) {
         when(price){
            0.0 -> _priceValue.value = "Free"
            in 0.1..0.3 -> _priceValue.value = "Low"
            in 0.4..0.6 -> _priceValue.value = "Medium"
            else -> _priceValue.value = "High"
        }
    }

    private var editTextIsValid = MutableLiveData(false)
    val fetchEditTextCheck : LiveData<Boolean> = editTextIsValid

    fun checkEditText(etNumber: String){
        editTextIsValid.value = !etNumber.isEmpty()
    }

    fun preventEditTextBeginWithZero(etNumberValue: String):MutableLiveData<Boolean> {
        return if (!etNumberValue.startsWith("0")){
            MutableLiveData(false)
        }else {
            MutableLiveData(true)
        }
    }
}