package me.seungyeol.tobisp.Chapter1.TemplateCallBack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {


    public Integer calcSum(String filepath) throws IOException {
        LineCallback<Integer> sumCallback = new LineCallback<>() {
            @Override
            public Integer domSomethingWithLine(String line, Integer value) {
                return value + Integer.parseInt(line);
            }
        };
        return lineReadTemplate(filepath, sumCallback,0);
    }
    public Integer calcMultiply(String filePath) throws IOException {
        LineCallback<Integer> multiplyCallback = new LineCallback<>() {
            @Override
            public Integer domSomethingWithLine(String line, Integer value) {
                return value * Integer.parseInt(line);
            }
        };
        return lineReadTemplate(filePath,multiplyCallback,1);
    }
    public String concatenate(String filepath) throws IOException {
        LineCallback<String> concatenateCallback = new LineCallback<String>() {
            @Override
            public String domSomethingWithLine(String line, String value) {
                return value + line;
            }
        };
        return lineReadTemplate(filepath,concatenateCallback, "");
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

    public <T> T lineReadTemplate(String filePath, LineCallback<T> callback, T initVal) throws IOException {
        BufferedReader br = null;
        T res = initVal;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = null;
            while((line = br.readLine()) != null) {
                res = callback.domSomethingWithLine(line,res);
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
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
        return res;
    }

}

