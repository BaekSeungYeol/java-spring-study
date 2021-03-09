package me.seungyeol.tobisp.Chapter1.TemplateCallBack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {


    public Integer calcMultiply(String filepath) throws IOException {
        BufferedReaderCallBack multiplyCallback = new BufferedReaderCallBack() {
            @Override
            public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                Integer mul = 1;
                String line = null;
                while((line = br.readLine()) != null) {
                    mul *= Integer.parseInt(line);
                }
                return mul;
            }
        };
        return fileReadTemplate(filepath, multiplyCallback);
    }
    public Integer calcSum(String filepath) throws IOException {
        BufferedReaderCallBack sumCallback = new BufferedReaderCallBack() {
            @Override
            public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                Integer sum = 0;
                String line = null;
                while((line = br.readLine()) != null) {
                    sum += Integer.parseInt(line);
                }
                return sum;
            }
        };
        return fileReadTemplate(filepath, sumCallback);
    }

    public Integer fileReadTemplate(String filepath, BufferedReaderCallBack callBack) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            int ret = callBack.doSomethingWithReader(br);
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}

