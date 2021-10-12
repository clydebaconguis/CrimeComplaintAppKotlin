package com.example.online_crime_reporting_app.Public_user

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.online_crime_reporting_app.R
import java.util.*

class AddReport : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    //for calendar and time variables
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0
    //for picking date and time
    lateinit var btnTimePicker : Button

    //for file of complaint variables
    //victims info
    lateinit var victimsName:EditText
    lateinit var victimsPhone:EditText
    lateinit var victimsEmail : EditText
    lateinit var lastname:EditText
    lateinit var whatHappend:EditText
    lateinit var howCommitted:EditText
    lateinit var location:EditText
    lateinit var dateTime :TextView
    //offenders description
    lateinit var offender:EditText
    lateinit var address:EditText
    lateinit var relation:EditText
    lateinit var age:Spinner //spinner
    lateinit var height:Spinner //spinner
    lateinit var bodyShape:Spinner //spinner
    lateinit var eyeColor:EditText
    lateinit var speech:Spinner //spinner
    lateinit var movement:EditText
    lateinit var clothing:EditText
    lateinit var directionFled:EditText
    lateinit var vehicleDescription:EditText
    lateinit var dangerousness:Spinner//spinner
    lateinit var evidence:EditText
    //buttons
    lateinit var btnSubmitComplaint:Button
    lateinit var rdButton1 : RadioButton
    lateinit var rdButton2: RadioButton
    lateinit var linear: LinearLayout
    lateinit var linear2:LinearLayout

    val none = "N/A"
    var radioBtn1IsActive = false
    var radioBtn2IsActive = false
    //progress bar
  /*  lateinit var writeProgressBarLayout: RelativeLayout
    lateinit var writeProgressBar:ProgressBar*/

    var fname = none
    var phNum = none
    var email = none
    var whatHap = none
    var howCom = none
    var dt = none
    var loc = none
    var perp = none
    var ad = none
    var rel=none
    var ageS = none
    var htS = none
    var bshS = none
    var eyeC = none
    var spS = none
    var mov = none
    var clo = none
    var dir = none
    var veh = none
    var dangeS = none
    var evi = none

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //status bar
        setStatusBarColor()

        setContentView(R.layout.activity_add_report)
        initializeAll()
    }
    fun setBtnRadio(){
        rdButton1.setOnClickListener {
            radioBtn1IsActive = true
            radioBtn2IsActive = false
            rdButton1.isChecked = true
            rdButton2.isChecked = false
            linear2.isVisible = true
            linear.isVisible = false
        }

        rdButton2.setOnClickListener {
            radioBtn1IsActive = false
            radioBtn2IsActive = true
            rdButton1.isChecked = false
            rdButton2.isChecked = true
            linear2.isVisible = false
            linear.isVisible = true
        }
    }

    fun initializeAll(){
        //toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar2))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        //datePicker button
        btnTimePicker = findViewById(R.id.btn_timePicker)
        pickDate()
        //initialized id's progress bar
        /*  writeProgressBarLayout=findViewById(R.id.writeProgressBarLayout)
          writeProgressBar=findViewById(R.id.writeProgressBar)*/

        //file complaint variables initialized id's
        victimsName = findViewById(R.id.edt_witnessName)
        lastname = findViewById(R.id.edt_lastname)
        victimsPhone = findViewById(R.id.edt_phone)
        victimsEmail = findViewById(R.id.edt_victimsEmail)
        whatHappend = findViewById(R.id.edt_q1)
        howCommitted = findViewById(R.id.edt_q2)
        dateTime = findViewById(R.id.tv_textTime)
        location = findViewById(R.id.edt_location)
        rdButton1 = findViewById(R.id.rd_1)
        rdButton2 = findViewById(R.id.rd_2)

        offender = findViewById(R.id.et_offender)
        address = findViewById(R.id.et_address)
        relation = findViewById(R.id.et_relation)
        age = findViewById(R.id.spinnerAge)
        height = findViewById(R.id.spinnerHeight)
        bodyShape = findViewById(R.id.spinnerBodyShape)
        eyeColor = findViewById(R.id.edt_eyeColor)
        speech = findViewById(R.id.spinnerSpeech)
        movement = findViewById(R.id.edt_movement)
        clothing = findViewById(R.id.edt_clothing)

        directionFled = findViewById(R.id.edt_q6)
        vehicleDescription = findViewById(R.id.edt_q7)
        dangerousness = findViewById(R.id.spinnerDangerousness)
        evidence = findViewById(R.id.edt_q9)
        btnSubmitComplaint = findViewById(R.id.btn_submit)

        linear = findViewById(R.id.linearMain)
        linear2 = findViewById(R.id.linearMain2)
        linear.isVisible = false
        linear2.isVisible = false
        //progressBar visibility
/*        writeProgressBarLayout.visibility= View.GONE
        writeProgressBar.visibility=View.GONE*/

        /*  val height = resources.getStringArray(R.array.Height)
          val body = resources.getStringArray(R.array.BodyShape)
          val speech = resources.getStringArray(R.array.Speech)
          val danger = resources.getStringArray(R.array.Dangerousness)*/

        populateSpinnerAge()
        populateSpinnerHeight()
        populateSpinnerBodyShape()
        populateSpinnerSpeech()
        populateSpinnerDanger()

        setBtnRadio()

        btnSubmitComplaint.setOnClickListener{
            if (notEmpty()){
                submitComplaint()
            }
            else{
                Toast.makeText(this,"Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun submitComplaint(){
        /*  writeProgressBarLayout.visibility= View.VISIBLE
          writeProgressBar.visibility=View.VISIBLE*/
        val action = "addItem"
        fname =victimsName.text.toString() +" "+ lastname.text.toString()
        phNum = victimsPhone.text.toString()
        email= victimsEmail.text.toString()
        whatHap = whatHappend.text.toString()
        howCom = howCommitted.text.toString()
        loc = location.text.toString()
        dt = dateTime.text.toString()
        dir = directionFled.text.toString()
        veh = vehicleDescription.text.toString()
        dangeS = dangerousness.selectedItem.toString()
        evi=evidence.text.toString()

        if(rdButton1.isChecked){
            perp = offender.text.toString()
            ad = address.text.toString()
            rel = relation.text.toString()
            ageS = none
            htS = none
            bshS = none
            eyeC = none
            spS = none
            mov = none
            clo = none
        }
        else if(rdButton2.isChecked){
            perp = none
            ad = none
            rel = none
            ageS = age.selectedItem.toString()
            htS = height.selectedItem.toString()
            bshS = bodyShape.selectedItem.toString()
            eyeC = eyeColor.text.toString()
            spS = speech.selectedItem.toString()
            mov = movement.text.toString()
            clo = clothing.text.toString()
        }

        val url = "https://script.google.com/macros/s/AKfycbxGgZol4z4gbfIbSDGnwm8CnZ6yqMqZc9lxowd37Rt1SHtviaf7AjjwGZxTcKz6kTSH/exec"
        val stringRequest= object : StringRequest(
            Request.Method.POST,url,
            Response.Listener{
                Toast.makeText(this@AddReport, it.toString(), Toast.LENGTH_SHORT).show()
                /* writeProgressBarLayout.visibility= View.GONE
                 writeProgressBar.visibility=View.GONE*/
            },
            Response.ErrorListener {
                Toast.makeText(this@AddReport, it.toString(), Toast.LENGTH_SHORT).show()
                /* writeProgressBarLayout.visibility= View.GONE
                 writeProgressBar.visibility=View.GONE*/
            }){
            override fun getParams(): MutableMap<String, String> {
                val params=HashMap<String,String>()
                params["action"]=action
                params["victims"]=fname
                params["phone"]= phNum
                params["email"]= email
                params["whatHappened"]=whatHap
                params["howCommitted"]=howCom
                params["location"]=loc
                params["time"]=dt
                params["perpetrator"]=perp
                params["address"]=ad
                params["relation"]=rel

                params["age"]= ageS //spinner
                params["height"]=htS //spinner
                params["bodyShape"]= bshS //spinner
                params["eyeColor"]=eyeC
                params["speech"]=spS
                params["movement"]=mov
                params["clothing"]=clo

                params["directionFled"]=dir
                params["vehicleDescription"]=veh
                params["dangerousness"]=dangeS //spinner
                params["evidence"]=evi
                params["status"]="0"
                params["username"]="temporary"
                // params["username"]
                return params
            }
        }
        val queue = Volley.newRequestQueue(this@AddReport)
        queue.add(stringRequest)
    }
    fun populateSpinnerAge(){
        val aged = resources.getStringArray(R.array.Age)
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, aged)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
       age.adapter = aa
    }
    fun populateSpinnerHeight(){
        val ht = resources.getStringArray(R.array.Height)
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, ht)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        height.adapter = aa
    }
    fun populateSpinnerBodyShape(){
        val bs = resources.getStringArray(R.array.BodyShape)
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, bs)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        bodyShape.adapter = aa
    }
    fun populateSpinnerSpeech(){
        val sp = resources.getStringArray(R.array.Speech)
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, sp)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        speech.adapter = aa
    }
    fun populateSpinnerDanger(){
        val dg = resources.getStringArray(R.array.Dangerousness)
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, dg)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        dangerousness.adapter = aa
    }
    private fun notEmpty() : Boolean{
        if ( victimsName.text.isNotEmpty() && victimsPhone.text.isNotEmpty() && directionFled.text.isNotEmpty()
                && vehicleDescription.text.isNotEmpty() && evidence.text.isNotEmpty() && whatHappend.text.isNotEmpty() && howCommitted.text.isNotEmpty()
                && dateTime.text.isNotEmpty() && location.text.isNotEmpty() && victimsEmail.text.isNotEmpty()){
             if (radioBtn1IsActive&&offender.text.isNotEmpty()&&address.text.isNotEmpty()&&relation.text.isNotEmpty()){
                 return true
             }else if(radioBtn2IsActive&&eyeColor.text.isNotEmpty()&& movement.text.isNotEmpty()&&clothing.text.isNotEmpty()){
                 return true
             }
            else
                return false
        }else
            return false

    }
    private fun setStatusBarColor(){
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }
    private fun pickDate(){
        btnTimePicker.setOnClickListener {
            getDateTimeCalendar()

            DatePickerDialog(this, this, year,month,day).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()
        TimePickerDialog(this,this, hour, minute, true).show()
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute

        dateTime.text = "$savedDay-$savedMonth-$savedYear\n Hour: $savedHour Minute: $savedMinute"
    }



}