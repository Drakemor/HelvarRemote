package com.smok.liremote.helvarremote;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ConsumerIrManager mCIR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get a reference to the ConsumerIrManager
        mCIR = (ConsumerIrManager)getSystemService(Context.CONSUMER_IR_SERVICE);

        setContentView(R.layout.activity_main);

        findViewById(R.id.button0).setOnClickListener(new TransmitClicker(new int[]{900,850, 1800,1750, 850,900, 850,900, 1750,1750, 1750,900, 900,1700, 850,900, 1800,850, 900}));
        findViewById(R.id.button1).setOnClickListener(new TransmitClicker(new int[]{950,800, 950,800, 950,850, 900,850, 900,850, 1800,1700, 1850,800, 950,800, 950,800, 950,800, 950,1700, 950}));
        findViewById(R.id.button2).setOnClickListener(new TransmitClicker(new int[]{900,850, 900,850, 900,850, 900,850, 900,850, 1850,1650, 1850,800, 950,800, 950,800, 950,1700, 1800}));
        findViewById(R.id.button3).setOnClickListener(new TransmitClicker(new int[]{900,850, 900,900, 850,900, 850,900, 850,900, 1750,1750, 1750,900, 850,900, 850,900, 850,1800, 850,900, 850}));
        findViewById(R.id.button4).setOnClickListener(new TransmitClicker(new int[]{900,850, 1850,1650, 950,800, 950,800, 1850,1700, 1800,800, 950,800, 1000,1650, 1850,800, 950}));
        findViewById(R.id.buttonUP).setOnClickListener(new TransmitClicker(new int[]{850,900, 1800,1700, 900,900, 850,900, 1800,1700, 1750,1750, 1750,900, 850,900, 850,900, 900}));
        findViewById(R.id.buttonDOWN).setOnClickListener(new TransmitClicker(new int[]{950,800, 1850,1700, 900,850, 900,850, 1850,1650, 1850,1650, 1850,800, 950,800, 950,1700, 950}));
    }

    public class TransmitClicker implements View.OnClickListener {

        int[] pattern;
        TransmitClicker(int[] pattern) {
            this.pattern = pattern;
        }

        @Override
        public void onClick(View v) {
            if (!mCIR.hasIrEmitter()) {
                Toast.makeText(MainActivity.this,"No IR Emitter found", Toast.LENGTH_LONG).show();
                return;
            }
            // transmit the pattern at 38.4KHz
            mCIR.transmit(38400, pattern);
        }
    }
}
