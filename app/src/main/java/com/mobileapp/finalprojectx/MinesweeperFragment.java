package com.mobileapp.finalprojectx;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapp.finalprojectx.databinding.FragmentMinesweeperBinding;
import com.mobileapp.finalprojectx.databinding.FragmentTictactoeBinding;

public class MinesweeperFragment extends Fragment {

    private FragmentMinesweeperBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMinesweeperBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }




}