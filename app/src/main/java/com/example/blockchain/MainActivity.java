package com.example.blockchain;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    Button add, varify, edit;
    List<Block> blockChain;
    Block c;
    MyRvAdapter adapter;
    EditText index, amount;

    String editIndex, editAmont;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    boolean isvalid() throws NoSuchAlgorithmException {
        for (int i = 1; i < this.blockChain.size() ; i++){
            Block currentBlock = this.blockChain.get(i);
            Block previousBlock = this.blockChain.get(i-1);

            String hashvalue = currentBlock.calculatehash();
            if (!currentBlock.getHash().equals(hashvalue)){
                return false;
            }
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())){
                return false;
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        add = findViewById(R.id.add);
        edit = findViewById(R.id.edit);
        varify = findViewById(R.id.varify);
        amount = findViewById(R.id.amount);
        index = findViewById(R.id.index);

        blockChain = new ArrayList<>();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editIndex = index.getText().toString();
                editAmont = amount.getText().toString();

                index.setText("");
                amount.setText("");

                int index = Integer.parseInt(editIndex);

                blockChain.get(index - 1).setData(editAmont);
                Toast.makeText(MainActivity.this, "Block Edited Successfully!!!", Toast.LENGTH_SHORT).show();
                rv = findViewById(R.id.rv);
                RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
                rv.setLayoutManager(lm);
                MyRvAdapter adapter = new MyRvAdapter(blockChain, getApplicationContext());
                adapter.setContactList(blockChain);
                rv.setAdapter(adapter);


            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,1);
            }
        });

        varify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean varified = false;
                try {
                    varified = isvalid();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                if (varified)
                    Toast.makeText(MainActivity.this, "Blockchain Varified", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Invalid Blockchain", Toast.LENGTH_LONG).show();
            }
        });

        try {
            blockChain.add(new Block("18/4/2015"    , "10"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        adapter = new MyRvAdapter(blockChain, this);
        adapter.setContactList(blockChain);
        rv.setAdapter(adapter);
    }

    Block getLatestBlock(){
        return blockChain.get(this.blockChain.size() - 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void addBlock(Block newblock) throws NoSuchAlgorithmException {
        newblock.setPreviousHash(this.getLatestBlock().getHash());
        newblock.setHash(newblock.calculatehash());
        this.blockChain.add(newblock);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("result");


                StringTokenizer tokenizer = new StringTokenizer(result, ",");

                String tempData = " ";
                String tempDate = " ";

                int counter = 0;
                while (tokenizer.hasMoreTokens()) {
                    if (counter == 0)
                        tempData = tokenizer.nextToken();
                    if (counter == 1)
                        tempDate = tokenizer.nextToken();
                    counter++;
                }

                String Result = tempData + "," + tempDate;

                try {
                    this.addBlock(new Block(tempDate, tempData));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }


                rv = findViewById(R.id.rv);
                RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
                rv.setLayoutManager(lm);
                MyRvAdapter adapter = new MyRvAdapter(blockChain, this);
                adapter.setContactList(blockChain);
                rv.setAdapter(adapter);

                //Toast.makeText(MainActivity.this, Result, Toast.LENGTH_LONG).show();

            }
        }
    }
}