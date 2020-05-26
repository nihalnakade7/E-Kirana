package com.example.ekirana;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectedItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView descriptyion_tv;
    TextView price_tv;
    TextView total_tv;
    ImageView mImageView;
    EditText units_edtTxt;
    Button mButton;

    SelectedProductAdapter.onButtonClickListener mListener;

    public SelectedItemViewHolder(@NonNull View itemView, SelectedProductAdapter.onButtonClickListener listener) {

        super(itemView);
        this.descriptyion_tv = itemView.findViewById(R.id.decsription);
        this.price_tv = itemView.findViewById(R.id.textView_price);
        this.total_tv = itemView.findViewById(R.id.total);
        this.mButton = itemView.findViewById(R.id.addItembtn);
        this.units_edtTxt = itemView.findViewById(R.id.units_edtTxt);
        mImageView = itemView.findViewById(R.id.imageViewProduct);
        mListener = listener;
        this.mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String val = units_edtTxt.getText().toString();
            mListener.onButtonClick(getAdapterPosition(),val);
    }
}
