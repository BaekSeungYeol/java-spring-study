package me.seungyeol.tobisp.Chapter1.TemplateCallBack;

public interface LineCallback<T> {
    T domSomethingWithLine(String line, T value);
}
