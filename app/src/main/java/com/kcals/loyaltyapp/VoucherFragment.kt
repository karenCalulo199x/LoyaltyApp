package com.kcals.loyaltyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kcals.loyaltyapp.databinding.FragmentVoucherBinding

class VoucherFragment : Fragment() {
    private lateinit var binding: FragmentVoucherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVoucherBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_voucher, container, false)
    }
}