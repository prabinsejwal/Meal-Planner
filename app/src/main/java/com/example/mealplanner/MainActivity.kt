package com.example.mealplanner
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var daySpinner: Spinner
    private lateinit var mealTypeSpinner: Spinner
    private lateinit var addButton: Button
    private lateinit var mealPlanTextView: TextView
    private lateinit var mealDescriptionEditText: EditText
    private var selectedDay: String = "Monday" // Default value
    private var selectedMealType: String = "Breakfast" // Default value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        daySpinner = findViewById(R.id.daySpinner)
        mealTypeSpinner = findViewById(R.id.mealTypeSpinner)
        addButton = findViewById(R.id.addButton)
        mealPlanTextView = findViewById(R.id.mealPlanTextView)
        mealDescriptionEditText = findViewById(R.id.mealDescriptionEditText)

        // Set up Day Spinner
        val dayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.days_of_week,
            android.R.layout.simple_spinner_item
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        daySpinner.adapter = dayAdapter

        daySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedDay = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Set up Meal Type Spinner
        val mealTypeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.meal_types,
            android.R.layout.simple_spinner_item
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        mealTypeSpinner.adapter = mealTypeAdapter

        mealTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedMealType = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Set up Button
        addButton.setOnClickListener {
            val mealDescription = mealDescriptionEditText.text.toString()
            if (mealDescription.isNotBlank()) {
                val currentText = mealPlanTextView.text.toString()
                val newText = "Meal plan for $selectedDay ($selectedMealType): $mealDescription"
                mealPlanTextView.text = if (currentText == "Meal plans will be displayed here") {
                    newText
                } else {
                    "$currentText\n$newText"
                }
                mealDescriptionEditText.text.clear()
            } else {
                mealPlanTextView.text = "Please enter a meal description."
            }
        }
    }
}
