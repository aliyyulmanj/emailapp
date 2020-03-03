package com.exampled3if4070.coba02


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.exampled3if4070.coba02.databinding.FragmentBmiBinding

/**
 * A simple [Fragment] subclass.
 */
class BMIFragment : Fragment() {

    private lateinit var binding:FragmentBmiBinding
    private var bmi = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bmi, container, false)

        binding.btnHitung.setOnClickListener { hitung()}
        binding.btnShare.setOnClickListener{ share()}

        return binding.root
    }

    private fun hitung(){
        if (binding.edTinggi.text.isEmpty()){
            Toast.makeText(activity, "ttidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else {
            if (binding.rgGender.checkedRadioButtonId == -1){
                Toast.makeText(activity,"pilih gender",Toast.LENGTH_SHORT).show()
            } else {
                if (binding.rbMale.isChecked){
                    bmi = binding.edTinggi.text.toString().toDouble() - 100
                }else {
                    bmi = (binding.edTinggi.text.toString().toDouble() - 100) * 0.9
                }
                binding.tvHasil.text = "$bmi"
            }
        }
    }
    private fun share(){
        val email = Intent(Intent.ACTION_SENDTO)
        email.data = Uri.parse("mailto:")
        email.putExtra(Intent.EXTRA_SUBJECT, "Hasil Body Mass Index")
        email.putExtra(Intent.EXTRA_TEXT, "Hasil BMI : $bmi")
        startActivity(email)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("hasil", bmi)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (savedInstanceState != null){
            bmi = savedInstanceState.getDouble("hasil")
            binding.tvHasil.text = "$bmi"
        }
        super.onViewStateRestored(savedInstanceState)
    }


}
